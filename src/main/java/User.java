import java.sql.Timestamp;

public class User {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Timestamp created_at;
    private boolean active;
    private Integer birthYear;
    private Integer birthMonth;
    private Integer birthDay;
    private Integer age = 0;
    private String gender;

    public User (String firstName, String lastName, String email, String password,
                 Integer birthYear, Integer birthMonth, Integer birthDay, String gender){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.gender = gender;
    }

    public User(Integer id, String firstName, String lastName, String email, String password,
                Timestamp created_at, boolean active, Integer birthYear, Integer birthMonth, Integer birthDay, Integer age, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
        this.active = active;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(Integer birthMonth) {
        this.birthMonth = birthMonth;
    }

    public Integer getBirthDay(){
        return birthDay;
    }

    public void setBirthDay(Integer birthDay) {
        this.birthDay = birthDay;
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

    //todo format the birthday correctly

    //todo calculate age

    //todo age-up on every birthday

    @Override
    public String toString(){
        return "ID: " + id + " | name: " + firstName + " " + lastName + " | e-mail: " + email
                 + "| member since: " + created_at + " | \nage: " + age + " | birthday: " + birthDay
                + "." + birthMonth + "." + birthYear + " | gender: " + gender;
    }
}
