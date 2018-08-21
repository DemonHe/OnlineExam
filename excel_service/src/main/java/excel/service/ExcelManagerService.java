package excel.service;

import excel.model.Question;
import excel.model.Selection;
import excel.model.StudentList;

public interface ExcelManagerService {

    public int getCourseIDByName(String cname);

    public int saveQuestion(Question question);

    public boolean saveSelection(Selection selection);

    public boolean saveStudentList(StudentList studentList);
}
