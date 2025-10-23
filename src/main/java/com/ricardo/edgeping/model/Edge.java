package com.ricardo.edgeping.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.UUID;

/**
 * This Edge entity will represent a connection between two systems or services.
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
    @NotBlank(message = "Source must not be blank.")
    @Size(max = 100, message = "Source must be 100 characters or less.")
    @Column(nullable = false)
    @JsonProperty("source")
    private String source;

    @NotBlank(message = "Target must not be blank.")
    @Size(max = 100, message = "Target must be 100 characters or less.")
    @Column(nullable = false)
    @JsonProperty("target")
    private String target;

    @NotBlank(message = "URL must not be blank.")
    @Size(message = "URL must be 100 characters or blank.")
    @Column(nullable = false)
    @JsonProperty("url")
    private String url;

    //when the edge was created
    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    @Column(nullable = false)
    private String status = "UNKNOWN";

    private Instant lastCheckedAt;

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

    public String getStatus() {return status; }
    public void setStatus(String status) { this.status = status; }

    public Instant getLastCheckedAt() { return lastCheckedAt; }
    public void setLastCheckedAt(Instant lastCheckedAt) { this.lastCheckedAt = lastCheckedAt; }
}
