package com.capgemini.oap.service;

import com.capgemini.oap.exception.CircuitException;
import com.capgemini.oap.model.Circuit;
import com.capgemini.oap.repository.CircuitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@Service
@Transactional
public class CircuitServiceImpl implements CircuitService{

    Logger logger= Logger.getLogger(CircuitServiceImpl.class.getName());
    public CircuitServiceImpl(){
    }

    private CircuitRepository circuitRepository;

    @Autowired
    public CircuitServiceImpl(CircuitRepository circuitRepository){
        this.circuitRepository = circuitRepository;
    }

    @Override
    public Circuit addCircuit(Circuit circuit) {

         circuitRepository.save(circuit);
         return circuit;
    }

    @Override
    public List<Circuit> getAllCircuits() {
        logger.info("Adding a circuit");
        return circuitRepository.findAll();
    }

    @Override
    public Circuit getCircuitById(Integer id) {
        Optional<Circuit> circuit= circuitRepository.findById(id);
        if(circuit.isEmpty()) {
            logger.warning("Invalid Circuit ID");
            throw new CircuitException("Invalid Circuit ID");
        }
        logger.info("Getting a circuit by ID");
        return circuit.get();
    }

    @Override
    public Circuit getCircuitBySource(String src) {
        Optional<Circuit> circuit = circuitRepository.findBySource(src);
        if(circuit.isEmpty()){
            logger.warning("Invalid Circuit Source");
            throw new CircuitException("Invalid Circuit Source");
        }
        logger.info("Getting a circuit by Source");
        return circuit.get();
    }

    @Override
    public Circuit getCircuitByDestination(String dest) {
        Optional<Circuit> circuit = circuitRepository.findByDestination(dest);
        if(circuit.isEmpty()){
            logger.warning("Invalid Circuit Source");
            throw new CircuitException("Invalid Circuit Destination");
        }
        logger.info("Getting a circuit by Destination");
        return circuit.get();
    }

    @Override
    public void deleteCircuit(Integer id) {
        Optional<Circuit> circuit= circuitRepository.findById(id);
        if (circuit.isEmpty()) {
            logger.warning("Invalid Circuit ID");
            throw new CircuitException("Invalid Circuit ID");
        }
        logger.info("Deleting a circuit by ID");
        circuitRepository.deleteById(id);
    }

    @Override
    public Circuit updateCircuit(Integer id, Circuit circuit) {
        Optional<Circuit> circuit1 = circuitRepository.findById(id);
        if (circuit1.isEmpty()) {
            logger.warning("Invalid Circuit ID");
            throw new CircuitException("Invalid Circuit ID");
        }
        logger.info("Updating Circuit details");
        circuit1.get().setSource(circuit.getSource());
        circuit1.get().setDestination(circuit.getDestination());
        return circuit1.get();
    }
}
