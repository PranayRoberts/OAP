package com.capgemini.oap.service;
import com.capgemini.oap.exception.EdgeException;
import com.capgemini.oap.model.Edge;
import com.capgemini.oap.repository.EdgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Transactional
@Service
public class EdgeServiceImpl implements EdgeService {

    Logger logger = Logger.getLogger(EdgeServiceImpl.class.getName());
    public EdgeServiceImpl(){}
    private EdgeRepository edgeRepository;
    @Autowired
    public EdgeServiceImpl(EdgeRepository edgeRepository) {
        this.edgeRepository = edgeRepository;
    }@Override

    public Edge addEdge(Edge edge) {
        logger.info("Add a new Edge");
        edgeRepository.save(edge);
        return edge;
    }
    @Override
    public List<Edge> getAllEdges() {
        logger.info("Finding all Edges");
        return edgeRepository.findAll();
    }
    @Override
    public Edge getEdgeById(Integer id) {
        Optional<Edge> edge = edgeRepository.findById(id);
        if (edge.isEmpty()) {
            logger.warning("Invalid Edge ID");
            throw new EdgeException("Invalid Edge ID");
        }
        logger.info("Finding Edge By ID");
        return edge.get();
    }
    @Override
    public Edge getEdgeByName(Character name) {
        Optional<Edge> edge = edgeRepository.findByName(name);
        if(edge.isEmpty()) {
            logger.warning("Invalid Edge Name");
            throw new EdgeException("Invalid Edge Name");
        }
        logger.info("Finding Edge By Name");
        return edge.get();
    }
    @Override
    public void deleteEdge(Integer id) {
        Optional<Edge> edge = edgeRepository.findById(id);
        if(edge.isEmpty()) {
            logger.warning("Invalid Edge ID");
            throw new EdgeException("Invalid Edge ID");
        }
        logger.info("Deleting Edge By ID");
        edgeRepository.deleteById(id);
    }
    @Override
    public Edge updateEdge(Integer id, Edge edge) {
        Optional<Edge> edge1= edgeRepository.findById(id);
        if(edge1.isEmpty()) {
            logger.warning("Invalid Edge ID");
            throw new EdgeException("Invalid Edge ID");
        }
        logger.info("Updating Edge details");
        edge1.get().setName(edge.getName());
        edge1.get().setIsAvailable(edge.getIsAvailable());
        return edge1.get();
    }
}
