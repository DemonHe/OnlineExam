package presentation;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Kimone.
 */
@Controller
public class HelloController {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(Model model) {
//        model.addAttribute("name", "Dear");
        return "hello";
    }

    @RequestMapping(value = "/testSession",method = RequestMethod.GET)
    public String testSession() {

        return "test";
    }

    @RequestMapping(value = "/admintest",method = RequestMethod.GET)
    public String admintest() {
        return "admin/test_set";
    }

    @RequestMapping(value = "/loadquestest/a",method = RequestMethod.GET)
    public String loadquestest() {
        return "admin/questions_load";
    }

}
