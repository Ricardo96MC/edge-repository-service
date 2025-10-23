package com.ricardo.edgeping.repository;

import com.ricardo.edgeping.model.Edge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * EdgeRepository is responsible for all database operations
 * related to the Edge entity.
 * JpaRepository automatically provides methods like:
 * - save(Edge edge)
 * - findAll()
 * - findById(UUID id)
 * - deleteById(UUID id)
 */
public interface EdgeRepository extends JpaRepository<Edge, UUID> {
    /**
     *   This is our basic library or CRUDn via JPARepo
     *   Now lets add our own custom request to find by status
     * Spring Data JPA has its set naming convention system that automatically generates queries
     * from our repos names
     * findBy -> is a reserved keyword, which translates to "query for entries by fields"
     * status -> is the said fiel we are looking for
     * ultimately builds a query like:
     * SELECT * FROM edge WHERE status = ?(our whatever we are looking for)
     */
    List<Edge> findByStatus(String status);
}
