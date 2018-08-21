package result.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import result.domain.AnswerRecord;
import result.repository.AnswerRecordRepository;
import result.repository.MarkListRepository;
import result.service.MarkService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Kimone.
 */
@RestController
public class MarkController {
    private static Logger logger = LoggerFactory.getLogger(MarkController.class);

    @Autowired
    private AnswerRecordRepository answerRecordRepository;
    @Autowired
    private MarkService markService;
    @Autowired
    private MarkListRepository markListRepository;

    @PostMapping(value = "/submit")
    public double  calculateMark(Integer testID, String sid, HttpServletRequest request) {
        LinkedHashMap<Integer, List<AnswerRecord>> map = new LinkedHashMap<>();

        Map<String, String[]> in = request.getParameterMap();
        Map<String, String> out = new HashMap<>();
        Iterator<Map.Entry<String, String[]>> i = in.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry<String, String[]> entry = i.next();
            out.put(entry.getKey(), entry.getValue()[0]);
            String key = entry.getKey();
            String value = entry.getValue()[0];
            System.out.println("key="+key+", value="+value);

            if(key.contains("data[")){
                int qid = Integer.parseInt(key.substring(5,key.length()-1));
                String answer = value;
                List<AnswerRecord> list = answerRecordRepository.findByTestIDAndSidAndQid(testID,sid,qid);

                for (AnswerRecord answerRecord : list) {
                    String optOrder = answerRecord.getOptOrder();
                    if(answer.contains(optOrder)) {
                        answerRecordRepository.setIsSelected(1, answerRecord.getId());
                    }
                }

                map.put(qid, answerRecordRepository.findByTestIDAndSidAndQid(testID,sid,qid));
            }
        }

        return markService.calculateMark(testID, sid, map);
    }

    @GetMapping(value = "/getMark")
    public double getMark(String sid, Integer testID){
        return markListRepository.getMark(sid, testID);
    }
}
