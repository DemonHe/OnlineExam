package course.service;

import course.model.Course;
import course.model.Test;

import java.util.List;

public interface CourseManagerService {

    public List<Course> getAllCourses();

    public Course getCourseByID(int id);

    public Course getCourseByName(String name);

    public List<Course> searchByNameLike(String name);

    public List<Test> getTestsofCourse(int cid);

    public void deleteCourseByID(int id);

    public boolean addCourse(Course course);

    public boolean isCourseNameExist(String name);
}
