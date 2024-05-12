import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import sad from '../assets/sad.png';
import './show-expense.css';

const ShowExpense = () => {
  const navigate = useNavigate();
  const [expenses, setExpenses] = useState([]);
  const [no, setNo] = useState("1");
  const [categoryInput,setCategoryInput]=useState("");
  const [valid,setvalid]=useState("false");
  useEffect(() => {
    const fetchExpenses = async () => {
      try {
        let response;
        if (no == "1") {
          response = await fetch('http://localhost:9000/listExpenses');
        }else if (no == "2" && valid=='true') {
           response = await fetch(`http://localhost:9000/expensesByCategory?category=${encodeURIComponent(categoryInput)}`);
        } else if (no == "3"){ ;
        }
         else if (no == "3") {
          response = await fetch('http://localhost:9000/expenses/filterByMonth?month=2024-01');
        } else if (no == "4") {
          response = await fetch('http://localhost:9000/highest-amount');
        } else if (no == "5") {
          response = await fetch('http://localhost:9000/lowest-amount');
        } else if (no == "6") {
          //http://localhost:9000/editExpense/1
        }
        if (!response.ok) {
          throw new Error('Failed to fetch expenses');
        }
        const data = await response.json();
        setExpenses(data);
      } catch (error) {
        console.error('Error fetching expenses:', error);
      }
    };

    fetchExpenses();
  }, [no]);

  // Calculate total expenses
  const totalExpenses = expenses.reduce((total, expense) => total + expense.amount, 0);

  const handleEditExpense = (expense) => {
    // Add functionality to edit an expense here
    console.log('Edit expense:', expense);
  };

  return (
    <>
      <div className="mainaddcontainer">
        <div className="show-background-image"></div>
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
                Bored? Logout HUMAN 
                <img className='spanimg' src={sad} alt=""/>
              </span>
            </span>
          </div>
        </div>

        <div className='expense-container'>

          {no == "1" && (
            <h2 className='expense-heading'>All Expenses</h2>
          )}
          {no == "2" && (
            <h2 className='expense-heading'>Expenses by category</h2>
          )}
          {no == "3" && (
            <h2 className='expense-heading'>Expenses by Date</h2>
          )}
          {no == "4" && (
            <h2 className='expense-heading'>Expenses by Highest Amount</h2>
          )}
          {no == "5" && (
            <h2 className='expense-heading'>Expenses by Lowest Amount</h2>
          )}
          {no == "6" && (
            <h2 className='expense-heading'>Edit or delete an expense</h2>
          )}
          {expenses.length === 0 ? (
            <p>No expenses found.</p>
          ) : (
            <>
              <table className='expense-table'>
                <thead>
                  <tr>
                    <th>Category</th>
                    <th>Date</th>
                    <th>Amount</th>
                    <th>Description</th>
                    {no === "6" && (
                      <th>Action</th>
                    )}
                  </tr>
                </thead>
                <tbody>
                  {expenses.map(expense => (
                    <tr key={expense.id} className='expense-row'>
                      <td>{expense.category}</td>
                      <td>{expense.date}</td>
                      <td>Rs {expense.amount}</td>
                      <td>{expense.description}</td>
                      {no == "6" && (
                        <td>
                          <button onClick={() => handleEditExpense(expense)}>Edit</button>
                          <button>Delete</button>
                        </td>
                      )}
                    </tr>
                  ))}
                </tbody>
              </table>
              <span className='expense-functions'>
                <div className="exp-heading">
                  Select what you want to do??
                </div>
                {no!='1'?
              <div className='function' onClick={() => setNo("1")}>

                1. Show all expenses??
              </div>:<div  id='Func-select' onClick={() => setNo("1")}>

1. Show all expenses??
</div>}

{no!='2'?
              <div className='function' onClick={() => setNo("2")}>

                2. Show expenses by category??
              </div>:<div  id='Func-select' onClick={() => setNo("2")}>

2. Show expenses by category??


 <div>
 <input
   type="text"
   placeholder="Enter category"
   value={categoryInput}
   onChange={(e) => setCategoryInput(e.target.value)}
 />
 <button onClick={() => {
  setNo("2");
  setvalid("true");
}}>Search</button>
</div> </div>
}


{no!='3'?
              <div className='function' onClick={() => setNo("3")}>

                3. Show expenses by Date??
              </div>:<div  id='Func-select' onClick={() => setNo("3")}>

3. Show expenses by Date??
</div>}

{no!='4'?
              <div className='function' onClick={() => setNo("4")}>

                4. Show expenses by Highest Amount??
              </div>:<div  id='Func-select' onClick={() => setNo("4")}>

4. Show expenses by Highest Amount??
</div>}

{no!='5'?
              <div className='function' onClick={() => setNo("5")}>

                5. Show expenses by Lowest Amount??
              </div>:<div  id='Func-select' onClick={() => setNo("5")}>

5. Show expenses by Lowest Amount??
</div>}

{no!='6'?
              <div className='function' onClick={() => setNo("6")}>

                6. Edit/Remove an expense??
              </div>:<div  id='Func-select' onClick={() => setNo("6")}>

6. Edit/Remove an expense??
</div>}

                
              </span>
              <div className="total-expenses">
                <p>Total Expenses: Rs {totalExpenses}</p>
              </div>
            </>
          )}
        </div>
      </div>
    </>
  );
};

export default ShowExpense
