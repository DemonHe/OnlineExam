package course.service;

import course.dao.CourseDao;
import course.dao.TestDao;
import course.model.Course;
import course.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseManagerServiceImpl implements CourseManagerService {

    @Autowired
    private CourseDao courseDao;
    @Autowired
    private TestDao testDao;

    @Override
    public List<Course> getAllCourses() {
        return courseDao.findAll();
    }

    @Override
    public Course getCourseByID(int id) {
        return courseDao.findById(id);
    }

    @Override
    public Course getCourseByName(String name) {
        return courseDao.findByName(name);
    }

    @Override
    public List<Course> searchByNameLike(String name) {
        return courseDao.findByNameLike(name);
    }

    @Override
    public List<Test> getTestsofCourse(int cid) {
        return testDao.findByCourseID(cid);
    }

    @Override
    public void deleteCourseByID(int id) {
        courseDao.delete(id);
    }

    @Override
    public boolean addCourse(Course course) {
        return courseDao.save(course)!=null;
    }

    @Override
    public boolean isCourseNameExist(String name) {
        return courseDao.findByName(name)!=null;
    }
}
