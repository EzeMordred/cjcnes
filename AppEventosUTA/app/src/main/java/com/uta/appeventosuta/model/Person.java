package com.uta.appeventosuta.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Person {

    private String id, name, lastName, birthDate, email, user;

    public Person() {
    }

    public static ArrayList<Person> getPeopleFromJson(JSONArray jsonArrayPeople) {
        ArrayList<Person> people = new ArrayList<>(jsonArrayPeople.length());
        for (int i = 0; i < jsonArrayPeople.length(); i++) {
            try {
                people.add(jsonToPerson(jsonArrayPeople.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return people;
    }

    public static Person jsonToPerson(JSONObject jsonPerson) {
        Person person = new Person();
        try {
            person.setId(jsonPerson.getString("id_persona"));
            person.setName(jsonPerson.getString("primer_nombre"));
            person.setLastName(jsonPerson.getString("primer_apellido"));
            person.setBirthDate(jsonPerson.getString("fecha_nacimiento"));
            person.setEmail(jsonPerson.getString("email"));
            //person.setUser(jsonPerson.getString(""));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return person;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}