import {BrowserRouter, Routes, Route } from 'react-router-dom'

import Admin from './pages/admin/Admin';
import Login from './pages/login/Login';
import Register from './pages/register/Register';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/login' element={<Login />} />
        <Route path='/register' element={<Register />} />
        <Route path='*' element={<Admin />}/>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
