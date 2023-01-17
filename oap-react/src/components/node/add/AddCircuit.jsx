import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { addCircuit } from "../../../services/NodeService";

function AddCircuit() {
  const [source, setSource] = useState("");
  const [destination, setDestination] = useState("");

  const [formErrors, setFormErrors] = useState({});

  const navigate = useNavigate();
  function addCircuitAxios(payload) {
    addCircuit(payload).then((res) => {
      alert("New Circuit Added");
    });
  }

  const handleSubmit = () => {
    let errors = {};

    if (!source) {
      errors["sourceError"] = "Please enter a valid Source";
    }
    if (!destination) {
      errors["destinationError"] = "Please enter a valid Destination";
    }

    setFormErrors(errors);

    const noErrors = Object.keys(errors).length === 0;

    if (noErrors) {
      const payload = {
        source: source,
        destination: destination,
      };

      addCircuitAxios(payload);
    }
  };

  return (
    <div>
      <div className="title">
        <h2>Please Enter the Circuit details</h2>
      </div>
      <div className="body">
        <form>
          <input
            className="input"
            id="source"
            value={source}
            onChange={(e) => setSource(e.target.value)}
            placeholder="Circuit Source"
          />
          {formErrors.sourceError && (
            <div style={{ color: "red", paddingBottom: 10 }}>
              {" "}
              {formErrors.sourceError}
            </div>
          )}
          ⮕
          <input
            className="input"
            id="destination"
            value={destination}
            onChange={(e) => setDestination(e.target.value)}
            placeholder="Circuit Destination"
          />
          {formErrors.destinationError && (
            <div style={{ color: "red", paddingBottom: 10 }}>
              {" "}
              {formErrors.destinationError}
            </div>
          )}
          <br />
          <button
            type="button"
            class="btn btn-outline-primary"
            onClick={handleSubmit}
          >
            Add Circuit
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

export default AddCircuit;
