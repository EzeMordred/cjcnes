package com.uta.appeventosuta.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Webinar {

    private String id;
    private String title;
    private String initDate;
    private String initTime;
    private String link;
    private String speakerPrefix;
    private String speakerName;
    private String speakerLastName;
    private String speakerImage;
    private String speakerJob;

    public Webinar() {
    }

    public Webinar(String id, String title, String initDate, String initTime, String link, String speakerPrefix,
                   String speakerName, String speakerLastName, String speakerImage, String speakerJob) {
        this.id = id;
        this.title = title;
        this.initDate = initDate;
        this.initTime = initTime;
        this.link = link;
        this.speakerPrefix = speakerPrefix;
        this.speakerName = speakerName;
        this.speakerLastName = speakerLastName;
        this.speakerImage = speakerImage;
        this.speakerJob = speakerJob;
    }

    public static Webinar jsonToWebinar(JSONObject jsonEvent) {
        Webinar event = new Webinar();
        try {
            event.setId(jsonEvent.getString("id_evento"));
            event.setTitle(jsonEvent.getString("titulo"));
            event.setInitDate(jsonEvent.getString("fecha_inicio"));
            event.setInitTime(jsonEvent.getString("hora_inicio"));
            event.setLink(jsonEvent.getString("enlace"));
            event.setSpeakerPrefix(jsonEvent.getString("prefijo_titulo"));
            event.setSpeakerName(jsonEvent.getString("primer_nombre"));
            event.setSpeakerLastName(jsonEvent.getString("primer_apellido"));
            event.setSpeakerImage(jsonEvent.getString("url_imagen"));
            event.setSpeakerJob(jsonEvent.getString("detalle_profesion"));
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

    public String getInitDate() {
        return initDate;
    }

    public void setInitDate(String initDate) {
        this.initDate = initDate;
    }

    public String getInitTime() {
        return initTime;
    }

    public void setInitTime(String initTime) {
        this.initTime = initTime;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSpeakerPrefix() {
        return speakerPrefix;
    }

    public void setSpeakerPrefix(String speakerPrefix) {
        this.speakerPrefix = speakerPrefix;
    }

    public String getSpeakerName() {
        return speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }

    public String getSpeakerLastName() {
        return speakerLastName;
    }

    public void setSpeakerLastName(String speakerLastName) {
        this.speakerLastName = speakerLastName;
    }

    public String getSpeakerImage() {
        return speakerImage;
    }

    public void setSpeakerImage(String speakerImage) {
        this.speakerImage = speakerImage;
    }

    public String getSpeakerJob() {
        return speakerJob;
    }

    public void setSpeakerJob(String speakerJob) {
        this.speakerJob = speakerJob;
    }
}
