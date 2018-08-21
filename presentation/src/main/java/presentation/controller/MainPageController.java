package presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by sam.
 */
@Controller
public class MainPageController {
    @RequestMapping(value = "/index")
    public String getMainPage(){
        return "admin/main";
    }

    @RequestMapping(value = "/admin")
    public String adminLogIn(){
        return "admin/signIn_admin";
    }

    @RequestMapping(value = "/student")
    public String studentLogIn(){
        return "admin/signIn_student";
    }

//    @RequestMapping(value = "/admin_re")
//    public String adminRegister(){
//        return "admin/register_admin";
//    }

    @RequestMapping(value = "/student_re")
    public String studentRegister(){
        return "admin/register_student";
    }

    @RequestMapping(value = "/register_active")
    public ModelAndView active(@RequestParam("code") String code){
        ModelAndView modelAndView = new ModelAndView("admin/register_active");
        modelAndView.addObject("code",code);
        System.out.println("@_@ code is:"+code);
        return  modelAndView;
    }
}
