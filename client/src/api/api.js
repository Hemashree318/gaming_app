import axios from "axios";

// ✅ Base API instance
const api = axios.create({
  baseURL: "http://localhost:8080/api", // change to your Render URL in prod
});

// ==================== MEMBERS ====================

// Get all members
export const getMembers = () => api.get("/members");

// Create a new member
export const createMember = (member) => api.post("/members", member);

// Get member by ID
export const getMemberById = (id) => api.get(`/members/${id}`);


// ==================== RECHARGES ====================

// Recharge a member
export const rechargeMember = (data) => api.post("/recharges", data);

// Get all recharges
export const getRecharges = () => api.get("/recharges");

// Get recharge by ID
export const getRechargeById = (id) => api.get(`/recharges/${id}`);


// ==================== GAMES ====================

// Get all games
export const getGames = () => api.get("/games");

// Create a new game
export const createGame = (game) => api.post("/games", game);

// Get game by ID
export const getGameById = (id) => api.get(`/games/${id}`);

// Register a member for a game (deduct entry fee)
export const registerMemberForGame = (gameId, memberId) =>
  api.post(`/games/${gameId}/register/${memberId}`);
