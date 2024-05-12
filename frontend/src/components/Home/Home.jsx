import React from 'react';

import akshay from '../assets/akshay.jpeg';
import amitabh from '../assets/amitabh.jpeg';
import middle from '../assets/middle.jpeg';
import nana from '../assets/nana.webp';
import amir from '../assets/amir.jpg';
import sharukh from '../assets/sharukh.jpg';




import { useNavigate } from 'react-router-dom';
import styles from "./home.css";
import { Link } from 'react-router-dom';
const Home = () => {
  const navigate = useNavigate();

  // return (
  //   <div className="parent2">
  //     <div className="container2">
  //       Welcome user please select what you want to do ????
  //       <div className="submit-container2">
  //         {/* Use arrow functions to call navigate with the desired route */}
  //         <div className="submit2" onClick={() => navigate("/Add-expense")}>Add Expense</div>
  //         <div className="submit2" onClick={() => navigate('/show-expense')}>Show Expense</div>
  //         <div className="submit2">Logout</div>
  //       </div>
  //     </div>
  //   </div>
  // );
  return (
    <div className="home-container">
    <nav className="navbar">
      <ul className="navbar-nav">
        <li className="nav-item">
          <Link to="/add-expense" className="nav-link">
            Add Expense
          </Link>
        </li>
        <li className="nav-item">
          <Link to="/show-expense" className="nav-link">
            Show Expense
          </Link>
        </li>
        <li className="nav-item">
          <Link to="/" className="nav-link">
            Logout
          </Link>
        </li>
      </ul>
    </nav>
     <div className="sections-container">
     <div className="section left-section">
       {/* First section content */}
       {/* <img src={akshay} alt="Image 1" />
      
      <img src={amitabh} alt="Image 2" />
      <img src={sharukh} alt="Image 2" />
       */}
       <h2>MOKA: Revolutionizing Expense Tracking</h2>
    
      </div> 
     
     <div className="section middle-section">
       {/* Second section content */}
      
       
        {/* <p>
          MOKA, the brainchild of Mohak and Kalyani, stands as a beacon of innovation in the realm of expense tracking applications. With meticulous attention to detail and a passion for user-centric design, they have crafted a seamless platform that redefines how individuals manage their finances. MOKA offers a holistic approach to expense tracking, empowering users to effortlessly monitor their expenditures, set budgets, and achieve financial goals with ease.
        </p> */}
              {/* <p>
              One of MOKA's standout features is its intuitive interface, designed to streamline the expense tracking process. Users can categorize expenses, add detailed descriptions, and attach receipts, ensuring a comprehensive overview of their spending habits. The app's real-time syncing capabilities enable users to access their financial data across multiple devices, providing unparalleled convenience and accessibility.
              </p> */}

          {/* <p>
          MOKA also prioritizes data security, employing robust encryption protocols to safeguard sensitive information. With bank-level security measures in place, users can trust MOKA to protect their financial data and provide peace of mind in an increasingly digital landscape.
            </p>    
            <p>
            Beyond its practical functionality, MOKA fosters a sense of financial literacy and accountability. Through personalized insights and visualizations, users gain valuable insights into their spending patterns, enabling informed decision-making and fostering healthier financial habits. Whether tracking daily expenses, planning for the future, or simply gaining a better understanding of their financial landscape, MOKA empowers users to take control of their financial well-being.
            </p>
            <p>
            In essence, MOKA represents a transformative tool in the journey towards financial empowerment. By combining cutting-edge technology with a user-centric approach, Mohak and Kalyani have created an indispensable companion for individuals seeking to achieve their financial goals and unlock a brighter financial future.        write this in my middle section  and apply a beautiful css on this text
            </p> */}

        {/* Include the rest of the text here */}  


     </div>
     <div className="section right-section">
    
     {/* <img src={middle} alt="Image 2" />
     <img src={nana} alt="Image 2" />
     <img src={amir} alt="Image 2" />
      */}


     </div>
      


       {/* Third section content */}
     </div>
   
 </div>
 
  );
};

export default Home;