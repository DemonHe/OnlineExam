package analyse.controller;

import analyse.domain.entity.MarkPerQuestion;
import analyse.domain.vo.QueryTestPaperParam;
import analyse.domain.vo.TestPaperVO;
import analyse.service.TestPaperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Kimone.
 */

@RestController
public class TestPaperController {
    private static Logger logger = LoggerFactory.getLogger(TestPaperController.class);

    @Autowired
    private TestPaperService testPaperService;

    /**
     * 根据考试号和学生学号单个生成试卷
     * @param testID
     * @param sid
     * @return
     */
    @GetMapping(value = "/getOneTestPaper/{testID}/{sid}")
    public List<TestPaperVO> getOneTestPaper(@PathVariable int testID,
                                             @PathVariable String sid) {
        return testPaperService.getTestPaper(testID, sid);
    }


    @PostMapping(value = "/getTestPaperInBatch")
    public Map<String, List<TestPaperVO>> getTestPaperInBatch(@RequestBody List<QueryTestPaperParam> params) {
        return testPaperService.getTestPapersInBatch(params);
    }

}
