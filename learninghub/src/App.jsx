// App.jsx
import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import HomeAccount from './components/HomeAccount';
import AccountTeamDashboard from './components/AccountTeamDashboard';
import CreateRequest from './components/CreateRequest';

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
      <Routes>
        <Route path="/" element={<HomeAccount />} />
        <Route path="/dashboard" element={<AccountTeamDashboard />} />
        <Route path="/create-request" element={<CreateRequest />} />
      </Routes>
    </Router>
  );
}

export default App;
