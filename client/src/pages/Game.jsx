import { useEffect, useState } from "react";
import { getGames, createGame, registerMemberForGame, getMembers } from "../api/api";

export default function Game() {
  const [games, setGames] = useState([]);
  const [members, setMembers] = useState([]);
  const [name, setName] = useState("");
  const [price, setPrice] = useState(0);
  const [description, setDescription] = useState("");

  useEffect(() => {
    getGames().then(res => setGames(res.data));
    getMembers().then(res => setMembers(res.data));
  }, []);

  const handleCreate = () => {
    createGame({ name, price, description })
      .then(res => setGames([...games, res.data]))
      .catch(err => alert("Error: " + err.response?.data || err.message));
  };

  const handleRegister = (gameId, memberId) => {
    registerMemberForGame(gameId, memberId)
      .then(res => alert(res.data))
      .catch(err => alert("Error: " + err.response?.data || err.message));
  };

  return (
    <div>
      <h1>Games</h1>
      <input
        type="text"
        placeholder="Game name"
        value={name}
        onChange={(e) => setName(e.target.value)}
      />
      <input
        type="number"
        placeholder="Entry fee"
        value={price}
        onChange={(e) => setPrice(e.target.value)}
      />
      <input
        type="text"
        placeholder="Description"
        value={description}
        onChange={(e) => setDescription(e.target.value)}
      />
      <button onClick={handleCreate}>Add Game</button>

      <ul>
        {games.map(g => (
          <li key={g.id}>
            {g.name} - Fee: {g.price}
            <button
              onClick={() => handleRegister(g.id, members[0]?.id)}
              disabled={!members.length}
            >
              Register First Member
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}
