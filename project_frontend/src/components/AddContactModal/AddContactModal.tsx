import {
  TextField,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  Button,
  ButtonGroup,
} from "@mui/material";
import { useEffect, useState } from "react";
import axios from "axios";

export default function AddContactModal({ handleClose, open, handleAdd }: any) {
  const [phoneNumbers, setPhoneNumbers] = useState([""]);
  const [phoneNumberLabels, setPhoneNumberLabels] = useState([""]);
  const [emails, setEmails] = useState([""]);
  const [emailLabels, setEmailLabels] = useState([""]);

  // Add phones and email handlers
  const addPhone = () => {
    setPhoneNumbers([...phoneNumbers, ""]);
    setPhoneNumberLabels([...phoneNumberLabels, ""]);
  };
  const addEmail = () => {
    setEmails([...emails, ""]);
    setEmailLabels([...emailLabels, ""]);
  };

  // Change handlers
  const handleChangePhone = (index: number, value: string) => {
    setPhoneNumbers((prev) => {
      prev[index] = value;
      return [...prev];
    });
  };
  const handleChangePhoneLabel = (index: number, value: string) => {
    setPhoneNumberLabels((prev) => {
      prev[index] = value;
      return [...prev];
    });
  };
  const handleChangeEmail = (index: number, value: string) => {
    setEmails((prev) => {
      prev[index] = value;
      return [...prev];
    });
  };
  const handleChangeEmailLabel = (index: number, value: string) => {
    setEmailLabels((prev) => {
      prev[index] = value;
      return [...prev];
    });
  };

  // Remove phone and email handlers
  const removePhone = () => {
    if (phoneNumbers.length === 0) {
      return;
    }
    setPhoneNumbers([...phoneNumbers.slice(0, -1)]);
  };
  const removeEmail = () => {
    if (emails.length === 0) {
      return;
    }
    setEmails([...emails.slice(0, -1)]);
  };
  const closeForm = () => {
    setPhoneNumbers([""]);
    setPhoneNumberLabels([""]);
    setEmails([""]);
    setEmailLabels([""]);
    handleClose();
  };

  // Submit handler
  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    const formData = new FormData(event.currentTarget);
    const formJson = Object.fromEntries((formData as any).entries());
    let firstName = formJson.firstName;
    let lastName = formJson.lastName;
    let phoneObj = [...phoneNumbers].map((item, index) => {
      return {
        number: item,
        label: phoneNumberLabels[index],
      };
    });
    let emailObj = [...emails].map((item, index) => {
      return {
        email: item,
        label: emailLabels[index],
      };
    });
    handleAdd(firstName, lastName, emailObj, phoneObj);
  };

  return (
    <Dialog
      open={open}
      onClose={handleClose}
      PaperProps={{
        component: "form",
        onSubmit: (event: React.FormEvent<HTMLFormElement>) => {
          event.preventDefault();
          handleSubmit(event);
          closeForm();
        },
      }}
    >
      <DialogTitle>New Contact</DialogTitle>
      <DialogContent>
        <DialogContentText>
          Enter the details of your new contact below.
        </DialogContentText>
        <TextField
          autoFocus
          required
          margin="dense"
          id="name"
          name="firstName"
          label="First Name"
          type="text"
          fullWidth
          variant="standard"
        />
        <TextField
          autoFocus
          required
          margin="dense"
          id="name"
          name="lastName"
          label="Last Name"
          type="text"
          fullWidth
          variant="standard"
        />
        {phoneNumbers.map((phone, index) => {
          return (
            <div key={index}>
              <TextField
                autoFocus
                required
                value={phone}
                margin="dense"
                id="name"
                name={`phone${index}`}
                label={`Phone Number ${index + 1}`}
                type="text"
                onChange={(e) => {
                  handleChangePhone(index, e.target.value);
                }}
                fullWidth
                variant="standard"
              />
              <TextField
                autoFocus
                required
                value={phoneNumberLabels[index]}
                onChange={(e) => {
                  handleChangePhoneLabel(index, e.target.value);
                }}
                margin="dense"
                id="name"
                name={`phoneLabel${index}`}
                label={`Label For Phone Number ${index + 1}`}
                type="text"
                fullWidth
                variant="standard"
              />
            </div>
          );
        })}
        <ButtonGroup>
          <Button variant="outlined" onClick={addPhone}>
            Add Phone Number
          </Button>
          <Button variant="outlined" onClick={removePhone}>
            Remove Phone Number
          </Button>
        </ButtonGroup>
        {emails.map((email, index) => {
          return (
            <div key={index}>
              <TextField
                autoFocus
                required
                value={email}
                onChange={(e) => {
                  handleChangeEmail(index, e.target.value);
                }}
                margin="dense"
                id="name"
                name={`email-${index}`}
                label={`Email ${index + 1}`}
                type="text"
                fullWidth
                variant="standard"
              />
              <TextField
                autoFocus
                required
                value={emailLabels[index]}
                onChange={(e) => {
                  handleChangeEmailLabel(index, e.target.value);
                }}
                margin="dense"
                id="name"
                name={`email-label-${index}`}
                label={`Label For Email ${index + 1}`}
                type="text"
                fullWidth
                variant="standard"
              />
            </div>
          );
        })}
        <ButtonGroup>
          <Button variant="outlined" onClick={addEmail}>
            Add Email
          </Button>
          <Button variant="outlined" onClick={removeEmail}>
            Remove Email
          </Button>
        </ButtonGroup>
      </DialogContent>
      <DialogActions>
        <Button variant="outlined" onClick={closeForm}>
          Cancel
        </Button>
        <Button variant="contained" type="submit">
          Add
        </Button>
      </DialogActions>
    </Dialog>
  );
}
