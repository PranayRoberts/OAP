package com.capgemini.oap.controller;

import com.capgemini.oap.model.Edge;
import com.capgemini.oap.service.EdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/edge")
public class EdgeController {

    private EdgeService edgeService;

    @Autowired
    public EdgeController(EdgeService edgeService) {
        this.edgeService = edgeService;
    }

    @GetMapping("/get-edge")
    public ResponseEntity<List<Edge>> getAllEdges(){
        return ResponseEntity.ok().body(edgeService.getAllEdges());
    }

    @GetMapping("/get-edge-by-id/{id}")
    public ResponseEntity<Edge> getEdgeById(@PathVariable Integer id){
        return ResponseEntity.ok().body(edgeService.getEdgeById(id));
    }

    @GetMapping("/get-edge-by-name/{name}")
    public ResponseEntity<Edge> getEdgeByName(@PathVariable Character name){
        return ResponseEntity.ok().body(edgeService.getEdgeByName(name));
    }

    @PostMapping("/add-edge")
    public ResponseEntity<Edge> addEdge(@RequestBody Edge edge){
        return ResponseEntity.ok().body(edgeService.addEdge(edge));
    }

    @PutMapping("/update-edge")
    public ResponseEntity<Edge> updateEdge(@RequestParam Integer id, @RequestBody Edge edge){
        return ResponseEntity.ok().body(edgeService.updateEdge(id,edge));
    }

    @DeleteMapping("/delete-edge")
    public void deleteEdge(@RequestParam Integer id){
        edgeService.deleteEdge(id);
    }

}
