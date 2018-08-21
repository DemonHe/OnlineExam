package attribute.service;

import attribute.dao.CourseDao;
import attribute.dao.QuestionDao;
import attribute.dao.ScorePerQuestionDao;
import attribute.dao.TestDao;
import attribute.model.ScorePerQuestion;
import attribute.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TestManageServiceImpl implements TestManageService {
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private TestDao testDao;
    @Autowired
    private ScorePerQuestionDao scorePerQuestionDao;
    @Autowired
    private QuestionDao questionDao;

    @Override
    public List<String> getAllCourseNames() {
        return courseDao.findAllNames();
    }

    @Override
    public int getCourseID(String cname) {
        return courseDao.findIDByName(cname);
    }

    @Override
    public int setTest(Test test, Map<Integer, Double> scores) {

        Map<Double,Integer> scoreMap = new HashMap<>();
        for(int i = 1;i<=scores.size();i++){
            double score =  scores.get(i);
            if(scoreMap.containsKey(score)){
                scoreMap.put(score,scoreMap.get(score)+1);
            }else{
                scoreMap.put(score,1);
            }
        }

        boolean isEnough = true;
        for(double score:scoreMap.keySet()){
            int num_need = scoreMap.get(score);
            int num_act = questionDao.findByCourseIDAndScore(test.getCourseID(),score);
            if(num_need>num_act){
                isEnough = false;
                break;
            }
        }

        if(isEnough){
            Test t = testDao.save(test);
            int tid = t.getId();

            for(int i = 1;i<=scores.size();i++){
                ScorePerQuestion spq = new ScorePerQuestion(tid,i,scores.get(i));
                scorePerQuestionDao.save(spq);
            }

            return tid;
        }else{
            return -1;
        }

    }
}
