import { Link } from "react-router-dom";
import "./Navbar.css";

export default function Navbar() {
  return (
    <nav className="navbar">
      <div className="navbar-logo">🎮 Gaming Club</div>
      <ul className="navbar-links">
        <li><Link to="/">Home</Link></li>
        <li><Link to="/profile">Profile</Link></li>
        <li><Link to="/games">Games</Link></li>
        <li><Link to="/recharge">Recharge</Link></li>
        <li><Link to="/login">Login</Link></li>
      </ul>
    </nav>
  );
}
