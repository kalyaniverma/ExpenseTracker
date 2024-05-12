import React from 'react';
import { useNavigate } from 'react-router-dom';
import './start.css';

const Start = () => {
  const navigate = useNavigate();

  return (
    <div id='start-main'>
      <div className="background-image"></div>
      <div className="overlay"></div>
      <div className="content">
        <h1 id='start-heading'>
          MOKA: Revolutionizing Expense tracking
        </h1>
        <p id='start-info' >
          Welcome to MOKA! A revolutionary platform for effortless expense tracking.
        </p>
        <button id='login-button' onClick={() => navigate("/loginsignup")}>LOGIN</button>
      </div>
    </div>
  );
};

export default Start;
