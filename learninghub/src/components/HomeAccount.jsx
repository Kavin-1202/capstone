// HomeAccount.jsx
import React from 'react';
import { Link } from 'react-router-dom';
import { useSelector } from 'react-redux';

const HomeAccount = () => {
  // Example data
  const totalRequests = useSelector((state) => state.training.requests.length);
  const completedRequests = useSelector((state) => state.training.requests.filter(req => req.status === 'COMPLETED').length);
  const pendingRequests = totalRequests - completedRequests;

  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold mb-4 text-center">Welcome, Manager</h1>
      <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-6">
        <div className="bg-white p-4 shadow rounded-lg text-center">
          <h2 className="text-lg font-semibold">Total Requests</h2>
          <p className="text-2xl font-bold">{totalRequests}</p>
        </div>
        <div className="bg-white p-4 shadow rounded-lg text-center">
          <h2 className="text-lg font-semibold">Completed Requests</h2>
          <p className="text-2xl font-bold">{completedRequests}</p>
        </div>
        <div className="bg-white p-4 shadow rounded-lg text-center">
          <h2 className="text-lg font-semibold">Pending Requests</h2>
          <p className="text-2xl font-bold">{pendingRequests}</p>
        </div>
      </div>
      <Link to="/create-request">
        <button className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">
          Create New Request
        </button>
      </Link>
    </div>
  );
}

export default HomeAccount;
