// CreateRequest.jsx
import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { submitTrainingRequest } from '../trainingSlice';
import { useNavigate } from 'react-router-dom';

const CreateRequest = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    accountid: '',
    requestorname: '',
    coursename: '',
    description: '',
    concepts: '',
    duration: '',
    employeeposition: '',
    status: 'PENDING', // Default status
    createddate: new Date().toISOString().split('T')[0], // Default current date
    requiredemployees: 0,
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
  
    // Check if formData is valid before dispatching
    if (!formData.coursename) {
      console.error('Course Name is required.');
      return;
    }
  
    dispatch(submitTrainingRequest(formData))
      .unwrap()
      .then(() => {
        // Redirect to the home page on successful submission
        navigate('/');
      })
      .catch((error) => {
        // Log error and handle it appropriately
        console.error('Failed to save request:', error);
        alert('Failed to save request. Please try again.'); // Optional alert to inform the user
      });
  };
  

  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold mb-4 text-center">Create Request</h1>
      <form onSubmit={handleSubmit} className="space-y-4">
        <input
          type="text"
          name="accountid"
          value={formData.accountid}
          onChange={handleChange}
          placeholder="Account ID"
          className="p-2 border rounded w-full"
          required
        />
        <input
          type="text"
          name="requestorname"
          value={formData.requestorname}
          onChange={handleChange}
          placeholder="Requestor Name"
          className="p-2 border rounded w-full"
          required
        />
        <input
          type="text"
          name="coursename"
          value={formData.coursename}
          onChange={handleChange}
          placeholder="Course Name"
          className="p-2 border rounded w-full"
          required
        />
        <textarea
          name="description"
          value={formData.description}
          onChange={handleChange}
          placeholder="Description"
          className="p-2 border rounded w-full"
          required
        />
        <textarea
          name="concepts"
          value={formData.concepts}
          onChange={handleChange}
          placeholder="Concepts"
          className="p-2 border rounded w-full"
          required
        />
        <input
          type="text"
          name="duration"
          value={formData.duration}
          onChange={handleChange}
          placeholder="Duration"
          className="p-2 border rounded w-full"
          required
        />
        <input
          type="text"
          name="employeeposition"
          value={formData.employeeposition}
          onChange={handleChange}
          placeholder="Employee Position"
          className="p-2 border rounded w-full"
          required
        />
        <input
          type="number"
          name="requiredemployees"
          value={formData.requiredemployees}
          onChange={handleChange}
          placeholder="Required Employees"
          className="p-2 border rounded w-full"
          required
        />
        <button type="submit" className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">
          Submit Request
        </button>
      </form>
    </div>
  );
}

export default CreateRequest;
