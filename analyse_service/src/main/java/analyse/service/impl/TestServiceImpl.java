package analyse.service.impl;

import analyse.domain.entity.Course;
import analyse.domain.entity.Test;
import analyse.domain.vo.TestInfoVO;
import analyse.repository.CourseRepository;
import analyse.repository.TestDao;
import analyse.repository.TestRepository;
import analyse.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kimone.
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestDao testDao;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<TestInfoVO> getTestInfo(Integer courseID, String startTime, String endTime, int start, int length) {
        List<Test> list = testDao.getTestList(courseID, startTime, endTime, start, length);
        List<TestInfoVO> testInfoList = new ArrayList<>();
        for(Test test : list) {
            TestInfoVO vo = convertToVO(test);
            testInfoList.add(vo);
        }
        return testInfoList;
    }

    @Override
    public TestInfoVO getTest(int testID) {
        Test test = testRepository.findById(testID);
        TestInfoVO vo = convertToVO(test);
        return vo;
    }

    @Override
    public int getTestListCount(Integer courseID, String startTime, String endTime) {
        return testDao.getTestListCount(courseID, startTime, endTime);
    }

    private TestInfoVO convertToVO(Test test) {
        int testID = test.getId();
        int cID = test.getCourseID();
        TestInfoVO vo = new TestInfoVO();
        Course course = courseRepository.findById(cID);

        vo.setCourseName(course.getName());
        vo.setTestID(testID);
        vo.setStartTime(test.getStart());
        vo.setEndTime(test.getEnd());

        return vo;
    }
}
