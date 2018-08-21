package analyse.service.impl;

import analyse.domain.entity.MarkList;
import analyse.domain.entity.Student;
import analyse.domain.vo.MarkVO;
import analyse.repository.ReportCardsRepository;
import analyse.repository.StudentRepository;
import analyse.service.ReportCardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kimone.
 */
@Service
public class ReportCardsServiceImpl implements ReportCardsService {

    @Autowired
    private ReportCardsRepository reportCardsRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<MarkVO> getAllReportCardsByTestID(int testID, int start, int length) {
        List<MarkList> markLists = reportCardsRepository.findForTable(testID, start, length);
        List<MarkVO> list = new ArrayList<>();
        for(MarkList markList : markLists) {
            MarkVO vo = convertToVO(markList);
            list.add(vo);
        }
        return list;
    }

    @Override
    public MarkVO getOneReportCard(int testID, String sid) {
        MarkList markList = reportCardsRepository.findByTestIDAndSid(testID, sid);
        MarkVO markVO = convertToVO(markList);
        return markVO;
    }

    private MarkVO convertToVO(MarkList markList) {
        MarkVO vo = new MarkVO();
        String sid = markList.getSid();
        Student student = studentRepository.findBySid(sid);
        vo.setSid(sid);
        vo.setSname(student.getName());
        vo.setMark(markList.getMark());
        vo.setCheckBox("<input type='checkbox' class='markcb' value='"+sid+"' />");
        return vo;
    }
}
