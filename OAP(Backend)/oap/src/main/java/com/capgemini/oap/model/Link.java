package com.capgemini.oap.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Link {

    @Id
    @SequenceGenerator(name = "link_sequence", sequenceName = "link_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "link_sequence")
    private Integer link_id;
    private String name;
    private int length;
    private String from_node;
    private String to_node;

    private Character fromEdge;

    private Character toEdge;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "network_id")
    private Network network;




    public Link(String name, int length, String from_node, String to_node, Character fromEdge, Character toEdge) {
        this.name = name;
        this.length = length;
        this.from_node = from_node;
        this.to_node = to_node;
        this.fromEdge = fromEdge;
        this.toEdge = toEdge;
    }


}
