package com.capgemini.oap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Edge {
    @Id
    @SequenceGenerator(name = "edge_sequence",sequenceName = "edge_sequence")
    @GeneratedValue(strategy = SEQUENCE,generator = "edge_sequence")
    private Integer edge_id;
    private Character name;
    private Boolean isAvailable;
//
//    @OneToOne(mappedBy = "edge",cascade = CascadeType.ALL)
//    private Card booster;
//
//    @OneToOne( mappedBy = "edge", cascade = CascadeType.ALL)
//    private Card pre;

    @OneToMany(mappedBy = "edge" ,cascade = CascadeType.ALL)
    private List<Card> card;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "node_id")
    private Node node;


    public Edge(int edge_id, char name, boolean isAvailable) {
        this.edge_id=edge_id;
        this.name=name;
        this.isAvailable=isAvailable;
    }
}
