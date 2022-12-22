package com.capgemini.oap.service;

import com.capgemini.oap.model.Network;

import java.util.List;

public interface NetworkService {

    public Network addNetwork(Network network);
    public List<Network> getAllNetworks();
    public Network getNetworkById(Integer id);
//    public Network getNetworkByName(String name);
    public void deleteNetwork(Integer id);
    public Network updateNetwork(Integer id, Network network);

}
