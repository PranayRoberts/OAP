import logo from "./logo.svg";
import "./App.css";

import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import RegisterUser from "./components/user/register/RegisterUser";
import Home from "./pages/Home";
import UserLogin from "./components/user/login/UserLogin";
import UserDashboard from "./components/user/UserDashboard";
import GetUser from "./components/user/GetUser";
import Network from "./components/node/Network";
import Footer from "./pages/Footer";
import GetNodes from "./components/node/get/GetNodes";
import GetEdges from "./components/node/get/GetEdges";
import GetLinks from "./components/node/get/GetLinks";
import GetCircuits from "./components/node/get/GetCircuits";
import GetCards from "./components/node/get/GetCards";
import UpdateNode from "./components/node/update/UpdateNode";
import AddEdge from "./components/node/add/AddEdge";
import AddLink from "./components/node/add/AddLink";
import AddCircuit from "./components/node/add/AddCircuit";
import AddCard from "./components/node/add/AddCard";
import UpdateEdge from "./components/node/update/UpdateEdge";
import UpdateLink from "./components/node/update/UpdateLink";
import UpdateCircuit from "./components/node/update/UpdateCircuit";
import UpdateCard from "./components/node/update/UpdateCard";
import Optimum from "./components/node/algorithm/Optimum";
import AddNetwork from "./components/node/add/AddNetwork";
import GetNetworks from "./components/node/get/GetNetworks";
import GetNetwork from "./components/node/get/GetNetwork";

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/user/register" element={<RegisterUser />} />
          <Route path="/user/login" element={<UserLogin />} />
          <Route path="/user/dashboard" element={<UserDashboard />} />
          <Route path="/user/:userName" element={<GetUser />} />
          <Route path="/network" element={<Network />} />
          <Route path="/network/:username" element={<GetNetwork />} />
          <Route path="/network/get" element={<GetNetworks />} />
          <Route path="/network/add" element={<AddNetwork />} />
          <Route path="/node/get" element={<GetNodes />} />
          <Route path="/node/update/:id" element={<UpdateNode />} />
          <Route path="/edge/get" element={<GetEdges />} />
          <Route path="/edge/add" element={<AddEdge />} />
          <Route path="/edge/update/:id" element={<UpdateEdge />} />
          <Route path="/link/get" element={<GetLinks />} />
          <Route path="/link/add" element={<AddLink />} />
          <Route path="/link/update/:id" element={<UpdateLink />} />
          <Route path="/circuit/get" element={<GetCircuits />} />
          <Route path="/circuit/add" element={<AddCircuit />} />
          <Route path="/circuit/update/:id" element={<UpdateCircuit />} />
          <Route path="/card/get" element={<GetCards />} />
          <Route path="/card/add" element={<AddCard />} />
          <Route path="/card/update/:id" element={<UpdateCard />} />
          <Route path="/optimum" element={<Optimum />} />

        </Routes>
        {/* <Footer /> */}
      </Router>
    </div>
  );
}

export default App;
