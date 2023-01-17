import React from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { optimumAmplifier } from "../../../services/NodeService";

function Optimum() {
  const [node_id, setNodeId] = useState(0);
  const [link_id, setLinkId] = useState(0);
  const [card_id, setCardId] = useState(0);

  const navigate = useNavigate();

  const handleAlgo = () => {
    optimumAmplifier(node_id, link_id, card_id)
      .then((res) => navigate("/card/get"))
      .then(alert("Optimum Amplifier with Added as Card ID" + card_id))
  };

  return (
    <div>
      <div>
        <label>Node ID:</label>
        <input
          type="number"
          id="node_id"
          name="node_id"
          value={node_id}
          onChange={(e) => setNodeId(e.target.value)}
        />
      </div>

      <div>
        <label>Link ID:</label>
        <input
          id="link_id"
          name="link_id"
          value={link_id}
          onChange={(e) => setLinkId(e.target.value)}
        />
      </div>

      <div>
        <label>Card ID:</label>
        <input
          id="card_id"
          name="card_id"
          value={card_id}
          onChange={(e) => setCardId(e.target.value)}
        />
      </div>

      <div>
        <button class="btn btn-secondary" onClick={handleAlgo}>
          Get Optimum Amplifier
        </button>

        <button
          type="button"
          class="btn btn-danger"
          onClick={() => {
            navigate(-1);
          }}
        >
          Cancel
        </button>
      </div>
    </div>
  );
}

export default Optimum;
