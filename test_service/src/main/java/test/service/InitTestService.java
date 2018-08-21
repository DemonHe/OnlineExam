package test.service;

import java.util.ArrayList;

import test.vo.TestVO;

public interface InitTestService {
	/**
	 * 
	 * @param studentId
	 * @param testId
	 * 
	 */
	public void initTest(String studentId, int testId);

	/**
	 * 
	 * @param studentId
	 * @param testId
	 * @return 随机生成的题目+序号+选项;选项间以分号隔开
	 */
	public ArrayList<TestVO> getTest(String studentId, int testId);
}
