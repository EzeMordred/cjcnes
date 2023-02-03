package com.uta.appeventosuta.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Event {

    String id;
    String title;
    String subtittle;
    String modality;
    String initDate;
    String finalDate;

    public Event() {
    }

    public Event(String id, String title, String subtittle, String modality, String initDate, String finalDate) {
        this.id = id;
        this.title = title;
        this.subtittle = subtittle;
        this.modality = modality;
        this.initDate = initDate;
        this.finalDate = finalDate;
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
            event.setId(jsonEvent.getString("id_curso"));
            event.setTitle(jsonEvent.getString("titulo"));
            event.setSubtittle(jsonEvent.getString("subtitulo"));
            event.setModality(jsonEvent.getString("modalidad"));
            event.setInitDate(jsonEvent.getString("fecha_inicio_inscripcion"));
            event.setFinalDate(jsonEvent.getString("fecha_fin_inscripcion"));
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

    public String getSubtittle() {
        return subtittle;
    }

    public void setSubtittle(String subtittle) {
        this.subtittle = subtittle;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
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

}
