import axios from 'axios';
import React from 'react';
import  './loginsignup.css' ;
import  { useState } from 'react';
import user from '../assets/user.jpg' ;
import password from '../assets/password.png';
import mail from '../assets/mail.webp';
import { useNavigate } from 'react-router-dom';
const Loginsignup = () => {   
  const [action,setAction] = useState("Login");
  //  action is initialized to "login" then using setaction function we can change state
  const [email,setemail]= useState("");
  const [password1,setpassword1]= useState("");
  const [password2,setpassword2]= useState("");
  //const [passwordmatcherror,setpasswordmatcherror]= useState("");
  const [firstname,setfirstname]= useState("");
  const [lastname,setlastname]= useState("");

   const navigate = useNavigate();


  //  const handleSignUp= (e) => {
  //   e.preventDefault();
  //   //prevent default form submission
  //   if(password1!==password2){
  //     //using !== for strict checking
  //     setpasswordmatcherror("Passwords do not match You drunk??");
  //     return;
  //   }
  //   console.log("Email:",email);
  //   console.log("password1:",password1);
  //   console.log("password2:",password2);
    

  //   setfirstname("");
  //   setlastname("") ;
  //   setemail("");
  //    setpassword1("");
  //    setpassword2("");
  //   setpasswordmatcherror("");
  //  };
const handlesignup = async (e) => {
  e.preventDefault();
  // Prevent default form submission
  const emailRegex = /\S+@\S+\.\S+/;
if (!emailRegex.test(email)) {
  alert("Enter a valid email address. You moron");
  return;
}

  // Validate password match
  if(!firstname){
    alert("Enter your first name moron");
    return;
  }
  if(!lastname){
    alert("Enter your lastt name moron");
    return;
  }if(!email){
    alert("Enter your emailid moron");
    return;
  }
  if(!password1){
    alert("Enter your password moron");
    return;
  }
  if(!password2){
    alert("Enter your password again moron");
    return;
  }
  if (password1 !== password2) {
    //setpasswordmatcherror("Passwords do not match what the helll man.");
    //  {passwordmatcherror && <p  className="error">{passwordmatcherror}</p>}
    //console.log("hello",passwordmatcherror)    ;   
    alert("passwords dont match man what the hell"); 

    // alert(passwordmatcherror);
    return;
  }

  try {
    // Create a FormData object to hold form data
    const formData = new FormData();
    formData.append('firstname', firstname);
    formData.append('lastname', lastname);
    formData.append('email', email);
    formData.append('password', password1);

    //Convert FormData to JSON
    const jsonObject = {};
    formData.forEach((value, key) => {
        jsonObject[key] = value;
    });

    // Make POST request to backend endpoint for signup
    // const response = await axios.post('http://localhost:9000/signup', formData);

    // Make POST request to backend endpoint for signup
    const response = await axios.post('http://localhost:9000/signup', jsonObject, {
        headers: {
            'Content-Type': 'application/json'
        }
    });

    // Handle successful signup (e.g., display success message, redirect to login page)
    console.log('Signup successful:', response.data);
     alert("User added succesfully hurray ");
     setAction("Login");
    // Clear form fields and error message
    clearFormFields();
  } catch (error) {
    // Handle signup error (e.g., display error message)
    
    console.error('Signup error:', error);
    clearFormFields();
    alert("errror sorry man");
  }
};

const handlelogin = async (e) => {
  e.preventDefault();
  // Prevent default form submission
  //  navigate('home');
  const emailRegex = /\S+@\S+\.\S+/;
if (!emailRegex.test(email)) {
  alert("Enter a valid email address Idiot.");
  return;
}
  if(!email){
    alert("Enter your emailid moron");
    return;
  }
  if(!password1){
    alert("Enter your password moron");
    return;
  }
  try {
     // Make POST request to backend endpoint for login
    // const response = await axios.post('http://localhost:9000/login', { email, password1 });
    const formData2 = new FormData();
    formData2.append("email", email);
    formData2.append("password", password1);
    const jsonObject2 = {};
    formData2.forEach((value, key) => {
        jsonObject2[key] = value;
    });
    const response2 = await axios.post('http://localhost:9000/login', jsonObject2, {
        headers: {
            'Content-Type': 'application/json'}
        });

   // Handle successful login (e.g., authenticate user, redirect to dashboard)
     console.log('Login successful:', response2.data);
     if(response2.data === "Logged In successfully"){
        alert("Login Successful :)");
        navigate('/home');
     }
     else if(response2.data === "Invalid Credentials !!"){
      alert("Invalid credentials. Please try again.");
     }
     else if (response2.data === "User does not exist !!") {
      alert("User does not exist. Please sign up first");
      setAction("Sign Up");
     }
     else{
      alert("login unsuccessful, Stay away hacker !!!!! Ill call the police"); 
     }
    }catch (error) {
     // Handle login error (e.g., display error message for invalid credentials)
     console.error('Login error:', error);
     clearFormFields();
     alert("ERROR!!!!! enter correctly or I'll report you to the police");
   }
};

const clearFormFields = () => {
  setfirstname("");
  setlastname("");
  setemail("");
  setpassword1("");
  setpassword2("");
  //setpasswordmatcherror("");
};

  return (
    <div  className="container">
      <div className="log-background-img"></div>
      {/* <form {action==="Login" ? onSubmit=handlelogin :  onSubmit=handlesignup}>  this gives error */}
    {/* <form onSubmit={action === "Login" ? handlelogin : handlesignup}> */}
      <div className="header">
        <div className="text">{action} </div>
        <div className="underline"></div>
      </div>
      <div className="inputs">
        {action=="Login"? <div></div>: <div className="input" id='inputbig'>
      <img src={user} alt="" />
      <div className="name-inputs">
        <input type="text" placeholder='Enter your first name human' value={firstname} onChange={(e)=> setfirstname(e.target.value)}/>
        <input type="text" placeholder='Enter your last name human'value={lastname} onChange={(e)=> setlastname(e.target.value)} />
      </div>
    </div> }
        <div className="input">
          <img src={mail} alt=""/>
          <input type="email" placeholder='Email-id' value={email} onChange={(e)=> setemail(e.target.value)} />
        </div>
      {action=="Sign Up"?  <div className="input" id='inputbig'>
          <img src={password} alt="" />
          <div className="name-inputs">
          <input type="password" placeholder='Enter Password' value={password1} onChange={(e)=> setpassword1(e.target.value)}/>
          <input type="password" placeholder='Retype Password'value={password2} onChange={(e)=> setpassword2(e.target.value)}/>
</div>
        </div>  : <div className="input">
          <img src={password} alt=""/>
          <input type="password" placeholder='Enter Password'value={password1} onChange={(e)=> setpassword1(e.target.value)} />
        </div>
        }
        </div>
      {action=="Sign Up"?<div></div>:  <div className="forgot-password">Lost Password??<span> No worries CLICK HERE</span> </div>}
          {/* Render error message if passwords don't match */}
  {/* {passwordmatcherror && <p  className="error">{passwordmatcherror}</p>} */}
      {/* if passwordmatcherror is not empty then it with and returns true */}
      <div className="submit-container">
        <div className={action=="Sign Up"?"submit gray":"submit"} onClick={action === "Login" ? handlelogin : () => setAction("Login")}>Login</div>
        <div className={action=="Login"?"submit gray":"submit"} onClick={action === "Sign Up" ? handlesignup : () => setAction("Sign Up")}>Sign Up</div>
      {/* if(passwordmatcherror) { 
      alert(passwordmatcherror);
       clearFormFields();
       } */}
    {/* {passwordmatcherror && <p  className="error">{passwordmatcherror}</p>} */}
{/* console.log("hello",passwordmatcherror)    ;    
alert(passwordmatcherror); */}
{/* we are using react state and onclick arrow function to change state */}
    {/* <button type='submit'>Submit</button> */}
      </div>
      {/* </form> */}
    </div>
)
}  
export default Loginsignup;