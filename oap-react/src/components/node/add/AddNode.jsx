import React from "react";
import { useState } from "react";
import { addNode } from "../../../services/NodeService";
import "./AddNode.css";

function AddNode(props) {
  const [name, setName] = useState("");
  const [ip, setIp] = useState("");
  const [password, setPassword] = useState("");
  const [type, setType] = useState("");
  const [xposition, setXPosition] = useState();
  const [yposition, setYPosition] = useState();

  const [formErrors, setFormErrors] = useState({});

  function addNodeAxios(payload) {
    addNode(payload).then((res) => {
      alert("New Node Added");
    });
  }
  const handleSubmit = () => {
    let errors = {};

    if (!name) {
      errors["nameError"] = "Please enter a valid Node name";
    }
    if (!ip) {
      errors["ipError"] = "Please enter a valid Node IP Address";
    }
    if (!password) {
      errors["passwordError"] = "Please enter a valid Password";
    }
    if (!type) {
      errors["typeError"] = "Pleasae enter a valid Type";
    }
    if (!xposition) {
      errors["xPositionError"] = "Please enter a valid X Positon";
    }
    if (!yposition) {
      errors["yPositionError"] = "Please enter a valid Y Position";
    }

    setFormErrors(errors);

    const noErrors = Object.keys(errors).length === 0;

    if (noErrors) {
      const payload = {
        name: name,
        ip: ip,
        password: password,
        type: type,
        xposition: xposition,
        yposition: yposition,
      };
      addNodeAxios(payload);
    }
  };

  return (
    <div className="modalBackdrop">
      <div className="modalContainer">
        <div className="title">
          <h2>Please Enter the Node details</h2>
        </div>
        <div className="body">
          <form>
            <input
              id="name"
              value={name}
              onChange={(e) => setName(e.target.value)}
              placeholder="Node Name"
            />
            {formErrors.nameError && (
              <div style={{ color: "red", paddingBottom: 10 }}>
                {" "}
                {formErrors.nameError}
              </div>
            )}

            <input
              id="ip"
              value={ip}
              onChange={(e) => setIp(e.target.value)}
              placeholder="Node IP"
            />
            {formErrors.ipError && (
              <div style={{ color: "red", paddingBottom: 10 }}>
                {" "}
                {formErrors.ipError}
              </div>
            )}

            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="Node Password"
            />
            {formErrors.passwordError && (
              <div style={{ color: "red", paddingBottom: 10 }}>
                {" "}
                {formErrors.passwordError}
              </div>
            )}

            <input
              id="type"
              value={type}
              onChange={(e) => setType(e.target.value)}
              placeholder="Node Type"
            />
            {formErrors.typeError && (
              <div style={{ color: "red", paddingBottom: 10 }}>
                {" "}
                {formErrors.typeError}
              </div>
            )}

            <div>
              <input
                type="number"
                id="xposition"
                value={xposition}
                onChange={(e) => setXPosition(e.target.value)}
                placeholder="X Position"
              />
              {formErrors.xPositionError && (
                <div style={{ color: "red", paddingBottom: 10 }}>
                  {" "}
                  {formErrors.xPositionError}
                </div>
              )}
            </div>

            <div>
              <input
                type="number"
                id="yposition"
                value={yposition}
                onChange={(e) => setYPosition(e.target.value)}
                placeholder="Y Position"
              />
              {formErrors.yPositionError && (
                <div style={{ color: "red", paddingBottom: 10 }}>
                  {" "}
                  {formErrors.yPositionError}
                </div>
              )}
            </div>

            <br />
            <button
              type="button"
              class="btn btn-primary"
              onClick={handleSubmit}
            >
              Add Node
            </button>
          </form>
        </div>

        <div className="footer">
          <button id="cancelBtn" onClick={() => props.closeAddNode(false)}>
            Cancel
          </button>
        </div>
      </div>
    </div>
  );
}

export default AddNode;
