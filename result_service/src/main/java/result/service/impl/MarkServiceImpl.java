package result.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import result.domain.AnswerRecord;
import result.domain.MarkList;
import result.domain.MarkPerQuestion;
import result.repository.MarkListRepository;
import result.repository.MarkPerQuestionRepository;
import result.repository.QuestionRepository;
import result.repository.SelectionRepository;
import result.service.MarkService;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kimone.
 */
@Service
public class MarkServiceImpl implements MarkService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private SelectionRepository selectionRepository;
    @Autowired
    private MarkPerQuestionRepository markPerQuestionRepository;
    @Autowired
    private MarkListRepository markListRepository;

    @Override
    public double calculateMark(int testID, String sid, LinkedHashMap<Integer, List<AnswerRecord>> map) {
        //总分
        double totalMark = 0;

        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            //题目号
            int qid = (int) e.getKey();
            //该题分值
            double score = questionRepository.findById(qid).getScore();

            List<AnswerRecord> list = (List<AnswerRecord>) e.getValue();

            boolean isCorrect =  true;

            //判断学生是否选择正确
            for (AnswerRecord answerRecord : list) {
                int selectionID = answerRecord.getSelectionID();
                int isAnswer = selectionRepository.findById(selectionID).getIsAnswer();//是否为答案
                int isSelected = answerRecord.getIsSelected();//学生是否选择

                if(isAnswer != isSelected) {
                    isCorrect = false;
                }
            }

            if(isCorrect) {
                totalMark += score;
            }

            //存入mark_per_question表中
            MarkPerQuestion markPerQuestion = new MarkPerQuestion();
            markPerQuestion.setTestID(testID);
            markPerQuestion.setSid(sid);
            markPerQuestion.setQid(qid);
            if (isCorrect){
                markPerQuestion.setMark(score);
            }
            markPerQuestionRepository.save(markPerQuestion);
        }

//        MarkList markList = new MarkList();
//        markList.setTestID(testID);
//        markList.setSid(sid);
//        markList.setMark(totalMark);
        markListRepository.setMark(totalMark,testID, sid);
        return totalMark;
    }
}
