import "./ContactCard.css";
import Button from "@mui/material/Button";
import DeleteIcon from "@mui/icons-material/Delete";
import EditIcon from "@mui/icons-material/Edit";
import { useState } from "react";
export default function ContactCard({
  emails,
  phones,
  firstName,
  lastName,
  id,
  handleDelete,
}: any) {
  const [editMode, setEditMode] = useState(false);
  const [changes, setChanges] = useState<{}[]>([]);
  const deleteContact = () => {
    handleDelete(id);
  };
  const deleteEmail = (id: number, email: string, label: string) => {
    let emailObj = { id: id, email: email, label: label };
    setChanges([...changes, emailObj]);
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
          <div className="section-title">Emails:</div>
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
                        deleteEmail(item.id, item.email, item.label);
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
          <div className="section-title">Phones:</div>
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
                      // onClick={deleteNumber}
                      className="delete-btn"
                    ></Button>
                  )}
                </div>
              );
            })}
          </ol>
        </div>
      </div>
      <Button
        variant="outlined"
        startIcon={<DeleteIcon />}
        onClick={deleteContact}
        className="delete-contact-btn"
      >
        Delete Contact
      </Button>
    </div>
  );
}
