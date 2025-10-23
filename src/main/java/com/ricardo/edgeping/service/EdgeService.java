package com.ricardo.edgeping.service;

import com.ricardo.edgeping.model.Edge;
import com.ricardo.edgeping.repository.EdgeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * This will be our 'business logic' layer between controllers and repositories.
 * It will define how we create an edge, retrieved and deleted.
 */
@Service
public class EdgeService {

    private final EdgeRepository edgeRepository;

    // Spring lets us auto inject our EdgeRepo via the constructor
    public EdgeService(EdgeRepository edgeRepository) {
        this.edgeRepository = edgeRepository;
    }

    // To Save an edge into our DB
    public Edge create(Edge edge) {
        return edgeRepository.save(edge);
    }

    // Simple test for use, fetch all edges
    public List<Edge> findAllEdges() {
        return edgeRepository.findAll();
    }

    // Fetch by UUID, we are Using the Optional class to handle possible not found ids cleanly
    public Optional<Edge> findById(UUID id) {
        return edgeRepository.findById(id);
    }

    // Delete by UUID (return boolean for success/failure)
    public boolean deleteEdgeById(UUID id) {
        if(edgeRepository.existsById(id)) {
            edgeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Edge> updateById(UUID id,Edge edge) {
      return edgeRepository.findById(id)
              .map(existingEdge -> {
                  existingEdge.setSource(edge.getSource());
                  existingEdge.setTarget(edge.getTarget());
                  existingEdge.setUrl(edge.getUrl());
                  existingEdge.setCreatedAt(java.time.Instant.now());
                  return edgeRepository.save(existingEdge);
              });
    }

    public List<Edge> getEdgeByStatus(String status) {
        return edgeRepository.findByStatus(status.toUpperCase());
    }
}
