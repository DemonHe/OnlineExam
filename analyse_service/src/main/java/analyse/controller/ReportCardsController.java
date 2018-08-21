package analyse.controller;

import analyse.domain.entity.Course;
import analyse.domain.entity.MarkList;
import analyse.domain.entity.Test;
import analyse.domain.vo.MarkVO;
import analyse.domain.vo.TestInfoVO;
import analyse.repository.CourseRepository;
import analyse.repository.ReportCardsRepository;
import analyse.repository.TestRepository;
import analyse.service.ReportCardsService;
import analyse.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Kimone.
 */
@RestController
public class ReportCardsController {

    private static Logger logger = LoggerFactory.getLogger(ReportCardsController.class);

    @Autowired
    private ReportCardsService reportCardsService;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TestService testService;
    @Autowired
    private ReportCardsRepository reportCardsRepository;
    @Autowired
    private TestRepository testRepository;


    /**
     * 获取所有课程信息
     * @return
     */
    @GetMapping(value = "/getAllCourses")
    public List<Course> getAllCourses() {
        List<Course> list = courseRepository.findAll();
        return list;
    }

    /**
     * 获取符合条件的考试信息
     * @param request
     * @return
     */
    @PostMapping(value = "/getTestInfo")
    public Map<String, Object> getTestInfo(HttpServletRequest request) {
        Map<String, String[]> in = request.getParameterMap();
        Map<String, String> map = new HashMap<>();
        Iterator<Map.Entry<String, String[]>> i = in.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry<String, String[]> entry = i.next();
            map.put(entry.getKey(), entry.getValue()[0]);
            System.out.println("key="+entry.getKey()+", value="+entry.getValue()[0]);
        }

        int start = Integer.parseInt(map.get("start").trim());
        int length = Integer.parseInt(map.get("length").trim());
        int draw = Integer.parseInt(map.get("draw").trim());

        Integer courseID = map.get("courseID").trim().equals("")?null:Integer.parseInt(map.get("courseID").trim());
        String startTime = map.get("startTime").trim();
        String endTime = map.get("endTime").trim();
//        if (!startTime.equals("")) {
//            startTime = startTime.replace('T',' ')+":00";
//        }
//        if (!endTime.equals("")) {
//            endTime = endTime.replace('T',' ')+":00";
//        }
        System.out.println("startTime="+startTime);
        List<TestInfoVO> testInfoVOS = testService.getTestInfo(courseID, startTime, endTime, start, length);

        int recordsTotal = testService.getTestListCount(courseID, startTime, endTime);
        int recordsFiltered = recordsTotal;

        Map<String, Object> data = new HashMap<>();
        data.put("draw", draw);
        data.put("recordsTotal", recordsTotal);
        data.put("recordsFiltered", recordsFiltered);
        data.put("list", testInfoVOS);

        return data;
    }

    /**
     * 获取制定的考试信息
     * @param testID
     * @return
     */
    @GetMapping("/getTest")
    public TestInfoVO getTest(int testID) {
        return testService.getTest(testID);
    }
    /**
     * 根据考试号获取多个学生的成绩单
     * @return
     */
    @PostMapping(value = "/getReportCards")
    public Map<String, Object> getReportCards(HttpServletRequest request){
        Map<String, String[]> in = request.getParameterMap();
        Map<String, String> map = new HashMap<>();
        Iterator<Map.Entry<String, String[]>> i = in.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry<String, String[]> entry = i.next();
            map.put(entry.getKey(), entry.getValue()[0]);
            System.out.println("key="+entry.getKey()+", value="+entry.getValue()[0]);
        }

        int testID = Integer.parseInt(map.get("testID").trim());
        int start = Integer.parseInt(map.get("start").trim());
        int length = Integer.parseInt(map.get("length").trim());
        int draw = Integer.parseInt(map.get("draw").trim());

        Map<String, Object> data = new HashMap<>();
        List<MarkVO> markVOList = reportCardsService.getAllReportCardsByTestID(testID, start, length);
        int recordsTotal = reportCardsRepository.countByTestID(testID);
        data.put("draw", draw);
        data.put("list", markVOList);
        data.put("recordsTotal", recordsTotal);
        data.put("recordsFiltered", recordsTotal);
        return data;
    }

    /**
     * 根据考试号和学生学号获取某个学生的成绩单
     * @param testID
     * @param sid
     * @return
     */
    @GetMapping(value = "/getOneReportCard/{testID}/{sid}")
    public MarkVO getReportCard(@PathVariable int testID,
                                  @PathVariable String sid){
        return reportCardsService.getOneReportCard(testID, sid);
    }
}
