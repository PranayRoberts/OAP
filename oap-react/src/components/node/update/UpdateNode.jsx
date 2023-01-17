import React from "react";
import { useEffect } from "react";
import { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getNodeById, updateNode } from "../../../services/NodeService";

function UpdateNode() {
  const [node_id, setNodeId] = useState(0);
  const [name, setName] = useState("");
  const [password, setPassword] = useState("");
  const [ip, setIp] = useState("");
  const [type, setType] = useState("");
  const [xposition, setXPosition] = useState(0);
  const [yposition, setYPosition] = useState(0);

  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    getNodeById(id).then((res) => {
      setNodeId(res.data.node_id);
      setName(res.data.name);
      setIp(res.data.ip);
      setPassword(res.data.password);
      setType(res.data.type);
      setXPosition(res.data.xposition);
      setYPosition(res.data.yposition);
    });
  }, [id]);

  const handleUpdate = () => {
    const payload = {
      node_id: node_id,
      name: name,
      ip: ip,
      password: password,
      type: type,
      xposition: xposition,
      yposition: yposition,
    };
    updateNode(id, payload)
      .then((res) => navigate(-1))
      .then(alert("Details have been modified"))
      .catch((error) => console.log("Something went wrong"));
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
          disabled
        />
      </div>

      <div>
        <label>Node Name:</label>
        <input
          type="text"
          id="name"
          name="name"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
      </div>

      <div>
        <label>Node IP:</label>
        <input
          type="text"
          id="ip"
          name="ip"
          value={ip}
          onChange={(e) => setIp(e.target.value)}
        />
      </div>

      <div>
        <label>Node Type:</label>
        <input
          type="text"
          id="type"
          name="type"
          value={type}
          onChange={(e) => setType(e.target.value)}
        />
      </div>

      <div>
        <label>Node Password:</label>
        <input
          type="password"
          id="password"
          name="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
      </div>

      <div>
        <label>X Positon:</label>
        <input
          type="number"
          id="xposition"
          name="xposition"
          value={xposition}
          onChange={(e) => setXPosition(e.target.value)}
        />
      </div>

      <div>
        <label>Y Positon:</label>
        <input
          type="number"
          id="yposition"
          name="yposition"
          value={yposition}
          onChange={(e) => setYPosition(e.target.value)}
        />
      </div>

      <div>
        <button class="btn btn-primary" onClick={handleUpdate}>
          Edit Node
        </button>
      </div>
    </div>
  );
}

export default UpdateNode;
