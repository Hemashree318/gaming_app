import { useEffect, useState } from "react";
import { getRecharges, rechargeMember, getMembers } from "../api/api";

export default function Recharge() {
  const [recharges, setRecharges] = useState([]);
  const [members, setMembers] = useState([]);
  const [amount, setAmount] = useState(0);
  const [selectedMember, setSelectedMember] = useState("");

  useEffect(() => {
    getRecharges().then(res => setRecharges(res.data));
    getMembers().then(res => setMembers(res.data));
  }, []);

  const handleRecharge = () => {
    rechargeMember({ memberId: selectedMember, amount: parseFloat(amount) })
      .then(res => setRecharges([...recharges, res.data]))
      .catch(err => alert("Error: " + err.response?.data || err.message));
  };

  return (
    <div>
      <h1>Recharges</h1>
      <select value={selectedMember} onChange={(e) => setSelectedMember(e.target.value)}>
        <option value="">Select Member</option>
        {members.map(m => (
          <option key={m.id} value={m.id}>{m.name}</option>
        ))}
      </select>
      <input
        type="number"
        placeholder="Recharge amount"
        value={amount}
        onChange={(e) => setAmount(e.target.value)}
      />
      <button onClick={handleRecharge} disabled={!selectedMember}>
        Recharge
      </button>

      <ul>
        {recharges.map(r => (
          <li key={r.id}>
            Member ID: {r.memberId}, Amount: {r.amount}
          </li>
        ))}
      </ul>
    </div>
  );
}
