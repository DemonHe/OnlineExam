package excel.service;

import excel.dao.*;
import excel.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcelManagerServiceImpl implements ExcelManagerService {

    @Autowired
    private CourseDao courseDao;
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private SelectionDao selectionDao;
    @Autowired
    private StudentListDao studentListDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private MarkListDao markListDao;

    @Override
    public int getCourseIDByName(String cname) {
        return courseDao.findIDByName(cname);
    }

    @Override
    public int saveQuestion(Question question) {
        return questionDao.save(question).getId();
    }

    @Override
    public boolean saveSelection(Selection selection) {
        selectionDao.save(selection);
        return true;
    }

    @Override
    public boolean saveStudentList(StudentList studentList) {
        Student student = studentDao.findByEmail(studentList.getEmail());

        if(student!=null){
            studentListDao.save(studentList);

            MarkList markList = new MarkList();
            markList.setMark(0);
            markList.setSid(student.getSid());
            markList.setTestID(studentList.getTestID());
            markList.setIsSubmit(0);

            markListDao.save(markList);

            return true;
        }

        return false;
    }
}
