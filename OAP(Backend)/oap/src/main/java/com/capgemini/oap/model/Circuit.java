package com.capgemini.oap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Circuit {

    @Id
    @SequenceGenerator(name = "circuit_sequence",sequenceName ="circuit_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "circuit_sequence")
    private Integer circuit_id;

    private String source;
    private String destination;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="")
    private List<Link> forwardPath =new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="")
    private List<Link> reversePath =new ArrayList<>();

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "network_circuit_id")
    private Network network;

}
