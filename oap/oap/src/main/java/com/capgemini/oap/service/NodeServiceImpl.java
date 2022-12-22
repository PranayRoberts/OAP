package com.capgemini.oap.service;

import com.capgemini.oap.exception.NodeException;
import com.capgemini.oap.model.Node;
import com.capgemini.oap.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@Transactional
public class NodeServiceImpl implements NodeService{
    Logger logger = Logger.getLogger(NodeServiceImpl.class.getName());
    public NodeServiceImpl() {
    }
    private NodeRepository nodeRepository;

    @Autowired EdgeServiceImpl edgeService;

    @Autowired
    public NodeServiceImpl(NodeRepository nodeRepository){
        this.nodeRepository= nodeRepository;
    }

    @Override
    public Node addNode(Node node) {
        Node savedNode = nodeRepository.save(node);
        node.getEdges().forEach(edge -> {
            edge.setNode(savedNode);
            edgeService.addEdge(edge);
        });
        return node;
    }
    @Override
    public List<Node> getAllNodes() {

        logger.info("Getting all Nodes");
        return nodeRepository.findAll();
    }

    @Override
    public Node getNodeById(Integer id) throws NodeException {
        Optional<Node> node = nodeRepository.findById(id);
        if (node.isEmpty()) {
            logger.warning("Invalid Node ID");
            throw new NodeException("Invalid Node ID");
        }
        logger.info("Getting Node By ID");
        return node.get();
    }

    @Override
    public Node getNodeByName(String name) throws NodeException {
        Optional<Node> node=nodeRepository.findByNameIgnoreCase(name);
        if(node.isEmpty()) {
            logger.warning("Invalid Node Name");
            throw new NodeException("Invalid Node Name");
        }
        return node.get();
    }

    @Override
    public void deleteNode(Integer id) {
        Optional<Node> optionalNode= nodeRepository.findById(id);
        if (optionalNode.isEmpty()) {
            logger.warning("Invalid Node ID");
            throw new NodeException("Invalid Node ID");
        }
        logger.info("Deleting Node By ID");
        nodeRepository.deleteById(id);
    }

    @Override
    public Node updateNode(Integer id, Node node) throws NodeException {
        Optional<Node> node1=nodeRepository.findById(id);
        if(node1.isEmpty()) {
            logger.warning("Invalid Node ID");
            throw new NodeException("Invalid Node ID");
        }
        logger.info("Updating Node");
        node1.get().setName(node.getName());
        node1.get().setIp(node.getIp());
        node1.get().setPassword(node.getPassword());
        node1.get().setType(node.getType());
        node1.get().setXPosition(node.getXPosition());
        node1.get().setYPosition(node.getYPosition());
        return node1.get();
    }
}
