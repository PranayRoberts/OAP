import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { addEdge } from "../../../services/NodeService";

function AddEdge() {
  const [name, setName] = useState("");
  const [isAvailable, setIsAvailable] = useState(false);

  const [formErrors, setFormErrors] = useState({});

  const navigate = useNavigate();
  function addEdgeAxios(payload) {
    addEdge(payload).then((res) => {
      alert("New Edge Added");
    });
  }

  const handleSubmit = () => {
    let errors = {};

    if (!name || name.length > 1) {
      errors["nameError"] =
        "Please enter a valid Edge name (Must be a character)";
    }

    setFormErrors(errors);

    const noErrors = Object.keys(errors).length === 0;

    if (noErrors) {
      const payload = {
        name: name,
        isAvailable: isAvailable,
      };

      addEdgeAxios(payload);
    }
  };

  return (
    
      <div>
        <div className="title">
          <h2>Please Enter the Edge details</h2>
        </div>
        <div className="body">
          <form>
            <input
              className="input"
              id="name"
              value={name}
              onChange={(e) => setName(e.target.value)}
              placeholder="Edge Name"
            />
            {formErrors.nameError && (
              <div style={{ color: "red", paddingBottom: 10 }}>
                {" "}
                {formErrors.nameError}
              </div>
            )}

            <div className="drop-down-cont flex justify-content-space-between align-items-center">
              <label htmlFor="isAvailable">Is the Edge available?</label>
              <select id="isAvailable" className="input" value={isAvailable} onChange={(event) => setIsAvailable(event.target.value)}>
                <option
                  value={false}   
                >
                  false
                </option>

                <option
                  value={true}
                >
                  true
                </option>
              </select>
            </div>

            <br />
            <button
              type="button"
              class="btn btn-outline-primary"
              onClick={handleSubmit}
            >
              Add Edge
            </button>

            <button
              type="button"
              class="btn btn-outline-danger"
              onClick= {() => { navigate(-1)}}
            >
              Cancel
            </button>
          </form>
        </div>
        </div>
    
  );
}

export default AddEdge;
