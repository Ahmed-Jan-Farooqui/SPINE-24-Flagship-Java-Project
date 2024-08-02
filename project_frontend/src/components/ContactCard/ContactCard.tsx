import "./ContactCard.css";
import Button from "@mui/material/Button";
import DeleteIcon from "@mui/icons-material/Delete";
import EditIcon from "@mui/icons-material/Edit";
import CheckIcon from "@mui/icons-material/Check";
import ClearIcon from "@mui/icons-material/Clear";
import AddIcon from "@mui/icons-material/Add";
import { useEffect, useState } from "react";
import { ButtonGroup } from "@mui/material";
import AddPhoneModal from "../AddPhoneModal/AddPhoneModal";
import AddEmailModal from "../AddEmailModal/AddEmailModal";

interface Phone {
  phone: {
    id: number;
    number: string;
    label: string;
  };
  requestType: "add" | "update" | "delete";
}
interface Email {
  email: {
    id: number;
    email: string;
    label: string;
  };
  requestType: "add" | "update" | "delete";
}

interface UpdateRequest {
  id: number;
  firstName: String;
  lastName: String;
  phoneReqs?: Phone[];
  emailReqs?: Email[];
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
  const [emailChanges, setEmailChanges] = useState<Email[]>([]);
  const [phoneChanges, setPhoneChanges] = useState<Phone[]>([]);
  const [openAddPhone, setOpenAddPhone] = useState(false);
  const [openAddEmail, setOpenAddEmail] = useState(false);

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
      email: {
        id: id,
        email: email,
        label: label,
      },
      requestType: requestType,
    };
    setEmailChanges([...emailChanges, emailObj]);
    if (requestType === "add") {
      setLocalEmails([
        ...localEmails,
        {
          id: id,
          email: email,
          label: label,
        },
      ]);
    }
  };

  const handlePhoneUpdate = (
    id: number,
    number: string,
    label: string,
    requestType: "add" | "delete" | "update"
  ) => {
    let phoneObj: Phone = {
      phone: {
        id: id,
        number: number,
        label: label,
      },
      requestType: requestType,
    };
    setPhoneChanges([...phoneChanges, phoneObj]);
    if (requestType === "add") {
      setLocalPhones([
        ...localPhones,
        {
          id: id,
          number: number,
          label: label,
        },
      ]);
    }
  };

  const addPhone = () => {
    setOpenAddPhone(true);
  };
  const closePhone = () => {
    setOpenAddPhone(false);
  };

  const addEmail = () => {
    setOpenAddEmail(true);
  };
  const closeEmail = () => {
    setOpenAddEmail(false);
  };

  const restoreContact = () => {
    setLocalEmails([...emails]);
    setLocalPhones([...phones]);
    setEditMode(false);
  };

  const updateContact = () => {
    const updateReq: UpdateRequest = {
      id: id,
      firstName: "",
      lastName: "",
      emailReqs: [...emailChanges],
      phoneReqs: [...phoneChanges],
    };
    handleUpdate(updateReq);
    setEditMode(false);
  };

  useEffect(() => {
    restoreContact();
  }, []);

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
            {editMode && <Button startIcon={<AddIcon />} onClick={addEmail} />}
          </div>
          <ol>
            {localEmails.map((item: any) => {
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
                        setLocalEmails((prev) => {
                          let newEmails = prev.filter((email) => {
                            return email.id !== item.id;
                          });
                          return newEmails;
                        });
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
            {editMode && <Button startIcon={<AddIcon />} onClick={addPhone} />}
          </div>
          <ol>
            {localPhones.map((item: any) => {
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
                        setLocalPhones((prev) => {
                          let newPhones = prev.filter((phone) => {
                            return phone.id !== item.id;
                          });
                          return newPhones;
                        });
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
              onClick={updateContact}
            />
            <Button
              variant="outlined"
              startIcon={<ClearIcon />}
              className="confirm-btn"
              onClick={restoreContact}
            />
          </ButtonGroup>
        )}
      </div>
      <AddPhoneModal
        open={openAddPhone}
        handleAdd={handlePhoneUpdate}
        handleClose={closePhone}
      />
      <AddEmailModal
        open={openAddEmail}
        handleAdd={handleEmailUpdate}
        handleClose={closeEmail}
      />
    </div>
  );
}
