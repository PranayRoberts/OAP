package com.capgemini.oap.service;

import com.capgemini.oap.exception.EdgeException;
import com.capgemini.oap.model.Edge;
import com.capgemini.oap.repository.EdgeRepository;
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
public class EdgeServiceTest {

    @InjectMocks
    private EdgeService edgeService = new EdgeServiceImpl();

    @Mock
    private EdgeRepository edgeRepository;

    private final Edge e1 = new Edge(1, 'A', true);
    private final Edge e2 = new Edge(2, 'B', false);

    private final List<Edge> list = new ArrayList<>();
    {
        e1.setEdge_id(1);
        e2.setEdge_id(2);
        list.addAll(List.of(e1,e2));
    }

    @Test
    void getEdgesTest(){
        when(edgeRepository.findAll()).thenReturn(list);
        List<Edge> edgeList = edgeService.getAllEdges();
        assertThat(edgeList.size()).isEqualTo(2);
    }

    @Test
    void getEdgeByIdTest(){
        int id=1;
        when(edgeRepository.findById(id)).thenReturn(Optional.of(e1));
        Edge edge = edgeService.getEdgeById(id);
        assertThat(edge.getEdge_id().equals(id));
    }

    @Test
    void getEdgeByIdWithExceptionTest(){
        EdgeException edgeException = assertThrows(EdgeException.class,
            ()-> edgeService.getEdgeById(1));
        assertEquals("Invalid Edge ID", edgeException.getMessage());
    }

    @Test
    void getEdgeByNameTest(){
        Character ename= 'A';
        when(edgeRepository.findByName(ename)).thenReturn(Optional.of(e1));
        Edge edge = edgeService.getEdgeByName(ename);
        assertThat(edge.getName()).isEqualTo(ename);
    }

    @Test
    void getEdgeByNameWithExceptionTest(){
        EdgeException edgeException = assertThrows(EdgeException.class,
                ()-> edgeService.getEdgeByName('A'));
        assertEquals("Invalid Edge Name", edgeException.getMessage());
    }

    @Test
    void addEdgeTest(){
        when(edgeRepository.save(e1)).thenReturn(e1);

        Edge edge =edgeService.addEdge(e1);

        assertThat(edge.getName()).isEqualTo(e1.getName());
    }

    @Test
    void deleteEdgeTest(){
        Edge edge = new Edge();
        edge.setEdge_id(3);
        edge.setName('A');
        edge.setIsAvailable(true);

        Optional<Edge> optionalEdge = Optional.of(edge);

        when(edgeRepository.findById(3)).thenReturn(optionalEdge);
        edgeService.getEdgeById(3);
        edgeService.deleteEdge(3);
        verify(edgeRepository, times(1)).deleteById(3);
    }

    @Test
    void deleteEdgeWithException(){
        EdgeException edgeException = assertThrows(EdgeException.class,
                ()->edgeService.deleteEdge(1));
        assertEquals("Invalid Edge ID", edgeException.getMessage());
    }

    @Test
    void updateEdgeTest(){
        int id =2;
        when(edgeRepository.findById(id)).thenReturn(Optional.of(e2));

        Edge temp = e2;
        Character ename= 'A';
        temp.setName(ename);

        Edge edge = edgeService.updateEdge(id,e2);

        assertThat(edge.getName()).isEqualTo(ename);
    }

    @Test
    void updateEdgeWithException(){
        EdgeException edgeException = assertThrows(EdgeException.class,
                ()-> edgeService.updateEdge(1,e1));
        assertEquals("Invalid Edge ID", edgeException.getMessage());
    }

    @Test
    void exceptionTest(){
        int id =150;
        when(edgeRepository.findById(id)).thenThrow(EdgeException.class);
        assertThrows(EdgeException.class, ()-> edgeService.getEdgeById(id));
    }
    }

