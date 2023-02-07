package com.uta.appeventosuta.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Organizer {

    String id;
    String name;
    String email;
    String phone;
    String address;
    String image;

    public Organizer() {
    }

    public Organizer(String id, String name, String email, String phone, String address, String image) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.image = image;
    }

    public static ArrayList<Organizer> getOrganizersFromJson(JSONArray jsonArrayOrganizers) {
        ArrayList<Organizer> organizers = new ArrayList<>(jsonArrayOrganizers.length());
        for (int i = 0; i < jsonArrayOrganizers.length(); i++) {
            try {
                organizers.add(jsonToOrganizer(jsonArrayOrganizers.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return organizers;
    }

    public static Organizer jsonToOrganizer(JSONObject jsonOrganizer) {
        Organizer organizer = new Organizer();
        try {
            organizer.setId(jsonOrganizer.getString("id_organizador"));
            organizer.setName(jsonOrganizer.getString("nombre"));
            organizer.setEmail(jsonOrganizer.getString("email"));
            organizer.setPhone(jsonOrganizer.getString("telefono"));
            organizer.setAddress(jsonOrganizer.getString("direccion"));
            organizer.setImage(jsonOrganizer.getString("url_imagen"));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return organizer;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
