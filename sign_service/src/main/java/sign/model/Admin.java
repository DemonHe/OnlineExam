package sign.model;

import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class Admin {

    @Id
    private String email;

    private String name;

    private String password;

    private String code;
    private String state;

    public Admin(String email,String password,String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public Admin(){

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
