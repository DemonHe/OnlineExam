package presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@RequestMapping("/admin")
@Controller
public class QuestionsController {

    @RequestMapping(value = "/loadQuestions",method = RequestMethod.GET)
    public String loadQuestions() {
        return "admin/questions_load";
    }
}
