package com.capgemini.oap.controller;

import com.capgemini.oap.model.Link;
import com.capgemini.oap.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/link")
public class LinkController {

    private LinkService linkService;

    @Autowired
    public LinkController(LinkService linkService){
        this.linkService=linkService;
    }

    @GetMapping("/get-link")
    public ResponseEntity<List<Link>> getAllLinks() {
        return ResponseEntity.ok().body(linkService.getAllLinks());
    }

    @GetMapping("/get-link-by-id/{id}")
    public ResponseEntity<Link> getLinkById(@PathVariable Integer id){
        return ResponseEntity.ok().body(linkService.getLinkById(id));
    }

    @GetMapping("/get-link-by-name/{name}")
    public ResponseEntity<Link> getLinkByName(@PathVariable String name){
        return ResponseEntity.ok().body(linkService.getLinkByName(name));
    }

    @PostMapping("/add-link")
    public ResponseEntity<Link> addNode(@RequestBody Link link){
        return ResponseEntity.ok().body(linkService.addLink(link));
    }

    @PutMapping("/update-link")
    public ResponseEntity<Link> updateNode(@RequestParam Integer id,
                                           @RequestBody Link link){
        return ResponseEntity.ok().body(linkService.updateLink(id,link));
    }

    @DeleteMapping("/delete-link")
    public void deleteNode(@RequestParam(required = true) Integer id){
        linkService.deleteLink(id);
    }

}

