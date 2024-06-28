import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LogInPage from "./components/LogIn/LogInPage";
import SignupPage from "./components/SignUp/SignupPage";
import "./App.css";

function App() {
  return (
    <div>
      <Router>
        <Routes>
          <Route path="/login" element={<LogInPage />} />
          <Route path="/" element={<SignupPage />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
