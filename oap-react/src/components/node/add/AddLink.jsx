import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { addLink } from "../../../services/NodeService";

function AddLink() {
  const [name, setName] = useState("");
  const [length, setLength] = useState(0);
  const [from_node, setFromNode] = useState("");
  const [to_node, setToNode] = useState("");

  const [formErrors, setFormErrors] = useState({});

  const navigate= useNavigate();
  function addLinkAxios(payload) {
    addLink(payload).then((res) => {
      alert("New Link Added");
    });
  }

  const handleSubmit = () => {
    let errors = {};

    if (!name) {
      errors["nameError"] = "Please enter a valid Link name";
    }
    if (length < 0) {
      errors["lengthError"] = "Please enter a valid Length";
    }
    if (!from_node) {
      errors["fromNodeError"] = "Please enter a valid Node from the Link";
    }
    if (!to_node) {
      errors["toNodeError"] = "Please enter a valid Node to the link";
    }

    setFormErrors(errors);

    const noErrors = Object.keys(errors).length === 0;

    if (noErrors) {
      const payload = {
        name: name,
        length: length,
        from_node: from_node,
        to_node: to_node,
      };

      addLinkAxios(payload);
    }
  };

  return (
    <div>
      <div className="title">
        <h2>Please Enter the Link details</h2>
      </div>
      <div className="body">
        <form>
          <label htmlFor="name">Link Name</label>
          <input
            className="input"
            id="name"
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder="Link Name: "
          />
          {formErrors.nameError && (
            <div style={{ color: "red", paddingBottom: 10 }}>
              {" "}
              {formErrors.nameError}
            </div>
          )}

          <label htmlFor="name">Length</label>

          <input
            className="input"
            type="number"
            id="length"
            value={length}
            onChange={(e) => setLength(e.target.value)}
            placeholder="Length: "
          />
          {formErrors.lengthError && (
            <div style={{ color: "red", paddingBottom: 10 }}>
              {" "}
              {formErrors.lengthError}
            </div>
          )}

          <input
            className="input"
            id="from_node"
            value={from_node}
            onChange={(e) => setFromNode(e.target.value)}
            placeholder="From Node: "
          />
          {formErrors.fromNodeError && (
            <div style={{ color: "red", paddingBottom: 10 }}>
              {" "}
              {formErrors.fromNodeError}
            </div>
          )}

          <input
            className="input"
            id="to_node"
            value={to_node}
            onChange={(e) => setToNode(e.target.value)}
            placeholder="To Node: "
          />
          {formErrors.toNodeError && (
            <div style={{ color: "red", paddingBottom: 10 }}>
              {" "}
              {formErrors.toNodeError}
            </div>
          )}

          <br />
          <button
            type="button"
            class="btn btn-outline-primary"
            onClick={handleSubmit}
          >
            Add Link
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
export default AddLink;
