package com.example.deber_cuatro.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Productos {
    private String id;
    private String title;
    private String thumbnail;
    private String description;
    private List<String> images;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Productos(JSONObject a) throws JSONException {
        id = a.getString("id").toString();
        title = a.getString("title").toString();
        thumbnail = a.getString("thumbnail").toString();
        description = a.getString("description").toString();

        JSONArray jsonImages = a.getJSONArray("images");
        images = new ArrayList<>();
        for (int i = 0; i < jsonImages.length(); i++) {
            images.add(jsonImages.getString(i));
        }
    }

    public static ArrayList<Productos> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Productos> productos = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            productos.add(new Productos(datos.getJSONObject(i)));
        }
        return productos;
    }
}

