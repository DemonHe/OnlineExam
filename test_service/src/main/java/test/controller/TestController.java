package test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import test.service.GetTestService;
import test.service.InitTestService;
import test.vo.SelectionVO;
import test.vo.SimpleTestVO;
import test.vo.TestVO;

@RestController
public class TestController {

	@Autowired
	private InitTestService initTestService;
	@Autowired
	private GetTestService getTestService;

	@RequestMapping(value = "/init")
	public void initTestList(@RequestParam("studentID") String studentId, @RequestParam("testID") int testId) {
		System.out.println(studentId +testId+"@-@");
		initTestService.initTest(studentId, testId);
	}

	@RequestMapping(value = "/getTest")
	public List<TestVO> getTestList(@RequestParam("studentID") String studentId, @RequestParam("testID") int testId) {
		return initTestService.getTest(studentId, testId);
	}

	@RequestMapping(value = "/undone")
	public List<SimpleTestVO> getUndoneTest(@RequestParam("studentID") String sid) {

		List<SimpleTestVO> testList = getTestService.getUndone(sid);

		return testList;
	}

	@RequestMapping(value = "/processing")
	public List<SimpleTestVO> getProcessingTest(@RequestParam("studentID") String sid) {

		List<SimpleTestVO> testList = getTestService.getProcessing(sid);

		return testList;
	}

	@RequestMapping(value = "/accomplished")
	public List<SimpleTestVO> getAccomplishedTest(@RequestParam("studentID") String sid) {

		List<SimpleTestVO> testList = getTestService.getAccomplished(sid);

		return testList;
	}
}
