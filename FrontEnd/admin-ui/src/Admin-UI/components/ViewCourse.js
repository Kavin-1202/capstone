// ViewCourse.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';

const ViewCourse = () => {
  const { courseid } = useParams();
  const [course, setCourse] = useState(null);
  const [error, setError] = useState('');
  const [feedbacks, setFeedbacks] = useState([]);
  const [feedbackError, setFeedbackError] = useState('');
  const [showFeedbacks, setShowFeedbacks] = useState(false);
  const navigate = useNavigate();
  const [employeeUsernames, setEmployeeUsernames] = useState({});

  useEffect(() => {
    const fetchCourseData = async () => {
      try {
        const response = await axios.get(`http://localhost:9001/admin/courses/${courseid}`);
        setCourse(response.data);
      } catch (error) {
        setError('Error fetching course details.');
        console.error(error);
      }
    };

    fetchCourseData();
  }, [courseid]);

  const fetchFeedbacks = async () => {
    try {
      const response = await axios.get(`http://localhost:9001/admin/feedbacks/course/${courseid}`);
      setFeedbacks(response.data);
      setShowFeedbacks(true); // Show feedbacks after fetching
      fetchEmployeeUsernames(response.data);
    } catch (error) {
      setFeedbackError('Error fetching feedbacks.');
      console.error(error);
    }
  };
  // Function to fetch usernames based on employee IDs from feedbacks
  const fetchEmployeeUsernames = async (feedbacks) => {
    const usernames = {};
    await Promise.all(
      feedbacks.map(async (feedback) => {
        try {
          const employeeResponse = await axios.get(`http://localhost:9001/admin/employees/${feedback.employeeid}`);
          usernames[feedback.employeeid] = employeeResponse.data.username;
        } catch (error) {
          console.error(`Error fetching username for employee ID ${feedback.employeeid}`, error);
        }
      })
    );
    setEmployeeUsernames(usernames);
  };
  

  if (error) return <div className="text-red-500">{error}</div>;
  if (!course) return <div>Loading...</div>;

  // Function to render embedded YouTube video
  const renderYouTubeVideo = (url) => {
    const videoId = url.split('v=')[1].split('&')[0];
    return (
      <div className="mt-4">
        <iframe
          width="560"
          height="315"
          src={`https://www.youtube.com/embed/${videoId}`}
          title="YouTube video player"
          frameBorder="0"
          allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
          allowFullScreen
        ></iframe>
      </div>
    );
  };

  return (
    <div className="p-6">
      {/* Back Button */}
      <button
        onClick={() => navigate(-1)}
        className="bg-gray-500 text-white px-4 py-2 rounded mb-4 hover:bg-gray-600"
      >
        &larr; Back
      </button>

      <h1 className="text-2xl font-bold mb-4">{course.coursename}</h1>
      <p className="text-gray-700 mb-2"><strong>Description:</strong> {course.description}</p>
      <p className="text-gray-700 mb-4"><strong>Duration:</strong> {course.duration}</p>

      {/* Display resource links with embedded YouTube videos */}
      {course.resourcelinks && (
        <div>
          <h2 className="text-xl font-semibold mb-2">Resource Links:</h2>
          {course.resourcelinks.split(', ').map((link, index) => (
            <div key={index} className="mb-4">
              {link.includes('youtube.com') ? renderYouTubeVideo(link) : (
                <a href={link} className="text-blue-500" target="_blank" rel="noopener noreferrer">
                  {link}
                </a>
              )}
            </div>
          ))}
        </div>
      )}
      
      {/* Display other details */}
      <div>
        <h2 className="text-xl font-semibold mb-2">Other Links:</h2>
        {course.otherlinks && (
          <div>
            {course.otherlinks.split(', ').map((link, index) => (
              <a key={index} href={link} className="text-blue-500" target="_blank" rel="noopener noreferrer">
                {link}
              </a>
            ))}
          </div>
        )}
      </div>

      <div>
        <h2 className="text-xl font-semibold mb-2">Outcomes:</h2>
        <p>{course.outcomes}</p>
      </div>
      {/* Button to view feedbacks */}
      <div>
        <button
          type="button"
          onClick={fetchFeedbacks}
          className="bg-gray-500 text-white px-4 py-2 rounded mb-4 hover:bg-gray-600"
        >
          View Course Feedbacks
        </button>
      </div>

      {/* Display feedbacks if available */}
      {feedbackError && <div className="text-red-500">{feedbackError}</div>}
      {showFeedbacks && feedbacks.length > 0 && (
        <div className="mt-6">
          <h2 className="text-xl font-semibold mb-2">Feedbacks:</h2>
          <table className="min-w-full bg-white border">
            <thead>
              <tr>
                <th className="py-2 px-4 border">Feedback ID</th>
                <th className="py-2 px-4 border">Feedback</th>
                <th className="py-2 px-4 border">Rating</th>
                <th className="py-2 px-4 border">Submitted By</th>
              </tr>
            </thead>
            <tbody>
              {feedbacks.map((feedback) => (
                <tr key={feedback.feedbackId}>
                  <td className="py-2 px-4 border">{feedback.feedbackid}</td>
                  <td className="py-2 px-4 border">{feedback.comments}</td>
                  <td className="py-2 px-4 border">{feedback.rating}</td>
                  <td className="py-2 px-4 border">{employeeUsernames[feedback.employeeid] || feedback.employeeid}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
};

export default ViewCourse;
