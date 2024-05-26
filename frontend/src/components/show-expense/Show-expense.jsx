import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import sad from '../assets/sad.png';
import './show-expense.css';

const ShowExpense = () => {
  const navigate = useNavigate();
  const [expenses, setExpenses] = useState([]);
  const [no, setNo] = useState("1");
  const [categoryInput, setCategoryInput] = useState("family");
  const [category2, setCategoryInput2] = useState("family");

  const [isEditing, setIsEditing] = useState(false);
  const [currentExpense, setCurrentExpense] = useState({
    id: '',
    category: '',
    date: '',
    amount: '',
    description: ''
  });

  useEffect(() => {
    fetchExpenses();
  }, [no, categoryInput]);

  const fetchExpenses = async () => {
    try {
      let url = '';
      switch (no) {
        case "1":
          url = 'http://localhost:9000/listExpenses';
          break;
        case "2":
          if (categoryInput !== '') {
            url = `http://localhost:9000/expenses?category=$categoryInput`;
          } else {
            alert("Please enter a category to search.");
            return;
          }
          break;
        case "3":
          url = 'http://localhost:9000/expenses/filterByMonth?month=2024-01';
          break;
        case "4":
          url = 'http://localhost:9000/highest-amount';
          break;
        case "5":
          url = 'http://localhost:9000/lowest-amount';
          break;
        default:
          url = 'http://localhost:9000/listExpenses';
          break;
      }
  
      const response = await fetch(url);
  
      if (!response.ok) {
        throw new Error('Failed to fetch expenses');
      }
  
      const data = await response.json();
      setExpenses(data);
    } catch (error) {
      console.error('Error fetching expenses:', error);
    }
  };
  

  let totalExpenses = expenses.reduce((total, expense) => total + expense.amount, 0);

  const handleEditExpense = (expense) => {
    setCurrentExpense(expense);
    setIsEditing(true);
  };

  const handleDeleteExpense = async (expenseId) => {
    try {
      const response = await fetch(`http://localhost:9000/deleteExpense?id=${expenseId}`, {
        method: 'DELETE',
      });

      if (!response.ok) {
        throw new Error('Failed to delete expense');
      }

      setExpenses(expenses.filter(expense => expense.id !== expenseId));
    } catch (error) {
      console.error('Error deleting expense:', error);
    }
  };

  const handleUpdateExpense = async (event) => {
    event.preventDefault();

    try {
      const response = await fetch(`http://localhost:9000/editExpense/${currentExpense.id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(currentExpense),
      });

      if (!response.ok) {
        throw new Error('Failed to update expense');
      }

      setExpenses(expenses.map(expense =>
        expense.id === currentExpense.id ? currentExpense : expense
      ));
      setIsEditing(false);
    } catch (error) {
      console.error('Error updating expense:', error);
    }
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
          {no === "1" && (
            <h2 className='expense-heading'>All Expenses</h2>
          )}
          {no === "2" &&(
            <h2 className='expense-heading'>Expenses by category: {categoryInput}</h2>
          )}
          {no === "3" && (
            <h2 className='expense-heading'>Expenses by Date</h2>
          )}
          {no === "4" && (
            <h2 className='expense-heading'>Expenses by Highest Amount</h2>
          )}
          {no === "5" && (
            <h2 className='expense-heading'>Expenses by Lowest Amount</h2>
          )}
          {no === "6" && (
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
                      {no === "6" && (
                        <td>
                          <button onClick={() => handleEditExpense(expense)}>Edit</button>
                          <button onClick={() => handleDeleteExpense(expense.id)}>Delete</button>
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
                {no !== '1' ? (
                  <div className='function' onClick={() => setNo("1")}>
                    1. Show all expenses??
                  </div>
                ) : (
                  <div id='Func-select' onClick={() => setNo("1")}>
                    1. Show all expenses??
                  </div>
                )}

                {no !== '2' ? (
                  <div className='function' onClick={() => setNo("2")}>
                    2. Show expenses by category??
                  </div>
                ) : (
                  <div id='Func-select' onClick={() => setNo("2")}>
                    2. Show expenses by category??
                    <div>
                      <input
                        type="text"
                        placeholder="Enter category"
                        value={category2}
                        onChange={(e) => setCategoryInput2(e.target.value)}
                      />
                      <button onClick={() => setCategoryInput(category2)}>Search</button>
                    </div>
                  </div>
                )}

                {no !== '3' ? (
                  <div className='function' onClick={() => setNo("3")}>
                    3. Show expenses by Date??
                  </div>
                ) : (
                  <div id='Func-select' onClick={() => setNo("3")}>
                    3. Show expenses by Date??
                  </div>
                )}

                {no !== '4' ? (
                  <div className='function' onClick={() => setNo("4")}>
                    4. Show expenses by Highest Amount??
                  </div>
                ) : (
                  <div id='Func-select' onClick={() => setNo("4")}>
                    4. Show expenses by Highest Amount??
                  </div>
                )}

                {no !== '5' ? (
                  <div className='function' onClick={() => setNo("5")}>
                    5. Show expenses by Lowest Amount??
                  </div>
                ) : (
                  <div id='Func-select' onClick={() => setNo("5")}>
                    5. Show expenses by Lowest Amount??
                  </div>
                )}

                {no !== '6' ? (
                  <div className='function' onClick={() => setNo("6")}>
                    6. Edit or delete an expense??
                  </div>
                ) : (
                  <div id='Func-select' onClick={() => setNo("6")}>
                    6. Edit or delete an expense??
                  </div>
                )}
              </span>
              <div className="total-expenses">
                <p>Total Expenses: Rs {totalExpenses}</p>
              </div>

              {isEditing && (
                <div className='edit-form-container'>
                  <form className='edit-form' onSubmit={handleUpdateExpense}>
                    <h2>Edit Expense</h2>
                    <label>
                      Category:
                      <input
                        type="text"
                        value={currentExpense.category}
                        onChange={(e) => setCurrentExpense({ ...currentExpense, category: e.target.value })}
                      />
                    </label>
                    <label>
                      Date:
                      <input
                        type="date"
                        value={currentExpense.date}
                        onChange={(e) => setCurrentExpense({ ...currentExpense, date: e.target.value })}
                      />
                    </label>
                    <label>
                      Amount:
                      <input
                        type="number"
                        value={currentExpense.amount}
                        onChange={(e) => setCurrentExpense({ ...currentExpense, amount: e.target.value })}
                      />
                    </label>
                    <label>
                      Description:
                      <input
                        type="text"
                        value={currentExpense.description}
                        onChange={(e) => setCurrentExpense({ ...currentExpense, description: e.target.value })}
                      />
                    </label>
                    <button type="submit">Update</button>
                    <button type="button" onClick={() => setIsEditing(false)}>Cancel</button>
                  </form>
                </div>
              )}
            </>
          )}
        </div>
      </div>
    </>
  );
};

export default ShowExpense;
