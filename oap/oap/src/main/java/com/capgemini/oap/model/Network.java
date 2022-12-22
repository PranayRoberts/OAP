package com.capgemini.oap.model;

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
public class Network {
    @Id
    @SequenceGenerator(name = "network_sequence",sequenceName = "network_sequence")
    @GeneratedValue(strategy = SEQUENCE,generator = "network_sequence")
    private Integer network_id;
    private String network_name;
    private String username;

    @OneToMany(mappedBy = "network",cascade = CascadeType.ALL)
    private List<Node> nodes;

    @OneToMany(mappedBy = "network",cascade = CascadeType.ALL)
    private List<Link> links;

    @OneToOne(mappedBy = "network",cascade = CascadeType.ALL)
    private Circuit circuit;

}
