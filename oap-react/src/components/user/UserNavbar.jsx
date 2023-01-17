import React, { useState } from "react";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { MdLogout } from "react-icons/md";
import { GrAddCircle, GrView, GrFormView } from "react-icons/gr";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import Modal from "@mui/material/Modal";

function UserNavbar() {
  const user = JSON.parse(localStorage.getItem("loggedInUser"));

  const navigate = useNavigate();

  const [open, setOpen] = useState(false);

  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const style = {
    textAlign: "center",
    position: "absolute",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    width: 400,
    bgcolor: "background.paper",
    boxShadow: 24,
    p: 4,
  };

  const handleLogout = () => {
    if (user !== null) {
      localStorage.removeItem("loggedInUser");
      alert(user + " has been logged out!");
      navigate("/");
    }
  };
  return (
    <div>
      {user !== null ? (
        <div>
          <nav class="navbar navbar-expand-md navbar-light bg-light">
            <div class="container-fluid">
              <Link class="navbar-brand" to="/">
                OAP
              </Link>

              <button
                class="navbar-toggler"
                type="button"
                data-mdb-toggle="collapse"
                data-mdb-target="#navbarNavAltMarkup"
                aria-controls="navbarNavAltMarkup"
                aria-expanded="false"
                aria-label="Toggle navigation"
              >
                <i class="fas fa-bars"></i>
              </button>

              <div
                class="collapse navbar-collapse justify-content-center"
                id="navbarNavAltMarkup"
              >
                <div class="navbar-nav">
                  <Link to={`/user/${user}`} class="nav-link">
                    Profile
                  </Link>
                  <Link to="/network" class="nav-link">
                    Network
                  </Link>
                  <Link onClick={handleOpen} class="nav-link">
                    Network Details
                  </Link>

                  <Modal
                    open={open}
                    onClose={handleClose}
                    aria-labelledby="modal-modal-title"
                    aria-describedby="modal-modal-description"
                  >
                    <Box sx={style}>
                      <Typography
                        id="modal-modal-title"
                        variant="h6"
                        component="h2"
                      >
                        Networks
                      </Typography>
                      <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                        <Link to="/network/add">
                          <GrAddCircle /> Add Network
                        </Link>
                      </Typography>
                      <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                        <Link to="/network/get">
                          <GrView /> View Networks
                        </Link>
                        <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                          {/* <Link to={`/network/${user}`} > */}
                          <Link to={'/network'} >
                            <GrView /> View Network
                          </Link>
                        </Typography>
                      </Typography>
                    </Box>
                  </Modal>

                  <button
                    onClick={handleLogout}
                    class="btn btn-warning nav-link logout"
                  >
                    Logout <MdLogout />
                  </button>
                </div>
              </div>
            </div>
          </nav>
        </div>
      ) : (
        <h2>Please Login first!</h2>
      )}
    </div>
  );
}

export default UserNavbar;
