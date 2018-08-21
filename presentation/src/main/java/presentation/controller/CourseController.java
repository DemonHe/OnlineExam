package presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CourseController {

    @RequestMapping(value = "/addTest",method = RequestMethod.GET)
    public String addTest() {
        return "admin/test_set";
    }

    @RequestMapping(value = "/allCourses",method = RequestMethod.GET)
    public String showAllCourses() {
        return "admin/courses_management";
    }

    @RequestMapping(value = "/courseDetail",method = RequestMethod.GET)
    public String showCourseDetail(Model model,HttpServletRequest request) {
        model.addAttribute("courseID",request.getParameter("courseID"));
        return "admin/courseInfo";
    }
}
