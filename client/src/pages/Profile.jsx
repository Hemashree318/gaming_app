import { useEffect, useState } from "react";
import { getMembers, createMember } from "../api/api";

export default function Profile() {
  const [members, setMembers] = useState([]);
  const [name, setName] = useState("");
  const [balance, setBalance] = useState(0);

  useEffect(() => {
    getMembers().then(res => setMembers(res.data));
  }, []);

  const handleCreate = () => {
    createMember({ name, balance })
      .then(res => setMembers([...members, res.data]))
      .catch(err => alert("Error: " + err.response?.data || err.message));
  };

  return (
    <div>
      <h1>Profiles</h1>
      <input
        type="text"
        placeholder="Enter name"
        value={name}
        onChange={(e) => setName(e.target.value)}
      />
      <input
        type="number"
        placeholder="Enter balance"
        value={balance}
        onChange={(e) => setBalance(e.target.value)}
      />
      <button onClick={handleCreate}>Add Member</button>

      <ul>
        {members.map(m => (
          <li key={m.id}>{m.name} - Balance: {m.balance}</li>
        ))}
      </ul>
    </div>
  );
}
