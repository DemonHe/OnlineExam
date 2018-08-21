package analyse.service;

import analyse.domain.vo.TestInfoVO;

import java.util.List;

/**
 * Created by Kimone.
 */
public interface TestService {
    public List<TestInfoVO> getTestInfo(Integer courseID, String startTime, String endTime, int start, int length);

    public TestInfoVO getTest(int testID);

    public int getTestListCount(Integer courseID, String startTime, String endTime);
}
