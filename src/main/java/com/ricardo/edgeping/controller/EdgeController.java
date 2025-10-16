package com.ricardo.edgeping.controller;

import com.ricardo.edgeping.model.Edge;
import com.ricardo.edgeping.service.EdgeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * This controller will define REST endpoints for managing Edge records
 *
 * Routes:
 * - POST   /api/v1/edges   -> CREATE a new edge
 * - GET    /api/v1/edges   -> GET all edges
 * - GET    /api/v1/edges{id}   -> GET a specific edge via ID
 * - DELETE    /api/v1/edges{id}   -> DELETE a specific edge via ID
 */
@RestController
@RequestMapping("api/v1/edges")
public class EdgeController {

    private final EdgeService edgeService;

    public EdgeController(EdgeService service) {
        this.edgeService = service;
    }

    // Create a new edge POST
    @PostMapping
    public Edge create(@RequestBody Edge edge) {
        return edgeService.create(edge);
    }

    // List all edges
    @GetMapping
    public List<Edge> findAll() {
        return edgeService.findAllEdges();
    }

    // Get a specific edge by id
    @GetMapping("/{id}")
    public ResponseEntity<Edge> findById(@PathVariable UUID id) {
        return edgeService.findById(id)
                .map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        // noContent is used for SUCCESFUL events, but no body is returned: 204 No Content
        // vs notFound is a FAILURE event, resource does not exist: 404 Not Found
       return  edgeService.deleteEdgeById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Edge> update(@PathVariable UUID id, @RequestBody Edge edge) {
        return edgeService.updateById(id, edge)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

