package attribute.controller;

import attribute.model.Test;
import attribute.service.TestManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/test")
@RestController
public class TestController {

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestManageService testManageService;

    @RequestMapping(value="/getCourseNames")
    public List<String> getCourseNames(){
        return testManageService.getAllCourseNames();
    }

    @RequestMapping(value="/setTest",method= RequestMethod.POST)
    public int seTest(HttpServletRequest request){
        //logger.debug("setTest");
        String coureseName = request.getParameter("coursename");
        String starttime = request.getParameter("starttime");
        String endtime = request.getParameter("endtime");
        String qnum_str = request.getParameter("qnum");
        int qnum = Integer.parseInt(qnum_str);

        starttime = starttime.replace("T"," ");
        starttime = starttime + ":00";
        endtime = endtime.replace("T"," ");
        endtime = endtime + ":00";

        Test test = new Test();
        test.setCourseID(testManageService.getCourseID(coureseName));
        test.setStart(starttime);
        test.setEnd(endtime);
        test.setQnum(qnum);

        Map<Integer,Double> scores = new HashMap<>();
        for(int i = 1;i<=qnum;i++){
            String score_str = request.getParameter("scoreof"+i);
            double score = Double.parseDouble(score_str);
            scores.put(i,score);
        }

        return testManageService.setTest(test,scores);
    }
}
