package attribute.service;

import attribute.model.Test;

import java.util.List;
import java.util.Map;

public interface TestManageService {

    public List<String> getAllCourseNames();

    public int getCourseID(String cname);

    public int setTest(Test test, Map<Integer,Double> scores);
}
