package com.ricardo.edgeping.service;

import com.ricardo.edgeping.model.Edge;
import com.ricardo.edgeping.repository.EdgeRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.util.List;

@Service
public class EdgeStatusService {

    private final EdgeRepository edgeRepository;

    public EdgeStatusService(EdgeRepository edgeRepository) {
        this.edgeRepository = edgeRepository;
    }

    // Lets set to check it every 5 minutes
    @Scheduled(fixedRate = 300000)
    public void checkedEdgeStatuses() {
        List<Edge> edges = edgeRepository.findAll();

        for(Edge e: edges) {
            String status = pingUrl(e.getUrl());
            e.setStatus(status);
            e.setLastCheckedAt(Instant.now());
            edgeRepository.save(e);
        }
    }

    // Lets ping our URL
    private String pingUrl(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000); // 5 seconds
            connection.connect();

            int code = connection.getResponseCode();
            return (code >= 200 && code < 300) ? "UP": "DOWN";
        } catch (Exception e){
            return "DOWN";
        }
    }
}
