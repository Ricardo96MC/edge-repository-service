package com.ricardo.edgeping.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.Collate;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

/**
 * Thhis Edge entity will represent a connection between two systems or services.
 * Each record corresponds to one row in the 'edge' table
 */
@Entity
@Table(name = "edge")
public class Edge {

    //Primary key, a UUID auto generated.
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(columnDefinition = "uuid")
    private UUID id;

    //Where our connection is coming from/originates
    @Column(nullable = false)
    @JsonProperty("source")
    private String source;

    @Column(nullable = false)
    @JsonProperty("target")
    private String target;

    @Column(nullable = false)
    @JsonProperty("url")
    private String url;

    //when the edge was created
    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    public Edge() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getTarget() { return target; }
    public void setTarget(String target) { this.target = target; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

}
