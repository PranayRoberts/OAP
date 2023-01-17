import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { deleteEdge, getEdges } from "../../../services/NodeService";

function GetEdges() {
  const [edges, setEdges] = useState([]);

  const navigate = useNavigate()
  
  useEffect(() => {
    getEdges().then((res) => {
      setEdges(res.data);
    });
  }, []);

  function handleDelete(id) {
    deleteEdge(id).then((res) => {
      alert("Edge with ID " + id + " has been deleted!");
    });
  }
  return (
    <>
      <h1>Edges</h1>
      <div class="vh-100">
        <div class="container ">
          <div class="table-responsive ">
            <table class="table table-bordered">
              <thead class="table-dark">
                <tr>
                  <th>Edge ID</th>
                  <th>Edge Name</th>
                  <th>Is the edge Available?</th>
                  <th>Update</th>
                  <th>Delete</th>
                </tr>
              </thead>
              <tbody>
                {edges.map((e) => (
                  <tr key={e.edge_id}>
                    <td>{e.edge_id}</td>
                    <td>{e.name}</td>
                    <td>{e.isAvailable.toString()}</td>
                    <td>
                      <Link to={`/edge/update/${e.edge_id}`}>Update</Link>
                    </td>
                    <td>
                      <button
                        className="btn btn-outline"
                        onClick={() => {
                          handleDelete(e.edge_id);
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
          <Link class="btn btn-primary" to={"/edge/add"}>Add Edge</Link>
          <button
              type="button"
              class="btn btn-danger"
              onClick= {() => { navigate(-1)}}
            >
              Back
            </button>
        </div>
      </div>
    </>
  );
}

export default GetEdges;
