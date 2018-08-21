package analyse.service;

import analyse.domain.vo.QueryTestPaperParam;
import analyse.domain.vo.TestPaperVO;

import java.util.List;
import java.util.Map;

/**
 * Created by Kimone.
 */
public interface TestPaperService {
    /**
     * 单个获取成绩单
     * @param testID
     * @param sid
     * @return
     */
    public List<TestPaperVO> getTestPaper(int testID, String sid);

    /**
     * 批量获取成绩单
     * @param params
     * @return
     */
    public Map<String, List<TestPaperVO>> getTestPapersInBatch(List<QueryTestPaperParam> params);
}
