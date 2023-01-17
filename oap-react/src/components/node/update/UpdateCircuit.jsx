import React from 'react'
import { useEffect } from "react";
import { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getCircuitById, updateCircuit } from '../../../services/NodeService';

function UpdateCircuit() {
    const [circuit_id, setCircuitId] = useState(0);
    const [source, setSource] = useState("");
    const [destination, setDestination] = useState("");

    const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    getCircuitById(id).then((res) => {
      setCircuitId(res.data.circuit_id);
      setSource(res.data.source);
      setDestination(res.data.destination);
    });
  }, [id]);

  const handleUpdate = () => {
    const payload = {
        circuit_id: circuit_id,
        source: source,
        destination: destination,
    };

    updateCircuit(id, payload)
      .then((res) => navigate(-1))
      .then(alert("Details have been modified"))
      .catch((error) => console.log("Something went wrong"));
  };
  
  return (
    <div>
      <div>
        <label>Circuit ID:</label>
        <input
          type="number"
          id="circuit_id"
          name="circuit_id"
          value={circuit_id}
          disabled
        />
      </div>

      <div>
        <label>Source:</label>
        <input
          id="source"
          name="source"
          value={source}
          onChange={(e) => setSource(e.target.value)}
        />
      </div>

      <div>
        <label>Destination:</label>
        <input
          id="destination"
          name="destination"
          value={destination}
          onChange={(e) => setDestination(e.target.value)}
        />
      </div>

    

      <div>
        <button class="btn btn-primary" onClick={handleUpdate}>
          Edit Circuit
        </button>
      </div>
    </div>
  );
}

export default UpdateCircuit