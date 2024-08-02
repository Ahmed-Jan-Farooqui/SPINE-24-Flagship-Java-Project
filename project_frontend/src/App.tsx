import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LogInPage from "./components/LogIn/LogInPage";
import SignupPage from "./components/SignUp/SignupPage";
import "./App.css";
import HomePage from "./components/HomePage/HomePage";
import MyProfile from "./components/MyProfile/MyProfile";

function App() {
  return (
    <div>
      <Router>
        <Routes>
          <Route path="/login" element={<LogInPage />} />
          <Route path="/" element={<HomePage />} />
          <Route path="/signup" element={<SignupPage />} />
          <Route path="/profile" element={<MyProfile />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
