package com.capgemini.oap.service;

import com.capgemini.oap.exception.NetworkException;
import com.capgemini.oap.model.Network;
import com.capgemini.oap.repository.NetworkRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class NetworkServiceTest {

    @InjectMocks
    private NetworkService networkService= new NetworkServiceImpl();
    @Mock
    private NetworkRepository networkRepository;

    private Network n1 = new Network();
    private Network n2 = new Network();

    private final List<Network> list= new ArrayList<>();
    {
        n1.setNetwork_id(1);
        n1.setNetwork_name("Network1");
        n1.setUsername("harika");
        n2.setNetwork_id(2);
        n2.setNetwork_name("Network2");
        n2.setUsername("pranay");
        list.addAll(List.of(n1, n2));
    }
    @Test
    void addNetwork(){
        when(networkRepository.save(n1)).thenReturn(n1);
        Network network = networkService.addNetwork(n1);
        assertThat(network.getNetwork_name()).isEqualTo(n1.getNetwork_name());

//        Network network = new Network();
//        network.setNetwork_id(1);
//        String network_name = "Network1";
//        network.setNetwork_name(network_name);
//        String username = "User1";
//        network.setUsername(username);
//
//        when(networkRepository.save(network)).thenReturn(network);
//
//        Network newNetwork = networkService.addNetwork(network);
//
//        assertThat(newNetwork.getNetwork_name()).isEqualTo(network_name);
//        assertThat(newNetwork.getUsername()).isEqualTo(username);
    }
    @Test
    void getAllNetworks(){
        when(networkRepository.findAll()).thenReturn(list);
        List<Network> networkList = networkService.getAllNetworks();
        assertThat(networkList.size()).isEqualTo(2);
    }
    @Test
    void getNetworkById(){
        int id = 1;
        when(networkRepository.findById(id)).thenReturn(Optional.of(n1));
        Network network = networkService.getNetworkById(id);
        assertThat(network.getNetwork_id().equals(id));
    }
    @Test
    void getNetworkByIdWithException(){
        NetworkException networkException=assertThrows(NetworkException.class,()->networkService.getNetworkById(1));
        assertEquals("Invalid Network ID",networkException.getMessage());
    }
    @Test
    void deleteNetwork(){
        Network network=new Network();
        network.setNetwork_id(3);
        network.setNetwork_name("Network3");
        Optional<Network> optionalNetwork=Optional.of(network);
        when(networkRepository.findById(3)).thenReturn(optionalNetwork);
        networkService.getNetworkById(3);
        networkService.deleteNetwork(3);
        verify(networkRepository, times(1)).deleteById(3);
    }
    @Test
    void deleteNetworkWithException(){
        NetworkException networkException=assertThrows(NetworkException.class,()->networkService.deleteNetwork(1));
        assertEquals("Invalid Network ID",networkException.getMessage());
    }
    @Test
    void updateNetwork(){
        int id = 2;
        when(networkRepository.findById(id)).thenReturn(Optional.of(n2));
        Network temp=n2;
        String Network_name = "Network2";
        temp.setNetwork_name(Network_name);
        Network network = networkService.updateNetwork(id,n2);
        assertThat(network.getNetwork_name()).isEqualTo(Network_name);
    }
    @Test
    void updateNetworkWithException(){
        NetworkException networkException=assertThrows(NetworkException.class,()->networkService.updateNetwork(1,n1));
        assertEquals("Invalid Network ID",networkException.getMessage());
    }
    @Test
    void exceptionTest(){
        int id=15;
        when(networkRepository.findById(id)).thenThrow(NetworkException.class);
        assertThrows(NetworkException.class,()->networkService.getNetworkById(id));
    }
}
