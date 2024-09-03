// HomeAccount.jsx
import React, { useEffect } from 'react';
import { Link } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { fetchRequestsByRequestName } from '../trainingSlice'; // Adjust the import path if needed

const HomeAccount = () => {
  const dispatch = useDispatch();

  // Extract data from Redux store
  const { requests, loading, error } = useSelector((state) => state.training);

  // Fetch requests when the component mounts
  useEffect(() => {
    dispatch(fetchRequestsByRequestName('kavin')); // Adjust the parameter if necessary
  }, [dispatch]);

  // Calculate total, completed, and pending requests
  const totalRequests = requests.length;
  const completedRequests = requests.filter((req) => req.status === 'COMPLETED').length;
  const pendingRequests = totalRequests - completedRequests;

  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold mb-4 text-center">Welcome, Manager</h1>

      {/* Display loading message while fetching data */}
      {loading ? (
        <p className="text-center text-lg font-semibold">Loading requests...</p>
      ) : (
        <>
          <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-6">
            <div className="bg-white p-6 shadow rounded-lg text-center">
              <h2 className="text-lg font-semibold">Total Requests</h2>
              <p className="text-3xl font-bold">{totalRequests}</p>
            </div>
            <div className="bg-white p-6 shadow rounded-lg text-center">
              <h2 className="text-lg font-semibold">Completed Requests</h2>
              <p className="text-3xl font-bold">{completedRequests}</p>
            </div>
            <div className="bg-white p-6 shadow rounded-lg text-center">
              <h2 className="text-lg font-semibold">Pending Requests</h2>
              <p className="text-3xl font-bold">{pendingRequests}</p>
            </div>
          </div>
          <Link to="/create-request">
            <button className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 mt-6 block mx-auto">
              Create New Request
            </button>
          </Link>
        </>
      )}
    </div>
  );
};

export default HomeAccount;
