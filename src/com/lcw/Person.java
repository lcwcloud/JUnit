package com.lcw;

public class Person {
    private String firstName;
    private String lastName;
    public Person(String name, String name2) {
        this.firstName = name;
        this.lastName = name2;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }
}
