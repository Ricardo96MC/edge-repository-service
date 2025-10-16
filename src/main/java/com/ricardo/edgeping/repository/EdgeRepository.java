package com.ricardo.edgeping.repository;

import com.ricardo.edgeping.model.Edge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * EdgeRepository is responsible for all database operations
 * related to the Edge entity.
 *
 * JpaRepository automatically provides methods like:
 * - save(Edge edge)
 * - findAll()
 * - findById(UUID id)
 * - deleteById(UUID id)
 */
public interface EdgeRepository extends JpaRepository<Edge, UUID> {
}
