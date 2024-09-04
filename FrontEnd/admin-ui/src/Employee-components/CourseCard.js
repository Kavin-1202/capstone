import React from 'react';

const CourseCard = ({ course, onStart }) => {
  return (
    <div className="flex-shrink-0 w-64 bg-white border rounded-lg shadow p-4">
      <h3 className="text-lg font-semibold mb-2">{course.coursename}</h3>
      <p className="text-sm text-gray-600 mb-2">{course.description}</p>
      {/* Include more course details if needed */}
      <button
        className="mt-2 bg-blue-500 text-white py-1 px-4 rounded"
        onClick={onStart}
      >
        Start
      </button>
    </div>
  );
};

export default CourseCard;
