package analyse.repository;

import analyse.domain.entity.Test;

import java.util.List;

/**
 * Created by Kimone.
 */
public interface TestDao {
    public List<Test> getTestList(Integer courseID, String startTime, String endTime, int start, int length);

    public int getTestListCount(Integer courseID, String startTime, String endTime);
}
