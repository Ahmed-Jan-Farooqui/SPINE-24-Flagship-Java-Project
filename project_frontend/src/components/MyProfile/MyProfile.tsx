import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import {
  TextField,
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
} from "@mui/material";
import axios from "axios";
import "./MyProfile.css";

const MyProfile = () => {
  const [openChangePasswordDialog, setOpenChangePasswordDialog] =
    useState(false);
  const [currentPassword, setCurrentPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const navigate = useNavigate();
  const token = localStorage.getItem("JWT");
  const id = localStorage.getItem("ID");
  const backend_root = "http://localhost:8080/api/v1";

  const handleLogout = () => {
    localStorage.removeItem("JWT");
    localStorage.removeItem("ID");
    navigate("/login");
  };

  const handleChangePassword = async () => {
    if (newPassword !== confirmPassword) {
      alert("Passwords do not match!");
      return;
    }

    try {
      let result = await axios.post(
        `${backend_root}/user/change-password`,
        {
          id: id,
          currentPassword: currentPassword,
          newPassword: newPassword,
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      alert("Password changed successfully");
      setOpenChangePasswordDialog(false);
    } catch (error) {
      console.error("Error changing password:", error);
      alert("Failed to change password. Please try again.");
    }
  };

  return (
    <div className="profile-page-cntr">
      <h2>My Profile</h2>
      <Button variant="contained" onClick={() => navigate("/")}>
        Home
      </Button>
      <Button
        variant="contained"
        onClick={() => setOpenChangePasswordDialog(true)}
      >
        Change Password
      </Button>
      <Button variant="contained" onClick={handleLogout}>
        Log Out
      </Button>

      <Dialog
        open={openChangePasswordDialog}
        onClose={() => setOpenChangePasswordDialog(false)}
      >
        <DialogTitle>Change Password</DialogTitle>
        <DialogContent>
          <TextField
            label="Current Password"
            type="password"
            fullWidth
            margin="normal"
            value={currentPassword}
            onChange={(e) => setCurrentPassword(e.target.value)}
          />
          <TextField
            label="New Password"
            type="password"
            fullWidth
            margin="normal"
            value={newPassword}
            onChange={(e) => setNewPassword(e.target.value)}
          />
          <TextField
            label="Confirm New Password"
            type="password"
            fullWidth
            margin="normal"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setOpenChangePasswordDialog(false)}>
            Cancel
          </Button>
          <Button onClick={handleChangePassword} variant="contained">
            Change Password
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

export default MyProfile;
