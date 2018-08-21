package result.service;

import result.domain.AnswerRecord;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Kimone.
 */
public interface MarkService {
    public double calculateMark(int testID, String sid, LinkedHashMap<Integer, List<AnswerRecord>> map);
}
