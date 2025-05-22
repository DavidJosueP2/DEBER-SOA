package com.repaso_prueba.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import com.repaso_prueba.entities.Course;
import java.net.URI;

public class CourseService {

    private static final String BASE_URL = "http://127.0.0.1:8000/api/courses";
    private final HttpClient client;
    private final Gson gson;

    public CourseService() {
        this.client = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    public List<Course> getAll() throws Exception {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .build();

        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(
                resp.body(),
                new TypeToken<List<Course>>() {
                }.getType());
    }

    public Course getById(Long id) throws Exception {

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .GET()
                .build();

        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(resp.body(), Course.class);
    }

    public Course create(Course s) throws Exception {
        String json = gson.toJson(s);

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(resp.body(), Course.class);
    }

    public Course update(Long id, Course s) throws Exception {

        String json = gson.toJson(s);

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(resp.body(), Course.class);
    }

    public boolean delete(Long id) throws Exception {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .DELETE()
                .build();

        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());

        return resp.statusCode() == 200;
    }

    public Course withEnrollments(Long id) throws Exception {

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/withEnrollments/" + id))
                .GET()
                .build();

        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(res.body(), Course.class);
    }
}
