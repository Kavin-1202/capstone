import React from "react";
import Header from "./Header";

const HomePage = () => {
  return (
    <div className="min-h-screen bg-gray-100">
      <Header />

      {/* Body Content */}
      <main className="p-8">
        <section className="max-w-4xl mx-auto bg-white p-6 rounded-lg shadow-md">
          <h2 className="text-2xl font-bold mb-4">Welcome to LearningHub</h2>
          <p className="mb-4">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus lacinia odio vitae vestibulum vestibulum. Cras venenatis euismod malesuada. Maecenas sit amet pretium urna. Vivamus venenatis velit nec neque ultricies, eget elementum magna tristique.
          </p>
          <p className="mb-4">
            Integer nec bibendum lacus. Suspendisse dictum enim sit amet libero malesuada feugiat. Praesent malesuada congue magna at finibus. Sed mauris dolor, placerat eget nibh et, molestie convallis est. Integer pellentesque, velit a consectetur elementum, nunc metus cursus purus, id scelerisque justo nulla ut mauris.
          </p>
          <p className="mb-4">
            Quisque vel volutpat eros, et maximus augue. Cras in nisi placerat, ullamcorper elit quis, ultricies orci. Fusce eleifend, arcu nec aliquet sollicitudin, risus sapien ultricies leo, non cursus ex urna a orci. Pellentesque finibus pharetra dolor sit amet condimentum.
          </p>
          <p>
            Suspendisse potenti. Pellentesque lobortis elit eu varius consectetur. Praesent imperdiet felis quis elit maximus, in malesuada turpis elementum. Proin dictum ligula vel nunc scelerisque, sit amet venenatis erat aliquam. Donec vitae tincidunt leo.
          </p>
        </section>
      </main>
    </div>
  );
};

export default HomePage;
