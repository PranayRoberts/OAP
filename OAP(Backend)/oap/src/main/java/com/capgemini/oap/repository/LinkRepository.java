package com.capgemini.oap.repository;

import com.capgemini.oap.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Integer> {
    Optional<Link> findByNameIgnoreCase(String name);
}

