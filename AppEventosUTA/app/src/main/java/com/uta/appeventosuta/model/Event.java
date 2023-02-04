package com.uta.appeventosuta.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Event {

    String id;
    String title;
    String subtitle;
    String location;
    String initDate;
    String finalDate;
    String type;
    String image;

    public Event() {
    }

    public Event(String id, String title, String subtitle, String location,
                 String initDate, String finalDate, String type, String image) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.location = location;
        this.initDate = initDate;
        this.finalDate = finalDate;
        this.type = type;
        this.image = image;
    }

    public static ArrayList<Event> getEventsFromJson(JSONArray jsonArrayEvents) {
        ArrayList<Event> events = new ArrayList<>(jsonArrayEvents.length());
        for (int i = 0; i < jsonArrayEvents.length(); i++) {
            try {
                events.add(jsonToEvent(jsonArrayEvents.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return events;
    }

    public static Event jsonToEvent(JSONObject jsonEvent) {
        Event event = new Event();
        try {
            event.setId(jsonEvent.getString("id_evento"));
            event.setTitle(jsonEvent.getString("titulo"));
            event.setSubtitle(jsonEvent.getString("subtitulo"));
            event.setLocation(jsonEvent.getString("ubicacion"));
            event.setInitDate(jsonEvent.getString("fecha_inicio"));
            event.setFinalDate(jsonEvent.getString("fecha_fin"));
            event.setType(jsonEvent.getString("tipo_evento"));
            event.setImage(jsonEvent.getString("url_imagen"));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return event;
    }

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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInitDate() {
        return initDate;
    }

    public void setInitDate(String initDate) {
        this.initDate = initDate;
    }

    public String getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(String finalDate) {
        this.finalDate = finalDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
