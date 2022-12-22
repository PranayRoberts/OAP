package com.capgemini.oap.controller;

import com.capgemini.oap.model.Circuit;
import com.capgemini.oap.service.CircuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controller")
public class CircuitController {

    private CircuitService circuitService;

    @Autowired
    public CircuitController(CircuitService circuitService){
        this.circuitService = circuitService;
    }

    @GetMapping("/get-circuit")
    public ResponseEntity<List<Circuit>> getAllCircuits(){
        return ResponseEntity.ok().body(circuitService.getAllCircuits());
    }

    @GetMapping("/get-circuit-by-id/{id}")
    public ResponseEntity<Circuit> getCircuitById(@PathVariable Integer id){
        return ResponseEntity.ok().body(circuitService.getCircuitById(id));
    }

    @GetMapping("/get-circuit-by-source/{src}")
    public ResponseEntity<Circuit> getCircuitBySource(@PathVariable String src){
        return ResponseEntity.ok().body(circuitService.getCircuitBySource(src));
    }

    @GetMapping("/get-circuit-by-destination/{dest}")
    public ResponseEntity<Circuit> getCircuitByDestination(@PathVariable String dest) {
            return ResponseEntity.ok().body(circuitService.getCircuitByDestination(dest));
    }

    @PostMapping("/add-circuit")
    public ResponseEntity<Circuit> addCircuit(@RequestBody Circuit circuit){
        return ResponseEntity.ok().body(circuitService.addCircuit(circuit));
    }

    @PutMapping("/update-circuit")
    public ResponseEntity<Circuit> updateCircuit(@RequestParam Integer id, @RequestBody Circuit circuit){
        return ResponseEntity.ok().body(circuitService.updateCircuit(id,circuit));

    }

    @DeleteMapping("/delete-circuit")
    public void deleteCircuit(@RequestParam Integer id){
        circuitService.deleteCircuit(id);
    }

}

