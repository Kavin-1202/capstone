// EmployeeHome.js
import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { fetchCourses } from '../courseSlice'; // Use the new course slice
import CourseCard from './CourseCard';
import { useNavigate } from 'react-router-dom'; // Import useNavigate for navigation
import EmployeeNavbar from './EmployeeNavbar'
const EmployeeHome = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { courses, loading, error } = useSelector((state) => state.course); // Access course state
  const storedUsername = localStorage.getItem('username');

  useEffect(() => {
    dispatch(fetchCourses(storedUsername)); // Replace 'kavin' with the actual employee's username
  }, [dispatch]);

  const handleCourseClick = (coursename) => {
    navigate(`/courses/${coursename}`); // Navigate to the course details page
  };

  const totalAssigned = courses.length;
  const totalStarted = courses.filter((course) => course.status === 'Started').length;
  const totalCompleted = courses.filter((course) => course.status === 'Completed').length;

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error}</p>;

  return (
    <div className="p-6">
      <EmployeeNavbar />
      <h1 className="text-3xl font-bold mb-6 text-center">Employee Dashboard</h1>

      {/* Statistics Cards */}
      <div className="grid grid-cols-1 sm:grid-cols-3 gap-6 mb-6">
        <div className="bg-blue-100 p-4 rounded shadow">
          <h2 className="text-lg font-semibold">Total Courses Assigned</h2>
          <p className="text-2xl font-bold">{totalAssigned}</p>
        </div>
        <div className="bg-green-100 p-4 rounded shadow">
          <h2 className="text-lg font-semibold">Total Courses Started</h2>
          <p className="text-2xl font-bold">{totalStarted}</p>
        </div>
        <div className="bg-yellow-100 p-4 rounded shadow">
          <h2 className="text-lg font-semibold">Total Courses Completed</h2>
          <p className="text-2xl font-bold">{totalCompleted}</p>
        </div>
      </div>

      {/* Scrollable Courses List */}
      <h2 className="text-2xl font-bold mb-4">My Courses</h2>
      <div className="flex overflow-x-auto space-x-4 pb-4">
        {courses.length > 0 ? (
          courses.map((course) => (
            <CourseCard
              key={course.courseid}
              course={course}
              onStart={() => handleCourseClick(course.coursename)} // Navigate on course click
            />
          ))
        ) : (
          <p>No courses assigned.</p>
        )}
      </div>
    </div>
  );
};

export default EmployeeHome;
