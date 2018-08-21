package excel.controller;

import excel.model.Question;
import excel.model.Selection;
import excel.model.StudentList;
import excel.service.ExcelManagerService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequestMapping("/excel")
@RestController
public class ExcelController {
    @Autowired
    private ExcelManagerService excelManagerService;

    private final Logger logger = LoggerFactory.getLogger(ExcelController.class);
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = System.getProperty("user.dir")+"\\src\\main\\resources";

    /**
     * Single file upload
     * */
   // @PostMapping("/uploadFile")
    public String uploadFile(MultipartFile uploadfile) {

        logger.debug("Single file upload!");

        String fileName = UPLOADED_FOLDER+ "\\upload\\" +uploadfile.getOriginalFilename();
        File file = new File(fileName);

        if(file.exists()){
            file.delete();
            logger.debug("file delete");
        }

        try {
            byte[] bytes = uploadfile.getBytes();
            Path path = Paths.get(fileName);
            Files.write(path, bytes);
            return uploadfile.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";

    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> downloadFile()
            throws IOException {
        String filePath = UPLOADED_FOLDER + "\\download\\试题模板.xlsx";
        FileSystemResource file = new FileSystemResource(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", new String( file.getFilename().getBytes("gb2312"), "ISO8859-1" )));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }

    @RequestMapping(value = "/importQuestions", method = RequestMethod.POST)
    public String importQuestions(@RequestParam("file") List<MultipartFile> files) {

        for(int i = 0;i<files.size();i++){
            String filename = uploadFile(files.get(i));
            handleExcelofQuestions(filename);
        }
        return "true";
    }

    private void handleExcelofQuestions(String filename) {

        try {
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(UPLOADED_FOLDER + "\\upload\\"+filename);

            Workbook wb = new XSSFWorkbook(is);
            Sheet sheet = wb.getSheetAt(0);
            int firstRowIndex = sheet.getFirstRowNum();
            int lastRowIndex = sheet.getLastRowNum();

            for(int rIndex = firstRowIndex + 1; rIndex <= lastRowIndex; rIndex ++){
                Row row = sheet.getRow(rIndex);

                if(row != null){
                    Question question = new Question();
                    int firstCellIndex = row.getFirstCellNum();
                    int lastCellIndex = row.getLastCellNum();

                    String cname = row.getCell(firstCellIndex).toString();
                    int cid = excelManagerService.getCourseIDByName(cname);
                    question.setCourseID(cid);
                    question.setContent(row.getCell(firstCellIndex + 1).toString());
                    double score = Double.parseDouble(row.getCell(firstCellIndex + 2).toString());
                    question.setScore(score);
                    int qid = excelManagerService.saveQuestion(question);

                    for(int cIndex = firstCellIndex + 3; cIndex < lastCellIndex; cIndex ++){
                        Cell cell = row.getCell(cIndex);
                        System.out.println(cell.toString());
                        if ((cIndex - firstCellIndex) % 2 == 1) {
                            Selection selection = new Selection();
                            selection.setQid(qid);
                            selection.setContent(cell.toString());

                            String isAnswer = row.getCell(++cIndex).toString().trim();
                            if(isAnswer.equals("是")){
                                selection.setIsAnswer(1);
                            }else{
                                selection.setIsAnswer(0);
                            }

                            excelManagerService.saveSelection(selection);
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/importStudents")
    public boolean importStudents(@RequestParam("file") MultipartFile file, @RequestParam("testID") int testID) {
        String filename = uploadFile(file);
        handleExcelofStudents(filename, testID);
        return true;
    }

    private void handleExcelofStudents(String filename, int testID) {

        try {
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(UPLOADED_FOLDER + "\\upload\\"+filename);

            Workbook wb = new XSSFWorkbook(is);
            Sheet sheet = wb.getSheetAt(0);
            int firstRowIndex = sheet.getFirstRowNum();
            int lastRowIndex = sheet.getLastRowNum();

            for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex ++){
                Row row = sheet.getRow(rIndex);

                if(row != null){

                    int firstCellIndex = row.getFirstCellNum();
                    String email = row.getCell(firstCellIndex).toString();

                    //判断邮箱格式是否正确
                    Pattern p = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
                    Matcher m = p.matcher(email);

                    if(m.matches()){
                        StudentList studentList = new StudentList();
                        studentList.setTestID(testID);
                        studentList.setEmail(email);

                        excelManagerService.saveStudentList(studentList);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
