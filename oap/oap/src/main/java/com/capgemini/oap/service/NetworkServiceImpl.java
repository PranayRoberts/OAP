package com.capgemini.oap.service;

import com.capgemini.oap.exception.NetworkException;
import com.capgemini.oap.model.Network;
import com.capgemini.oap.repository.NetworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@Transactional
public class NetworkServiceImpl implements NetworkService  {

    Logger logger = Logger.getLogger(NetworkServiceImpl.class.getName());
    private NetworkRepository networkRepository;

    @Autowired private NodeServiceImpl nodeService;
    @Autowired private LinkServiceImpl linkService;
    @Autowired private CircuitServiceImpl circuitService;

    public NetworkServiceImpl(){}
    @Autowired
    public NetworkServiceImpl(NetworkRepository networkRepository) {
        this.networkRepository = networkRepository;
    }

    @Override
    public Network addNetwork(Network network){
        logger.info("Adding Network");
        Network savedNetwork = networkRepository.save(network);
        network.getNodes().forEach(node -> {
            node.setNetwork(savedNetwork);
            nodeService.addNode(node);
        });
        network.getLinks().forEach(link -> {
            link.setNetwork(savedNetwork);
            linkService.addLink(link);
        });
//        network.getCircuit().setNetwork(savedNetwork);
//        circuitService.addCircuit(network.getCircuit());
        return network;
    }

    @Override
    public List<Network> getAllNetworks(){
        logger.info("Getting all Networks");
        return networkRepository.findAll();
    }

    @Override
    public Network getNetworkById(Integer id){
        Optional<Network> network = networkRepository.findById(id);
        if (network.isEmpty()) {
            logger.warning("Invalid Network ID");
            throw new NetworkException("Invalid Network ID");
        }
        logger.info("Getting Network By ID");
        return network.get();
    }

//    @Override
//    public Network getNetworkByName(String name){
//
//    }

    @Override
    public void deleteNetwork(Integer id){
        Optional<Network> network = networkRepository.findById(id);
        if (network.isEmpty()) {
            logger.warning("Invalid Network ID");
            throw new NetworkException("Invalid Network ID");
        }
        logger.info("Deleting Network By ID");
        networkRepository.deleteById(id);
    }

    @Override
    public Network updateNetwork(Integer id, Network network){
        Optional<Network> network1=networkRepository.findById(id);
        if(network1.isEmpty()) {
            logger.warning("Invalid Network ID");
            throw new NetworkException("Invalid Network ID");
        }
        logger.info("Updating Network");
        network1.get().setNetwork_name(network.getNetwork_name());
        return network1.get();
    }
}
