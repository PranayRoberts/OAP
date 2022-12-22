package com.capgemini.oap.service;
import com.capgemini.oap.exception.NodeException;
import com.capgemini.oap.model.Node;
import com.capgemini.oap.repository.NodeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class NodeServiceTest {
    @InjectMocks
    private NodeService nodeService= new NodeServiceImpl();
    @Mock
    private NodeRepository nodeRepository;

    @Test
    void addNode(){
        Node node = new Node();
        node.setNode_id(16);
        String name = "A";
        node.setName(name);
        String ip = "192.168.1.1";
        node.setIp(ip);
        String password = "r@34";
        node.setPassword(password);
        String type = "Pass-Through";
        node.setType(type);
        int xPosition = 100;
        node.setXPosition(xPosition);
        int yPosition = 100;
        node.setYPosition(yPosition);

        when(nodeRepository.save(node)).thenReturn(node);

        Node newNode = nodeService.addNode(node);

        assertThat(newNode.getIp()).isEqualTo(ip);
        assertThat(newNode.getName()).isEqualTo(name);
        assertThat(newNode.getPassword()).isEqualTo(password);
        assertThat(newNode.getType()).isEqualTo(type);
        assertThat(newNode.getXPosition()).isEqualTo(xPosition);
        assertThat(newNode.getYPosition()).isEqualTo(yPosition);


    }

    @Test
    void deleteNode(){

        Node node = new Node();
        int id = 16;
        node.setNode_id(id);
        String name = "A";
        node.setName(name);
        String ip = "192.168.1.1";
        node.setIp(ip);
        String password = "r@34";
        node.setPassword(password);
        String type = "Pass-Through";
        node.setType(type);
        int xPosition = 100;
        node.setXPosition(xPosition);
        int yPosition = 100;
        node.setYPosition(yPosition);

        when(nodeRepository.findById(id)).thenReturn(Optional.of(node));

        nodeService.deleteNode(id);

        verify(nodeRepository, times(1)).findById(id);
        verify(nodeRepository, times(1)).deleteById(id);

        when(nodeRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NodeException.class,()->nodeService.deleteNode(id));

    }

    @Test
    void getAllNodes(){

        Node node1 = new Node();
        node1.setNode_id(16);
        node1.setName("A");
        node1.setIp("192.168.1.1");
        node1.setPassword("r@34");
        node1.setType("Pass-Through");
        node1.setXPosition(100);
        node1.setYPosition(100);

        Node node2 = new Node();
        node2.setNode_id(17);
        node2.setName("B");
        node2.setIp("192.168.2.1");
        node2.setPassword("rda@34");
        node2.setType("Add-Drop");
        node2.setXPosition(200);
        node2.setYPosition(200);

        List<Node> list = new ArrayList<>();
        list.add(node1);
        list.add(node2);

        when(nodeRepository.findAll()).thenReturn(list);

        List<Node> allNode = nodeService.getAllNodes();

        assertThat(allNode.size()).isEqualTo(2);

    }

    @Test
    void getNodeById(){

        Node node = new Node();
        int id = 16;
        node.setNode_id(id);
        String name = "A";
        node.setName(name);
        String ip = "192.168.1.1";
        node.setIp(ip);
        String password = "r@34";
        node.setPassword(password);
        String type = "Pass-Through";
        node.setType(type);
        int xPosition = 100;
        node.setXPosition(xPosition);
        int yPosition = 100;
        node.setYPosition(yPosition);

        when(nodeRepository.findById(id)).thenReturn(Optional.of(node));

        Node newNode = nodeService.getNodeById(id);

        assertThat(newNode.getIp()).isEqualTo(ip);
        assertThat(newNode.getName()).isEqualTo(name);
        assertThat(newNode.getPassword()).isEqualTo(password);
        assertThat(newNode.getType()).isEqualTo(type);
        assertThat(newNode.getXPosition()).isEqualTo(xPosition);
        assertThat(newNode.getYPosition()).isEqualTo(yPosition);

        when(nodeRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NodeException.class,()->nodeService.getNodeById(id));


    }
    @Test
    void getNodeByName(){

        Node node = new Node();
        int id = 16;
        node.setNode_id(id);
        String name = "A";
        node.setName(name);
        String ip = "192.168.1.1";
        node.setIp(ip);
        String password = "r@34";
        node.setPassword(password);
        String type = "Pass-Through";
        node.setType(type);
        int xPosition = 100;
        node.setXPosition(xPosition);
        int yPosition = 100;
        node.setYPosition(yPosition);

        when(nodeRepository.findByNameIgnoreCase(name)).thenReturn(Optional.of(node));

        Node newNode = nodeService.getNodeByName(name);

        assertThat(newNode.getIp()).isEqualTo(ip);
        assertThat(newNode.getName()).isEqualTo(name);
        assertThat(newNode.getPassword()).isEqualTo(password);
        assertThat(newNode.getType()).isEqualTo(type);
        assertThat(newNode.getXPosition()).isEqualTo(xPosition);
        assertThat(newNode.getYPosition()).isEqualTo(yPosition);

        when(nodeRepository.findByNameIgnoreCase(name)).thenReturn(Optional.empty());
        assertThrows(NodeException.class,()->nodeService.getNodeByName(name));


    }

    @Test
    void updateNode(){

        Node node = new Node();
        int id = 16;
        node.setNode_id(id);
        String ip = "192.168.1.1";
        node.setIp(ip);
        String password = "r@34";
        node.setPassword(password);
        String type = "Pass-Through";
        node.setType(type);
        int xPosition = 100;
        node.setXPosition(xPosition);
        int yPosition = 100;
        node.setYPosition(yPosition);

        when(nodeRepository.findById(id)).thenReturn(Optional.of(node));

        Node newNode = nodeService.updateNode(id,node);

        assertThat(newNode.getIp()).isEqualTo(ip);
        assertThat(newNode.getPassword()).isEqualTo(password);
        assertThat(newNode.getType()).isEqualTo(type);
        assertThat(newNode.getXPosition()).isEqualTo(xPosition);
        assertThat(newNode.getYPosition()).isEqualTo(yPosition);

        when(nodeRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NodeException.class,()->nodeService.updateNode(id, node));
    }

    @Test
    void getNodeIdWithException(){
        when(nodeRepository.findById(1)).thenThrow(NodeException.class);
        assertThrows(NodeException.class,()->nodeService.getNodeById(1));
    }

    @Test
    void getNodeNameWithException(){
        when(nodeRepository.findByNameIgnoreCase("A")).thenThrow(NodeException.class);
        assertThrows(NodeException.class,()->nodeService.getNodeByName("A"));
    }

}
