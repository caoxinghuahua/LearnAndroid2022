package com.example.kotlinleran.java;

public class PersonS {
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    private String name;
    private int age;

    public PersonS(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
    }


    public static class Builder {
        private String name;
        private int age;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder seAge(int age) {
            this.age = age;
            return this;
        }

        public PersonS Build() {
            PersonS person = new PersonS(this);
            return person;
        }
    }
}