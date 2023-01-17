import { useCallback, useState, useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import ReactFlow, {
  MiniMap,
  Controls,
  Background,
  useNodesState,
  useEdgesState,
  addEdge,
} from "reactflow";
import "reactflow/dist/style.css";
import "../../../styles/Network.css";
import { getLinks, getNetworkByUsername, getNodes } from "../../../services/NodeService";
import AddNode from ".././add/AddNode";

function GetNetwork() {
  const [nodes, setNodes, onNodesChange] = useNodesState([]);
  const [edges, setEdges, onEdgesChange] = useEdgesState([]);

  const [openModal, setOpenModal] = useState(false);

  const { username } = useParams()
  const user = JSON.parse(localStorage.getItem("loggedInUser"));

  useEffect(() => {
    getNetworkByUsername(username).then((res) => {
      setNodes(
        res.data.map((n) => ({
          id: n.nodes.node_id.toString(),
          position: { x: n.nodes.xposition, y: n.nodes.yposition },
          data: { label: "ID: "+n.nodes.node_id + "\n" + n.nodes.type },
          style: {
            height: "75px",
            width: "75px",
            borderRadius: "50%",
            backgroundColor: n.nodes.type==="ILA" ? "#F05454" : (n.nodes.type ==="Passthrough" ? "#263859" : "#519872"),
            color: "#FFFF",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
          },
        }))
      );
    });
  }, []);

  useEffect(() => {
    getLinks().then((res) => {
      setEdges(
        res.data.map((l) => ({
          id: l.link_id.toString(),
          source: l.from_node,
          target: l.to_node,
          label: l.to_node + " => " + l.from_node,
        }))
      );
    });
  }, []);

  const onConnect = useCallback(
    (params) => setEdges((eds) => addEdge(params, eds)),
    [setEdges]
  );

  const calculate = () =>{
    
  }

  return (
    <>
      <div
        style={{
          width: "100%",
          height: "100vh",
          backgroundColor: "#FFFFFF",
          position: "relative",
        }}
      >
        <div className="network-nav">
          <Link className="network-links btn btn-dark" to={"/user/dashboard"}>
            Welcome {user}!
          </Link>

          <button
            className="btn btn-light openModalButton network-links"
            onClick={() => {
              setOpenModal(true);
            }}
          >
            Add Node
          </button>
          {openModal && <AddNode closeAddNode={setOpenModal} />}

          <Link className="network-links btn btn-light" to={"/node/get"}>
            View Nodes
          </Link>
          <Link className="network-links btn btn-light" to={"/edge/get"}>
            View Edges
          </Link>
          <Link className="network-links btn btn-light" to={"/link/get"}>
            View Links
          </Link>
          <Link className="network-links btn btn-light" to={"/card/get"}>
            View Cards
          </Link>
          <Link className="network-links btn btn-light" to={"/circuit/get"}>
            View Circuits
          </Link>
          <Link className="network-links btn btn-light" to={"/optimum"}>
           Optimum Amplifier
          </Link>

          <button
            className="btn btn-light openModalButton network-links"
            onClick={calculate}
          >
            Calculate
          </button>
        </div>
        <div style={{ height: "85vh" }}>
          <ReactFlow
            nodes={nodes}
            edges={edges}
            onNodesChange={onNodesChange}
            onEdgesChange={onEdgesChange}
            onConnect={onConnect}
          >
            <MiniMap />
            <Controls />
            <Background />
          </ReactFlow>
        </div>
      </div>
    </>
  );
}

export default GetNetwork;
