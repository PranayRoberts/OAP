import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { deleteNode, getNodes } from "../../../services/NodeService";

function GetNodes() {
  const [nodes, setNodes] = useState([]);

  const navigate = useNavigate()
  useEffect(() => {
    getNodes().then((res) => {
      setNodes(res.data);
    });
  }, []);

  function handleDelete(id) {
    deleteNode(id).then((res) => {
      alert("Node with ID " + id + " has been deleted!");
    });
  }

  return (
    <>
      <h1>Nodes</h1>
      <div class="vh-100">
        <div class="container ">
          <div class="table-responsive">
            <table class="table table-bordered">
              <thead class="table-dark">
                <tr>
                  <th>Node ID</th>
                  <th>Node Name</th>
                  <th>Node IP</th>
                  <th>Node Type</th>
                  <th>X Postion</th>
                  <th>Y Position</th>
                  <th>Update</th>
                  <th>Delete</th>
                </tr>
              </thead>
              <tbody>
                {nodes.map((n) => (
                  <tr key={n.node_id}>
                    <td>{n.node_id}</td>
                    <td>{n.name}</td>
                    <td>{n.ip}</td>
                    <td>{n.type}</td>
                    <td>{n.xposition}</td>
                    <td>{n.yposition}</td>
                    <td>
                      <Link to={`/node/update/${n.node_id}`}>Update</Link>
                    </td>
                    <td>
                      <button
                        className="btn btn-outline"
                        onClick={() => {
                          handleDelete(n.node_id);
                        }}
                      >
                        🗑️
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
        <button
              type="button"
              class="btn btn-danger"
              onClick= {() => { navigate(-1)}}
            >
              Back
            </button>
      </div>
    </>
  );
}

export default GetNodes;
