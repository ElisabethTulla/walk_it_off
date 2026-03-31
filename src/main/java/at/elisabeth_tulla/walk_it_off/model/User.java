package at.elisabeth_tulla.walk_it_off.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;

public class User {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Timestamp created_at;
    private boolean active;
    private LocalDate birthdayDate;
    private Integer age;
    private String gender;

    public User (String firstName, String lastName, String email, String password,
                 LocalDate birthdayDate, String gender){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthdayDate = birthdayDate;
        this.gender = gender;
    }

    public User(Integer id, String firstName, String lastName, String email, String password,
                Timestamp created_at, boolean active, LocalDate birthdayDate, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
        this.active = active;
        this.birthdayDate = birthdayDate;
        this.gender = gender;}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(LocalDate birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public Integer getAge() {
           return age = Period.between(birthdayDate, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString(){
        return "ID: " + id + " | name: " + firstName + " " + lastName + " | e-mail: " + email
                 + "| member since: " + created_at + " | \nage: " + getAge() +
                " | birthday: " + birthdayDate + " | gender: " + gender;
    }
}
