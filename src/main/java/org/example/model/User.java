package org.example.model;

public class User {
    private long id;
    private String name;
    private String email;
    private String gender;
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User: ").append(name)
                .append("\nID: ").append(id)
                .append("\nEmail: ").append(email)
                .append("\nGender: ").append(gender)
                .append("\nStatus: ").append(status);
        return sb.toString();
    }
}
