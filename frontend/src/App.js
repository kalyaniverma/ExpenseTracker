import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

import Loginsignup from './components/loginsignup/Loginsignup';
import Home from './components/Home/Home';
import Addexpense from './components/add-expense/Add-expense';
import ShowExpense from './components/show-expense/Show-expense';
import Start from './components/start/Start';

function App() {
  return (
      <div>
        <Routes>
        <Route path="/" element={<Start />} />

          <Route path="/loginsignup" element={<Loginsignup />} />
          <Route path="/home" element={<Home />} />
          <Route path="/Add-expense" element={<Addexpense />} />
          <Route path="/show-expense" element={<ShowExpense />} />
        </Routes>
      </div>
  );
}

export default App;