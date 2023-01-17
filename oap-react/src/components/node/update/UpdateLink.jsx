import React from "react";
import { useEffect } from "react";
import { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getLinkById, updateLink } from "../../../services/NodeService";

function UpdateLink() {
  const [link_id, setLinkId] = useState(0);
  const [name, setName] = useState("");
  const [length, setLength] = useState(0);
  const [from_node, setFromNode] = useState("");
  const [to_node, setToNode] = useState("");

  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    getLinkById(id).then((res) => {
      setLinkId(res.data.link_id);
      setName(res.data.name);
      setLength(res.data.length);
      setFromNode(res.data.from_node);
      setToNode(res.data.to_node);
    });
  }, [id]);

  const handleUpdate = () => {
    const payload = {
      link_id: link_id,
      name: name,
      length: length,
      from_node: from_node,
      to_node: to_node,
    };

    updateLink(id, payload)
      .then((res) => navigate(-1))
      .then(alert("Details have been modified"))
      .catch((error) => console.log("Something went wrong"));
  };

  return <div>
  <div>
    <label>Link ID:</label>
    <input
      type="number"
      id="link_id"
      name="link_id"
      value={link_id}
      disabled
    />
  </div>

  <div>
    <label>Link Name:</label>
    <input
      id="name"
      name="name"
      value={name}
      onChange={(e) => setName(e.target.value)}
    />
  </div>

  <div>
    <label>Length:</label>
    <input
      type="number"
      id="length"
      name="length"
      value={length}
      onChange={(e) => setLength(e.target.value)}
    />
  </div>

  <div>
    <label>From Node:</label>
    <input
      id="from_node"
      name="from_node"
      value={from_node}
      onChange={(e) => setFromNode(e.target.value)}
    />
  </div>

  <div>
    <label>To Node:</label>
    <input
      id="to_node"
      name="to_node"
      value={to_node}
      onChange={(e) => setToNode(e.target.value)}
    />
  </div>

  <div>
    <button class="btn btn-primary" onClick={handleUpdate}>
      Edit Link
    </button>
  </div>
</div>;
}

export default UpdateLink;
