package presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentMenuController {

	@RequestMapping(value = "/welcome")
    public String getTestInfo() {
        return "student/menu";
    }

    @RequestMapping(value = "/waiting")
    public String getMarkList() {
        return "student/waiting";
    }
    
    @RequestMapping(value = "/logout")
    public String logout() {
        return "admin/signIn_student";
    }
}
