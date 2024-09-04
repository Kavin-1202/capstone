// ViewCourse.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';

const ViewCourse = () => {
  const { courseid } = useParams();
  const [course, setCourse] = useState(null);
  const [error, setError] = useState('');
  const navigate = useNavigate();

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
      <div>
        <button type='button' className="bg-gray-500 text-white px-4 py-2 rounded mb-4 hover:bg-gray-600">
          View Course Feedbacks</button>
      </div>
    </div>
  );
};

export default ViewCourse;
