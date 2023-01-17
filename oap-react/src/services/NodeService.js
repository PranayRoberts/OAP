import { baseUrl, config } from "../util/AppConstants";
import axios from "axios";

//Nodes
export function getNodes() {
  return axios.get(baseUrl + "/node/get-node");
}

export function addNode(node) {
  return axios.post(baseUrl + "/node/add-node", node);
}

export function updateNode(id, node) {
  return axios.put(baseUrl + "/node/update-node?id=" + id, node);
}

export function deleteNode(id) {
  return axios.delete(baseUrl + "/node/delete-node?id=" + id);
}

export function getNodeById(id) {
  return axios.get(baseUrl + "/node/get-node-by-id/" + id);
}

export function getNodeByName(name) {
  return axios.get(baseUrl + "/node/get-node-by-name/" + name);
}

//Edges
export function getEdges() {
  return axios.get(baseUrl + "/edge/get-edge");
}

export function getEdgeById(id) {
  return axios.get(baseUrl + "/edge/get-edge-by-id/" + id);
}

export function getEdgeByName(name) {
  return axios.get(baseUrl + "/edge/get-edge-by-name/" + name);
}

export function addEdge(edge) {
  return axios.post(baseUrl + "/edge/add-edge", edge);
}

export function updateEdge(id, edge) {
  return axios.put(baseUrl + "/edge/update-edge?id=" + id, edge);
}

export function deleteEdge(id) {
  return axios.delete(baseUrl + "/edge/delete-edge?id=" + id);
}

//Links
export function getLinks() {
  return axios.get(baseUrl + "/link/get-link");
}

export function getLinkById(id) {
  return axios.get(baseUrl + "/link/get-link-by-id/" + id);
}

export function getLinkByName(name) {
  return axios.get(baseUrl + "/link/get-link-by-name/" + name);
}

export function addLink(link) {
  return axios.post(baseUrl + "/link/add-link", link);
}

export function updateLink(id, link) {
  return axios.put(baseUrl + "/link/update-link?id=" + id, link);
}

export function deleteLink(id) {
  return axios.delete(baseUrl + "/link/delete-link?id=" + id);
}

//Circuit
export function getCircuits() {
  return axios.get(baseUrl + "/controller/get-circuit");
}

export function getCircuitById(id) {
  return axios.get(baseUrl + "/controller/get-circuit-by-id/" + id);
}

export function getCircuitBySource(src) {
  return axios.get(baseUrl + "/controller/get-circuit-by-source/" + src);
}

export function getCircuitByDestination(dest) {
  return axios.get(baseUrl + "/controller/get-circuit-by-destination/" + dest);
}

export function addCircuit(circuit) {
  return axios.post(baseUrl + "/controller/add-circuit", circuit);
}

export function updateCircuit(id, circuit) {
  return axios.put(baseUrl + "/controller/update-circuit?id=" + id, circuit);
}

export function deleteCircuit(id) {
  return axios.delete(baseUrl + "/controller/delete-circuit?id=" + id);
}

//Cards
export function getCards() {
  return axios.get(baseUrl + "/card/get-cards");
}

export function getCardById(id) {
  return axios.get(baseUrl + "/card/get-cards-by-id/" + id);
}

export function getCardByName(name) {
  return axios.get(baseUrl + "/card/get-cards-by-name/" + name);
}

export function addCard(card) {
  return axios.post(baseUrl + "/card/add-card", card);
}

export function updateCard(id, card) {
  return axios.put(baseUrl + "/card/update-card?card_id=" + id, card);
}

export function deleteCard(id) {
  return axios.delete(baseUrl + "/card/delete-card?card_id=" + id);
}

//Network
export function addNetwork(network){
  return axios.post(baseUrl + "/network/add-network", network)
}

export function getNetworks(){
  return axios.get(baseUrl + "/network/get-network")
}

export function getNetworkByUsername(username){
  return axios.get(baseUrl + "/network/get-network-by-name?name=" +username)
}

export function deleteNetwork(id){
  return axios.delete(baseUrl + "/network/delete-network?id="+id)
}


//Algorithm
export function optimumAmplifier(node_id, link_id, card_id){
  return axios.put(baseUrl + `/algorithm/optimum-placement?node_id=${node_id}&link_id=${link_id}&card_id=${card_id}`)
}