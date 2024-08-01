import "./ContactCard.css";
import Button from "@mui/material/Button";
import DeleteIcon from "@mui/icons-material/Delete";
import EditIcon from "@mui/icons-material/Edit";
import CheckIcon from "@mui/icons-material/Check";
import ClearIcon from "@mui/icons-material/Clear";
import AddIcon from "@mui/icons-material/Add";
import { useState } from "react";
import { ButtonGroup } from "@mui/material";

interface Phone {
  id: number;
  number: string;
  label: string;
  requestType: "add" | "update" | "delete";
}
interface Email {
  id: number;
  email: string;
  label: string;
  requestType: "add" | "update" | "delete";
}

interface UpdateRequest {
  id: number;
  firstName: String;
  lastName: String;
  email?: Email[];
  phone?: Phone[];
}

export default function ContactCard({
  emails,
  phones,
  firstName,
  lastName,
  id,
  handleDeleteContact,
  handleUpdate,
}: any) {
  const [editMode, setEditMode] = useState(false);
  const [localPhones, setLocalPhones] = useState([...phones]);
  const [localEmails, setLocalEmails] = useState([...emails]);
  const [emailChanges, setEmailChanges] = useState<{}[]>([]);
  const [phoneChanges, setPhoneChanges] = useState<{}[]>([]);

  const deleteContact = () => {
    handleDeleteContact(id);
  };

  const handleEmailUpdate = (
    id: number,
    email: string,
    label: string,
    requestType: "add" | "delete" | "update"
  ) => {
    let emailObj: Email = {
      id: id,
      email: email,
      label: label,
      requestType: requestType,
    };
    setEmailChanges([...emailChanges, emailObj]);
  };

  const handlePhoneUpdate = (
    id: number,
    number: string,
    label: string,
    requestType: "add" | "delete" | "update"
  ) => {
    let phoneObj: Phone = {
      id: id,
      number: number,
      label: label,
      requestType: requestType,
    };
    setPhoneChanges([...phoneChanges, phoneObj]);
  };

  return (
    <div className="contact-card-cntr">
      <div className="header-cntr">
        <div className="name-cntr">{firstName + " " + lastName}</div>
        <Button
          variant="outlined"
          onClick={() =>
            setEditMode((prev) => {
              return !prev;
            })
          }
          size="small"
        >
          <EditIcon />
        </Button>
      </div>
      <div className="contact-info-cntr">
        <div className="emails-cntr">
          <div className="section-title">
            Emails:
            {editMode && <Button startIcon={<AddIcon />} />}
          </div>
          <ol>
            {emails.map((item: any) => {
              return (
                <div className="email-cntr" key={item.email}>
                  <li>
                    <span className="contact-info">{item.email}</span>
                    <span className="contact-label">{item.label}</span>
                  </li>
                  {editMode && (
                    <Button
                      variant="outlined"
                      startIcon={<DeleteIcon />}
                      onClick={() => {
                        handleEmailUpdate(
                          item.id,
                          item.email,
                          item.label,
                          "delete"
                        );
                      }}
                      className="delete-btn"
                    ></Button>
                  )}
                </div>
              );
            })}
          </ol>
        </div>
        <div className="phones-cntr">
          <div className="section-title">
            Phones:
            {editMode && <Button startIcon={<AddIcon />} />}
          </div>
          <ol>
            {phones.map((item: any) => {
              return (
                <div className="phone-num-cntr" key={item.number}>
                  <li>
                    <span className="contact-info">{item.number}</span>
                    <span className="contact-label">{item.label}</span>
                  </li>
                  {editMode && (
                    <Button
                      variant="outlined"
                      startIcon={<DeleteIcon />}
                      onClick={() => {
                        handlePhoneUpdate(
                          item.id,
                          item.number,
                          item.label,
                          "delete"
                        );
                      }}
                      className="delete-btn"
                    ></Button>
                  )}
                </div>
              );
            })}
          </ol>
        </div>
      </div>
      <div className="action-btn-cntr">
        <Button
          variant="outlined"
          startIcon={<DeleteIcon />}
          onClick={deleteContact}
          className="delete-contact-btn"
        >
          Delete Contact
        </Button>
        {editMode && (
          <ButtonGroup>
            <Button
              variant="contained"
              startIcon={<CheckIcon />}
              className="confirm-btn"
            />
            <Button
              variant="outlined"
              startIcon={<ClearIcon />}
              className="confirm-btn"
            />
          </ButtonGroup>
        )}
      </div>
    </div>
  );
}
