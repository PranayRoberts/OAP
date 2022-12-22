package com.capgemini.oap.service;

import com.capgemini.oap.model.Node;

import java.util.List;


public interface NodeService {

    public Node addNode(Node node);
    public List<Node> getAllNodes();
    public Node getNodeById(Integer id);
    public Node getNodeByName(String name);
    public void deleteNode(Integer id);
    public Node updateNode(Integer id, Node node);
}
