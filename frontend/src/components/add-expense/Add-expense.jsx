 import React from 'react'
  import axios from 'axios';
  import './add-expense.css' ;
 import  { useState } from 'react';
  import { useNavigate } from 'react-router-dom';
import sad from '../assets/sad.png';
  

// // // const [category,setcategory]= useState("");
// // // const [date,setdate]= useState("");
// // // const [ammount,setammount]= useState("");
// // // const [description,setdescription]= useState("");
// // // const navigate = useNavigate();
//  const Addexpense = () => {
// //   const [category,setcategory]= useState("");
// //   const [date,setdate]= useState("");
// //   const [ammount,setammount]= useState("");
// //   const [description,setdescription]= useState("");
// //   const navigate = useNavigate();

// //     // handleaddexpense(){
      
// //     // }


//    return (

// //     // const [category,setcategory]= useState("");
// //     // const [date,setdate]= useState("");
// //     // const [ammount,setammount]= useState("");
// //     // const [description,setdescription]= useState("");
// //     // const navigate = useNavigate();



//      <div className='add-container'>
//        <div className="add-inputs">
//          {/* <input type="text" placeholder='Enter category of expense??' onChange={(e)=> setcategory(e.target.value)}/>
//          <input type="date" placeholder='Enter Date of expense??'  onChange={(e)=> setdate(e.target.value)}/>
//          <input type="text" placeholder='Enter ammount of expense??' onChange={(e)=> setammount(e.target.value)}/>
//          <input type="text" placeholder='Enter description of expense??'onChange={(e)=> setdescription(e.target.value)} /> */}
//          <input type="text" placeholder='Enter category of expense??' />
//          <input type="date" placeholder='Enter Date of expense??'/>
//          <input type="text" placeholder='Enter ammount of expense??' />
//          <input type="text" placeholder='Enter description of expense??'/>
//            <div className="submit" >
//              submit this expense???
//            </div>
//        </div>

      
//      </div>
//    )
//  }

//  export default Addexpense;




const Addexpense = () => {
  const navigate = useNavigate();
  
  
  const [category,setcategory]= useState("");
  const [date,setdate]= useState("");
  const [amount,setamount]= useState("");
  const [description,setdescription]= useState("");





  const handleAddExpense = async () => {
    // Gather data from input fields
    const category = document.querySelector('input[name="category"]').value;
    const date = document.querySelector('input[name="date"]').value;
    const amount = document.querySelector('input[name="amount"]').value;
    const description = document.querySelector('input[name="description"]').value;

    // Create payload object
   

    // const expenseData = {
    //   category,
    //   date,
    //   amount,
    //   description
    // };
    
    try {
      // Send expense data to backend
      const formData = new FormData();
    formData.append('category', category);
    formData.append('date', date);
    formData.append('amount', amount);
    formData.append('description', description);

    //Convert FormData to JSON
    const jsonObject = {};
    formData.forEach((value, key) => {
        jsonObject[key] = value;
    });

    // Make POST request to backend endpoint for signup
    // const response = await axios.post('http://localhost:9000/signup', formData);

    // Make POST request to backend endpoint for signup
    const response = await axios.post('http://localhost:9000/addExpense', jsonObject, {
        headers: {
            'Content-Type': 'application/json'
        }
    });
      console.log('Expense added:', response.data);
      // Redirect to a new page (or navigate to another route)
      //navigate('/expenses');
      clearFormFields();
      alert("Expense added successfully!!");
    }
     catch (error) {
      console.error('Error adding expense:', error);
      // Handle error (e.g., display error message)
    }
  };

  const clearFormFields = () => {
    setamount("");
    setdate("");
    setdescription("");
    setcategory("");
  
  };

  return (
    // <div className="Addexpensemaincontainer">
  // <span className="navcontainer">
  //   <span className='navspan'>
  //     <div className="navtext">
  //       GO TO HOMEPAGE?
  //     </div>
  //   </span>
  //   <span className='navspan'>
  //   <div className="navtext">
  //     Bored? Then Logout Man 
  //     <span className='spanimg'></span>
  //   </div>

  //   </span>
  // </span>
     <div className="mainaddcontainer">
      <div className="add-background-image"></div>
         <div className="addparentnavbar">
         <div className="navcontainer">
     <span className='navspan'>
       <span className="navtext" onClick={() => navigate("/home")}>
         GO TO HOMEPAGE?
       </span>
     </span>
     </div>
     <div className="navcontainer">
     <span className='navspan'>
     <span className="navtext" onClick={() => navigate("/")}>
       Bored?  Logout HUMAN 
       
       <img className='spanimg' src={sad} alt=""/>
       
     </span>

     </span>
   </div>
         </div>


    <div className='add-container'>
       {/* <span className="navcontainer">
    <span className='navspan'>
      <div className="navtext">
        GO TO HOMEPAGE?
      </div>
    </span>
    <span className='navspan'>
    <div className="navtext">
      Bored? Then Logout Man 
      <span className='spanimg'></span>
    </div>

    </span>
  </span> */}
      <p className='before-inp'>
        ENTER EXPENSE DETAILS USER!!
      </p>

      <div className="add-inputs">
        <input type="text" name="category" placeholder='Enter category of expense' onChange={(e)=> setcategory(e.target.value)} />
        <input type="date" name="date" placeholder='Enter Date of expense' onChange={(e)=> setdate(e.target.value)} />
        <input type="text" name="amount" placeholder='Enter amount of expense'  onChange={(e)=> setamount(e.target.value)}/>
        <input type="text" name="description" placeholder='Enter description of expense' onChange={(e)=> setdescription(e.target.value)}/>
        <div className="submit" onClick={handleAddExpense}  >
          <div className="add-text">Submit this expense</div>
        </div>
      </div>
    </div>
    </div>
  );
};

export default Addexpense;
