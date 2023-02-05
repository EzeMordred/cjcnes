package com.uta.appeventosuta.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable {

    private String name, lastName, birthDate, email, phone, role;

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
            person.setName(jsonPerson.getString("primer_nombre"));
            person.setLastName(jsonPerson.getString("primer_apellido"));
            person.setBirthDate(jsonPerson.getString("fecha_nacimiento"));
            person.setEmail(jsonPerson.getString("email"));
            person.setPhone(jsonPerson.getString("telefono"));
            getUserRole(person, jsonPerson);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return person;
    }

    private final static String[] roles = {"administrador", "estudiante", "egresado",
                                    "docente", "administrativo", "particular"};

    private static void getUserRole(Person person, JSONObject jsonPerson) throws JSONException {
        StringBuilder userRoles = new StringBuilder();
        for (String rol : roles) {
            if (jsonPerson.getString(rol).equals("1")) {
                int size = rol.length();
                userRoles.append(String.format("%s%s\n", rol.substring(0, 1).toUpperCase(), rol.substring(1)));
            }
        }
        person.setRole(userRoles.toString());
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}