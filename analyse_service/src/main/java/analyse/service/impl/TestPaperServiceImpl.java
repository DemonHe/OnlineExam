package analyse.service.impl;

import analyse.domain.entity.*;
import analyse.domain.vo.QueryTestPaperParam;
import analyse.domain.vo.TestPaperVO;
import analyse.repository.*;
import analyse.service.TestPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kimone.
 */

@Service
public class TestPaperServiceImpl implements TestPaperService {

    @Autowired
    private TestPaperRepository testPaperRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRecordRepository answerRecordRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<TestPaperVO> getTestPaper(int testID, String sid) {
        List<TestPaperVO> testPaperList = new ArrayList<>();
        List<MarkPerQuestion> list = testPaperRepository.findByTestIDAndSid(testID, sid);
        for(MarkPerQuestion markPerQuestion:list) {
            TestPaperVO vo = convertToVO(markPerQuestion);
            testPaperList.add(vo);
        }
        return testPaperList;
    }

    @Override
    public Map<String, List<TestPaperVO>> getTestPapersInBatch(List<QueryTestPaperParam> params) {
        Map<String, List<TestPaperVO>> map = new HashMap<>();

        for(QueryTestPaperParam param : params) {
            int testID = param.getTestID();
            String sid = param.getSid();
            List<MarkPerQuestion> list = testPaperRepository.findByTestIDAndSid(testID, sid);
            List<TestPaperVO> testPaperList = new ArrayList<>();
            for(MarkPerQuestion markPerQuestion:list) {
                TestPaperVO vo = convertToVO(markPerQuestion);
                testPaperList.add(vo);
            }
            map.put(sid, testPaperList);
        }

        return map;
    }

    private TestPaperVO convertToVO(MarkPerQuestion markPerQuestion) {
        TestPaperVO vo = new TestPaperVO();

        int testID = markPerQuestion.getTestID();
        String sid = markPerQuestion.getSid();
        int qid = markPerQuestion.getQid();

        Question q = questionRepository.findById(qid);

        Test test = testRepository.findById(testID);
        Course course = courseRepository.findById(test.getCourseID());
        Student student = studentRepository.findBySid(sid);

        //选项内容
        List<String> selections = answerRecordRepository.findSelection(testID, sid, qid);
        //学生答案
        List<String> answers = answerRecordRepository.findAnswers(testID,sid,qid);

        vo.setTestID(testID);
        vo.setCourseName(course.getName());
        vo.setName(student.getName());
        vo.setSid(sid);
        vo.setQuestion(q.getContent());
        vo.setSelections(selections);
        vo.setAnswers(answers);
        vo.setMark(markPerQuestion.getMark());

        return vo;
    }
}
