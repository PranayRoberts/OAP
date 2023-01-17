import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { deleteNetwork, getNetworks } from "../../../services/NodeService";

function GetNetworks() {
  const [networks, setNetworks] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    getNetworks().then((res) => {
      setNetworks(res.data);
    });
  }, []);

  function handleDelete(id){
    deleteNetwork(id).then((res)=> {
        alert("Network with ID " + id + " has been deleted" )
    })
  }


  return <>
  <h1>Networks</h1>
  <div class="vh-100">
    <div class="container ">
      <div class="table-responsive ">
        <table class="table table-bordered">
          <thead class="table-dark">
            <tr>
              <th>Network ID</th>
              <th>Network Name</th>
              <th>For User</th>
              <th>View</th>
              <th>Delete</th>
            </tr>
          </thead>
          <tbody>
            {networks.map((n) => (
              <tr key={n.network_id}>
                <td>{n.network_id}</td>
                <td>{n.network_name}</td>
                <td>{n.username}</td>
                <td>
                      <Link to={'/network'}>View</Link>
                    </td>
                <td>
                  <button
                    className="btn btn-outline"
                    onClick={() => {
                      handleDelete(n.network_id);
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
}

export default GetNetworks;
