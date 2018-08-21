package course.controller;

import course.model.Course;
import course.model.Test;
import course.service.CourseManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/course")
@RestController
public class CourseController {
    @Autowired
    private CourseManagerService courseManagerService;

    @RequestMapping(value="/getAllCourses")
    public List<Course> getAllCourses(){
        return courseManagerService.getAllCourses();
    }

    @RequestMapping(value="/searchCourses", method = RequestMethod.POST)
    public List<Course> searchCourses(@RequestParam("coursename") String coursename){
        return courseManagerService.searchByNameLike(coursename);
    }

    @RequestMapping(value="/deleteCourse", method = RequestMethod.POST)
    public void deleteCourse(@RequestParam("courseID") int courseID){
        courseManagerService.deleteCourseByID(courseID);
    }

    @RequestMapping(value="/isCourseNameExist", method = RequestMethod.POST)
    public boolean isCourseNameExist(@RequestParam("coursename") String coursename){
        return courseManagerService.isCourseNameExist(coursename);
    }


    @RequestMapping(value="/addCourse", method = RequestMethod.POST)
    public boolean addCourse(HttpServletRequest request){
        Course course = new Course();
        course.setName(request.getParameter("coursename"));
        course.setIntroduction(request.getParameter("introduction"));
        return courseManagerService.addCourse(course);
    }

    @RequestMapping(value="/getDetailofCourse", method = RequestMethod.POST)
    public Map getDetailofCourse(@RequestParam("courseID") String courseID_str){
        Map map = new HashMap();
        int courseID = Integer.parseInt(courseID_str);
        Course course = courseManagerService.getCourseByID(courseID);
        map.put("coursename",course.getName());
        map.put("introduction",course.getIntroduction());

        List<Test> tests = courseManagerService.getTestsofCourse(courseID);
        map.put("tests",tests);

        return map;
    }
}
