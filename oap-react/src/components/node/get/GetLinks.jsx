import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { deleteLink, getLinks } from "../../../services/NodeService";

function GetLinks() {
  const [links, setLinks] = useState([]);

  const navigate = useNavigate();
  useEffect(() => {
    getLinks().then((res) => {
      setLinks(res.data);
    });
  }, []);

  function handleDelete(id) {
    deleteLink(id).then((res) => {
      alert("Link with ID " + id + " has been deleted!");
    });
  }

  return (
    <>
      <h1>Links</h1>
      <div class="vh-100">
        <div class="container ">
          <div class="table-responsive ">
            <table class="table table-bordered">
              <thead class="table-dark">
                <tr>
                  <th>Link ID</th>
                  <th>Link Name</th>
                  <th>Length</th>
                  <th>From Node</th>
                  <th>To Node</th>
                  <th>Update</th>
                  <th>Delete</th>
                </tr>
              </thead>
              <tbody>
                {links.map((l) => (
                  <tr key={l.link_id}>
                    <td>{l.link_id}</td>
                    <td>{l.name}</td>
                    <td>{l.length}</td>
                    <td>{l.from_node}</td>
                    <td>{l.to_node}</td>
                    <td>
                      <Link to={`/link/update/${l.link_id}`}>Update</Link>
                    </td>
                    <td>
                      <button
                        className="btn btn-outline"
                        onClick={() => {
                          handleDelete(l.link_id);
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
          <Link class="btn btn-primary" to={"/link/add"}>Add Link</Link>
          <button
            type="button"
            class="btn btn-danger"
            onClick={() => {
              navigate(-1);
            }}
          >
            Back
          </button>
        </div>
      </div>
    </>
  );
}

export default GetLinks;
