package edu.bsuir.pojo;

public class User {

    private long id;
    private String name;
    private int age;
    private double salary;

    public long getId() {
        return id;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public User setAge(int age) {
        this.age = age;
        return this;
    }

    public double getSalary() {
        return salary;
    }

    public User setSalary(double salary) {
        this.salary = salary;
        return this;
    }
}
