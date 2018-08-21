package presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ExamController {

    @RequestMapping(value = "/exam",method = RequestMethod.GET)
    public String exam() {
        return "student/exam";
    }

    @RequestMapping(value = "/submit",method = RequestMethod.GET)
    public String submit() {
        return "student/submit_success";
    }
}
