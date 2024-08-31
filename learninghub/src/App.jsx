// App.jsx
import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import HomeAccount from './components/HomeAccount';
import AccountTeamDashboard from './components/AccountTeamDashboard';
import CreateRequest from './components/CreateRequest';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css'; // Import the CSS for the toast notifications

function App() {
  return (
    <Router>
      <nav className="bg-gray-800 p-4 text-white">
        <ul className="flex space-x-4">
          <li>
            <Link to="/" className="hover:underline">Home</Link>
          </li>
          <li>
            <Link to="/dashboard" className="hover:underline">Dashboard</Link>
          </li>
          <li>
            <Link to="/create-request" className="hover:underline">Create Request</Link>
          </li>
        </ul>
      </nav>
      {/* ToastContainer should be placed here, so it is available globally */}
      <ToastContainer 
        position="top-right" // You can adjust the position of the toast notifications
        autoClose={3000} // Auto close the notification after 3 seconds
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        theme="colored" // Optional: change the theme of the notifications
      />
      <Routes>
        <Route path="/" element={<HomeAccount />} />
        <Route path="/dashboard" element={<AccountTeamDashboard />} />
        <Route path="/create-request" element={<CreateRequest />} />
      </Routes>
    </Router>
  );
}

export default App;
