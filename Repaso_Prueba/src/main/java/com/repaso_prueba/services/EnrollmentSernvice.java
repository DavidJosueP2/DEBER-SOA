package com.repaso_prueba.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.net.http.*;
import java.util.List;
import com.repaso_prueba.entities.Enrollment;
import java.net.URI;
import java.util.ArrayList;

public class EnrollmentSernvice {

    private final String BASE_URL = "http://127.0.0.1:8000/api/enrollments";
    private HttpClient client;
    private Gson gson;

    public EnrollmentSernvice() {
        this.client = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    public List<Enrollment> getAll(Integer studentId, Integer courseId) throws Exception {
        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        List<String> params = new ArrayList<>();
        
        if (studentId != null) params.add("student_id=" + studentId);
        if (courseId != null) params.add("course_id=" + courseId);
        if (!params.isEmpty()) urlBuilder.append("?").append(String.join("&", params));
        
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(urlBuilder.toString()))
                .GET()
                .build();
                
        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(resp.body(), new TypeToken<List<Enrollment>>(){}.getType());
    }

    public Enrollment getById(Long id) throws Exception {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .GET()
                .build();

        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(resp.body(), Enrollment.class);
    }

    public boolean create(Enrollment enrollment) throws Exception {

        String json = gson.toJson(enrollment);

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        

        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println("post: " + resp.body());

        return resp.statusCode() == 200;
    }

    public Enrollment update(Long id, Enrollment enrollment) throws Exception {

        String json = gson.toJson(enrollment);

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());

        System.out.println("respuesta: " + resp.body());

        return gson.fromJson(resp.body(), Enrollment.class);
    }
    
    public boolean delete(Long id) throws Exception {
        
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .DELETE()
                .build();

        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());

        return resp.statusCode() == 200;
    }

}
