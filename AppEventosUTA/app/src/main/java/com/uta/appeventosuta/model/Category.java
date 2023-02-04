package com.uta.appeventosuta.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Category {

    private String id;
    private String name;
    private String image;

    public Category() {}

    public Category(String id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public static ArrayList<Category> getCategoriesFromJson(JSONArray jsonArrayCategories) {
        ArrayList<Category> categories = new ArrayList<>(jsonArrayCategories.length());
        for (int i = 0; i < jsonArrayCategories.length(); i++) {
            try {
                categories.add(jsonToPerson(jsonArrayCategories.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return categories;
    }

    public static Category jsonToPerson(JSONObject jsonCategory) {
        Category category = new Category();
        try {
            category.setId(jsonCategory.getString("id_categoria"));
            category.setName(jsonCategory.getString("nombre"));
            category.setImage(jsonCategory.getString("url_imagen"));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
