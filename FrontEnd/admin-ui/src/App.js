import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import AdminDashboard from './Admin-UI/pages/AdminDashBoard';
//import RequestDetails from './Admin-UI/components/RequestDetails';

import CourseDisplayPage from './Admin-UI/pages/CourseDisplayPage';
import CreateCourseForm from './Admin-UI/components/CreateCourseForm';
import EditCourseForm from './Admin-UI/components/EditCourseForm';
import ViewCourse from './Admin-UI/components/ViewCourse';

import AccountTeamDashboard from './Account-UI/Components/AccountTeamDashboard';
import CreateRequest from './Account-UI/Components/CreateRequest';
import RequestDetails from './Admin-UI/components/RequestDetails';
import { ToastContainer } from 'react-toastify';
import HomePage from './Home-UI/components/HomePage';
import SignupForm from './Home-UI/components/Signup';
import LoginForm from './Home-UI/components/Login';


function App() {
  
  return (
    <Router>
      <div>
      <Routes>
        {/*Home Routes*/}
        <Route path='/' element={<HomePage/>}/>
        <Route path="/signup" element={<SignupForm />} />
        <Route path="/login" element={<LoginForm />} />


        {/* Admin Routes*/}
        <Route path="/admin-dashboard" element={<AdminDashboard />} />
        <Route path="/request-details/:requestid" element={<RequestDetails />} />
        <Route path="/create-course/:requestid" element={<CreateCourseForm/>} />
        <Route path="/courses" element={<CourseDisplayPage/>} /> 
        <Route path='/edit-course/:courseid' element={<EditCourseForm/>}/>
        <Route path="/view-course/:courseid" element={<ViewCourse/>} />
        
        {/* Accounts Route*/}
        <Route path="/manager-dashboard" element={<AccountTeamDashboard/>} />
      <Route path="/create-request" element={<CreateRequest/>} />

      </Routes>
      <ToastContainer/>
      </div>
     
    </Router>
  );
};
 

export default App;
