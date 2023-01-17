import { useCallback, useState, useEffect } from "react";
import { Link } from "react-router-dom";
import ReactFlow, {
  MiniMap,
  Controls,
  Background,
  useNodesState,
  useEdgesState,
  addEdge,
} from "reactflow";
import "reactflow/dist/style.css";
import "../../styles/Network.css";
import { getLinks, getNodes } from "../../services/NodeService";
import AddNode from "./add/AddNode";

function Network() {
  // const initialNodes = [
  //   {
  //     id: "1",
  //     position: { x: 0, y: 0 },
  //     data: { label: "1" },
  //     style: { height: 50, width: 50, borderRadius: 50 },
  //   },
  //   { id: "2", position: { x: 0, y: 100 }, data: { label: "2" } },
  // ];

  // const initialEdges = [{ id: "x", source: "1", target: "2" }];

  const [nodes, setNodes, onNodesChange] = useNodesState([]);
  const [edges, setEdges, onEdgesChange] = useEdgesState([]);

  const [openModal, setOpenModal] = useState(false);

  const user = JSON.parse(localStorage.getItem("loggedInUser"));

  useEffect(() => {
    getNodes().then((res) => {
      setNodes(
        res.data.map((n) => ({
          id: n.node_id.toString(),
          position: { x: n.xposition, y: n.yposition },
          data: { label: n.type },
          style: {
            height: "75px",
            width: "75px",
            borderRadius: "50%",
            backgroundColor:
              n.type === "ILA"
                ? "#F05454"
                : n.type === "Passthrough"
                ? "#263859"
                : "#519872",
            color: "#FFFF",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
          },
        }))
      );
    });
  }, [getNodes, setNodes]);

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

  const calculate = (e) => {
    const temp = [];

    nodes.forEach((node) => {
      const type = node.data.label;
      var amp = "";
      if (type === "ILA") {
        amp = "Booster Variant2";
      } else if (type === "Passthrough") {
        amp = "No Amplfier";
      } else if (type === "AddDrop") {
        amp ="Prebooster Variant1";
      } else if (type==="Booster Variant2"){
        amp="ILA"
      } else if(type ==="No Amplfier"){
        amp= "Passthrough"
      } else if(type==="Prebooster Variant1"){
        amp="AddDrop"
      }

      const l = amp;
      const val = {
        ...node,
        data: { label: l },
      };
      temp.push(val);
    });
    setNodes(temp);
  };

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
            onClick={(e) => calculate(e)}
          >
            Toggle Amplifiers
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

export default Network;
