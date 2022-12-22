package com.capgemini.oap.service;

import com.capgemini.oap.exception.CircuitException;
import com.capgemini.oap.model.Circuit;
import com.capgemini.oap.repository.CircuitRepository;
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
public class CircuitServiceTest {

    @InjectMocks
    private CircuitService circuitService = new CircuitServiceImpl();

    @Mock
    private CircuitRepository circuitRepository;

    private Circuit c1 = new Circuit();
    private Circuit c2 = new Circuit();

    private final List<Circuit> list= new ArrayList<>();
    {
        c1.setCircuit_id(1);
        c1.setSource("A");
        c1.setDestination("B");
        c2.setCircuit_id(2);
        c2.setSource("B");
        c2.setDestination("C");
        list.addAll(List.of(c1, c2));
    }

    @Test
    void addCircuit(){
        when(circuitRepository.save(c1)).thenReturn(c1);
        Circuit circuit = circuitService.addCircuit(c1);
        assertThat(circuit.getSource()).isEqualTo(c1.getSource());
    }
    @Test
    void getAllCircuits(){
        when(circuitRepository.findAll()).thenReturn(list);
        List<Circuit> circuitList = circuitService.getAllCircuits();
        assertThat(circuitList.size()).isEqualTo(2);
    }
    @Test
    void getCircuitById(){
        int id = 1;
        when(circuitRepository.findById(id)).thenReturn(Optional.of(c1));
        Circuit circuit = circuitService.getCircuitById(id);
        assertThat(circuit.getCircuit_id().equals(id));
    }
    @Test
    void getCircuitByIdWithException(){
        CircuitException circuitException=assertThrows(CircuitException.class,()->circuitService.getCircuitById(1));
        assertEquals("Invalid Circuit ID",circuitException.getMessage());
    }
    @Test
    void getCircuitBySource(){
        String source="A";
        when(circuitRepository.findBySource(source)).thenReturn(Optional.of(c1));
        Circuit circuit = circuitService.getCircuitBySource(source);
        assertThat(circuit.getSource()).isEqualTo(source);
    }
    @Test
    void getCircuitBySourceWithException(){
        CircuitException circuitException=assertThrows(CircuitException.class,()->circuitService.getCircuitBySource("A"));
        assertEquals("Invalid Circuit Source",circuitException.getMessage());
    }
    @Test
    void getCircuitByDestination(){
        String destination="B";
        when(circuitRepository.findByDestination(destination)).thenReturn(Optional.of(c1));
        Circuit circuit = circuitService.getCircuitByDestination(destination);
        assertThat(circuit.getDestination()).isEqualTo(destination);
    }
    @Test
    void getCircuitByDestinationWithException(){
        CircuitException circuitException=assertThrows(CircuitException.class,()->circuitService.getCircuitByDestination("B"));
        assertEquals("Invalid Circuit Destination",circuitException.getMessage());
    }
    @Test
    void deleteCircuit(){
        Circuit circuit=new Circuit();
        circuit.setCircuit_id(3);
        circuit.setSource("C");
        circuit.setDestination("D");
        Optional<Circuit> optionalCircuit=Optional.of(circuit);
        when(circuitRepository.findById(3)).thenReturn(optionalCircuit);
        circuitService.getCircuitById(3);
        circuitService.deleteCircuit(3);
        verify(circuitRepository, times(1)).deleteById(3);
    }
    @Test
    void deleteCircuitWithException(){
        CircuitException circuitException=assertThrows(CircuitException.class,()->circuitService.deleteCircuit(1));
        assertEquals("Invalid Circuit ID",circuitException.getMessage());
    }
    @Test
    void updateCircuit(){
        int id = 2;
        when(circuitRepository.findById(id)).thenReturn(Optional.of(c2));
        Circuit temp=c2;
        String source = "B";
        temp.setSource(source);
        Circuit circuit = circuitService.updateCircuit(id,c2);
        assertThat(circuit.getSource()).isEqualTo(source);
    }
    @Test
    void updateCircuitWithException(){
        CircuitException circuitException=assertThrows(CircuitException.class,()->circuitService.updateCircuit(1,c1));
        assertEquals("Invalid Circuit ID",circuitException.getMessage());
    }
    @Test
    void exceptionTest(){
        int id=15;
        when(circuitRepository.findById(id)).thenThrow(CircuitException.class);
        assertThrows(CircuitException.class,()->circuitService.getCircuitById(id));
    }
}
