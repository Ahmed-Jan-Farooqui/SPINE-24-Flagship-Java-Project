import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
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
import AddContactModal from "../AddContactModal/AddContactModal";
import "./HomePage.css";
import ContactCard from "../ContactCard/ContactCard";

interface HomePageProps {
  id: number;
}

interface Contact {
  id: number;
  firstName: string;
  lastName: string;
  emails: {
    id: number;
    email: string;
    label: string;
  }[];
  phones: {
    id: number;
    number: string;
    label: string;
  }[];
}
const HomePage = () => {
  const [openAddContactForm, setOpenAddContactForm] = useState(false);
  const [contacts, setContacts] = useState<Contact[]>([]);
  const [pages, setPages] = useState([""]);
  const [currentPage, setCurrentPage] = useState(1);
  const token = localStorage.getItem("JWT");
  const id = localStorage.getItem("ID");
  console.log("Token is: ", token);
  console.log("ID is: ", id);
  const navigate = useNavigate();
  const backend_root = "http://localhost:8080/api/v1";
  const fetchUserData = async () => {
    // Check if token exists
    if (!token) {
      navigate("/login");
    }
    try {
      let response = await axios.get(`${backend_root}/data/user`, {
        params: {
          id: id,
        },
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      let contactTemp = response.data.contacts.content.map((item: any) => {
        return {
          id: item.id,
          firstName: item.firstName,
          lastName: item.lastName,
          emails: item.emails,
          phones: item.phones,
        };
      });
      setContacts([...contactTemp]);
      for (
        let i = 0;
        i < parseInt(response.data.contacts.totalPages) - 1;
        i++
      ) {
        setPages([...pages, ""]);
      }
    } catch (error: any) {
      if (axios.isAxiosError(error) && error.response) {
        if (error.request.status === 403) {
          console.log("Authorization failed...");
          navigate("/login");
        } else {
          console.log("Some error occurred on the server...");
        }
      } else if (axios.isAxiosError(error) && error.request) {
        console.log("Could not reach the server...");
      } else {
        console.log("An unknown error occurred ", error);
      }
    }
  };

  const addContact = () => {
    setOpenAddContactForm(true);
  };

  const handleCloseAddContactForm = () => {
    setOpenAddContactForm(false);
  };

  const handleDelete = async (id: number) => {
    try {
      let response = await axios.post(
        `${backend_root}/contact/delete`,
        {
          id: id,
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      setContacts((prev) => {
        let temp = prev.filter((item) => {
          return item.id !== id;
        });
        return [...temp];
      });
    } catch (error) {
      console.error(error);
    }
  };

  const handleAdd = async (
    firstName: string,
    lastName: string,
    emailObj: any,
    phoneObj: any
  ) => {
    try {
      let res = await axios.post(
        "http://localhost:8080/api/v1/contact/add",
        {
          id: id,
          firstName: firstName,
          lastName: lastName,
          emails: emailObj,
          phones: phoneObj,
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      fetchUserData();
    } catch (error) {
      console.error(error);
    }
  };

  const handleUpdate = async (updateReq: any) => {
    try {
      let res = await axios.post(`${backend_root}/contact/update`, updateReq, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      fetchUserData();
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchUserData();
  }, []);

  useEffect(() => {
    console.log("Pages: ", pages);
  }, [pages]);

  return (
    <>
      <div className="header-cntr">
        <h2>My Profile</h2>
        <h2
          onClick={() => {
            localStorage.removeItem("JWT");
            localStorage.removeItem("ID");
            navigate("/login");
          }}
        >
          Log Out
        </h2>
      </div>
      <div className="home-page-cntr">
        <div className="search-bar-cntr">
          <div className="search-bar">
            <TextField label="Search contacts" sx={{ width: 1 }} />
          </div>
        </div>
        <div className="contact-list-cntr">
          {contacts.map((item) => {
            return (
              <div className="contact-card" key={item.id}>
                <ContactCard
                  emails={item.emails}
                  phones={item.phones}
                  firstName={item.firstName}
                  lastName={item.lastName}
                  id={item.id}
                  handleDeleteContact={handleDelete}
                  handleUpdate={handleUpdate}
                />
              </div>
            );
          })}
          <ButtonGroup className="page-nmbr-cntr">
            {pages.map((_, index) => {
              return <Button variant="outlined">{index + 1}</Button>;
            })}
          </ButtonGroup>
        </div>
        <Button
          variant="contained"
          className="add-contact-btn"
          onClick={addContact}
        >
          Add Contact
        </Button>
        <AddContactModal
          handleClose={handleCloseAddContactForm}
          open={openAddContactForm}
          handleAdd={handleAdd}
        />
      </div>
    </>
  );
};

export default HomePage;
