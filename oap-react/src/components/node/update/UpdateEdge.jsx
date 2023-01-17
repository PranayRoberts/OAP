import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getEdgeById, updateEdge } from "../../../services/NodeService";

function UpdateEdge() {
  const [edge_id, setEdgeId] = useState(0);
  const [name, setName] = useState("");
  const [isAvailable, setIsAvailable] = useState(true);

  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    getEdgeById(id).then((res) => {
      setEdgeId(res.data.edge_id);
      setName(res.data.name);
      setIsAvailable(res.data.isAvailable);
    });
  }, [id]);

  const handleUpdate = () => {
    const payload = {
      edge_id: edge_id,
      name: name,
      isAvailable: isAvailable,
    };

    updateEdge(id, payload)
      .then((res) => navigate(-1))
      .then(alert("Details have been modified"))
      .catch((error) => console.log("Something went wrong"));
  };

  return (
    <div>
      <div>
        <label>Edge ID:</label>
        <input
          type="number"
          id="edge_id"
          name="edge_id"
          value={edge_id}
          disabled
        />
      </div>

      <div>
        <label>Edge Name:</label>
        <input
          type="text"
          id="name"
          name="name"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
      </div>

      <div className="drop-down-cont flex justify-content-space-between align-items-center">
        <label htmlFor="isAvailable">Is the Edge available?</label>
        <select id="isAvailable" className="input">
          
          <option
            value={isAvailable}
            onChange={(e) => setIsAvailable(false)}
          >
            false
          </option>

          <option
            value={isAvailable}
            onChange={(e) => setIsAvailable(true)}
          >
            true
          </option>
        </select>
      </div>

      <div>
        <button class="btn btn-primary" onClick={handleUpdate}>
          Edit Edge
        </button>
      </div>
    </div>
  );
}

export default UpdateEdge;
