package excel.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 学生信息，登陆/注册时用
 *
 * @author 云奎
 *
 */
@Entity
public class Student {

    @Id
    // 学号，年级从学号判断，不然每过一年都要改一次
    private String sid;
    private String name;
    private String email;
    // 14级，15级...
    private String grade;
    private String password;

    private String code;
    private String state;

    public Student(String sid, String name, String email, String grade, String password){
        this.sid = sid;
        this.name = name;
        this.email = email;
        this.grade = grade;
        this.password = password;
    }

    public Student(){

    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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
