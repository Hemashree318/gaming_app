
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Profile from './pages/Profile';
import Games from './pages/Game';
import Recharges from './pages/Recharge';
import Home from './pages/Home';
import Login from './pages/Login';
function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home/>} />
        <Route path="/login" element={<Login />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/games" element={<Games />} />
        <Route path="/recharges" element={<Recharges />} />
      </Routes>
    </Router>
  );
}

export default App;