import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { addNetwork } from "../../../services/NodeService";

function AddNetwork() {
  const [network_name, setNetworkName] = useState("");

  const user = JSON.parse(localStorage.getItem("loggedInUser"));

  const [formErrors, setFormErrors] = useState({});

  const navigate = useNavigate();

  function addNetworkAxios(payload) {
    addNetwork(payload).then((res) => {
      alert("New Network Added for User: "+user );
    });
  }

  const handleSubmit = () => {
    let errors = {};

    if (!network_name) {
      errors["networkNameError"] = "Please enter a valid Network name";
    }
    
    setFormErrors(errors);

    const noErrors = Object.keys(errors).length === 0;

    if (noErrors) {
      const payload = {
        network_name: network_name,
        username: user,
      };
      addNetworkAxios(payload);
    }
  };

  return (
    <div>
      <div className="title">
        <h2>Please Enter the Network details</h2>
      </div>

      <div className="body">
        <form>
          <label htmlFor="name">Network Name</label>
          <input
            className="input"
            id="network_name"
            value={network_name}
            onChange={(e) => setNetworkName(e.target.value)}
            placeholder="Network Name"
          />
          {formErrors.networkNameError && (
            <div style={{ color: "red", paddingBottom: 10 }}>
              {" "}
              {formErrors.networkNameError}
            </div>
          )}

          <label htmlFor="name">Username:</label>

          <input
            className="input"
            id="username"
            value={user}
            disabled
          />
          

          <br />
          <button
            type="button"
            class="btn btn-outline-primary"
            onClick={handleSubmit}
          >
            Add Network
          </button>

          <button
            type="button"
            class="btn btn-outline-danger"
            onClick={() => {
              navigate(-1);
            }}
          >
            Cancel
          </button>
        </form>
      </div>
    </div>
  );
}

export default AddNetwork;
