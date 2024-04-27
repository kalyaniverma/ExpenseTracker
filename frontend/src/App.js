// import logo from './logo.svg';
// import './App.css';
// import Loginsignup from './components/loginsignup/Loginsignup';
// import Home from './components/Home/Home';
// import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

// function App() {
//   return (
//     <div >
//       <Loginsignup/> 
//      {/* <Home/> */}
//      {/* our component is now mounted */}

//      <Routes>

//        {/* <Route path='' element = {<Loginsignup/>}/>  */}
//       <Route path='home' element = {<Home/>}/>
    
//     </Routes> 

//     </div>
//   );
// }

// export default App;

import logo from './logo.svg';
import './App.css';
import Loginsignup from './components/loginsignup/Loginsignup';
import Home from './components/Home/Home';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

function App() {
  return (
    
      <div>
        <Routes>
          <Route path="/" element={<Loginsignup />} />
          <Route path="/home" element={<Home />} />
          <Route path="/add-expense" element={<add-expense />} />
          <Route path="/show-expense" element={<show-expense />} />

        </Routes>
      </div>
    
  );
}

export default App;