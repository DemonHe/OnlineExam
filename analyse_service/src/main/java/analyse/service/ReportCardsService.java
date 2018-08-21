package analyse.service;

import analyse.domain.entity.MarkList;
import analyse.domain.vo.MarkVO;

import java.util.List;

/**
 * Created by Kimone.
 */
public interface ReportCardsService {

    /**
     * 根据考试号获取所有参与考试的学生的总成绩列表
     * @param testID
     * @return
     */
    public List<MarkVO> getAllReportCardsByTestID(int testID, int start, int length);

    public MarkVO getOneReportCard(int testID, String sid);
}
