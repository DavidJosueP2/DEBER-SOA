package com.repaso_prueba.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import com.repaso_prueba.entities.Student;

public class StudentService {

    private static final String BASE_URL = "http://127.0.0.1:8000/api/students";
    private final HttpClient client;
    private final Gson gson;
    
    public StudentService() {
        this.client = HttpClient.newHttpClient();
        this.gson = new Gson();
    }
    
    public List<Student> getAll() throws Exception {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .build();
        
        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        
        return gson.fromJson(
                resp.body(),
                new TypeToken<List<Student>>(){}.getType());
    }
    
    public Student getById(Long id) throws Exception {
        
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .GET()
                .build();
        
        
        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        
        return gson.fromJson(resp.body(), Student.class);
    }
    
    public Student create(Student s) throws Exception {
        String json = gson.toJson(s);
        
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        
        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
     
        return gson.fromJson(resp.body(), Student.class);
    }
    
    public Student update(Long id, Student s) throws Exception {
        
        String json = gson.toJson(s);
        
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/"  + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();
        
        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
     
        return gson.fromJson(resp.body(), Student.class);
    }
    
    public boolean delete(Long id) throws Exception {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .DELETE()
                .build();
               
        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        
        return resp.statusCode() == 200;
    }    
    
    public Student withEnrollments(Long id) throws Exception {
        
        HttpRequest req =  HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/withEnrollments/" + id))
                .GET()
                .build();
        
        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
        
        return gson.fromJson(res.body(), Student.class);
    }
}
