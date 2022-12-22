package com.capgemini.oap.repository;

import com.capgemini.oap.model.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NodeRepository extends JpaRepository<Node,Integer> {
    Optional<Node> findByNameIgnoreCase(String name);
}
