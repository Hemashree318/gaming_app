import { Link } from "react-router-dom";
import "./Home.css";
import Navbar from "../components/Navbar";

export default function Home() {
  return (
    <>
    <Navbar />
    <div className="home-container">
      <div className="home-hero">
        <h1>Welcome to Gaming Club 🎮</h1>
        <p>Play. Recharge. Compete. All in one place.</p>
        <div className="home-buttons">
          <Link to="/profile" className="btn">Manage Profile</Link>
          <Link to="/games" className="btn">Explore Games</Link>
          <Link to="/recharge" className="btn">Recharge Wallet</Link>
        </div>
      </div>
    </div>
    </>
  );
}
