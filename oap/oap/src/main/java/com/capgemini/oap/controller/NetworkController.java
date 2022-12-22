package com.capgemini.oap.controller;

import com.capgemini.oap.model.Network;
import com.capgemini.oap.repository.NetworkRepository;
import com.capgemini.oap.service.NetworkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/network")
public class NetworkController {

    private NetworkService networkService;

    private NetworkRepository networkRepository;

    public NetworkController(NetworkService networkService, NetworkRepository networkRepository){

        this.networkService = networkService;
        this.networkRepository = networkRepository;
    }

    @PostMapping("/add-network")
    public Network addNetwork(@RequestBody Network network){ return networkService.addNetwork(network);}

    @GetMapping("/get-network")
    public ResponseEntity<List<Network>> getAllNetworks(){ return ResponseEntity.ok().body(networkService.getAllNetworks());}

    @GetMapping("/get-network-by-id/{id}")
    public Network getNetworkById(@RequestParam Integer id){return networkService.getNetworkById(id);}

//    @GetMapping("/get-network-by-name/{name}")
//    public Network getNetworkByName(@RequestParam String name){return networkService.getNetworkByName(name);}

    @DeleteMapping("/delete-network")
    public void deleteNetwork(@RequestParam(required = true) Integer id){
        networkService.deleteNetwork(id);}

    @PutMapping("/update-network")
    public Network updateNetwork(@RequestParam Integer id,
                                 @RequestBody Network network){
        return networkService.updateNetwork(id, network);}
}
