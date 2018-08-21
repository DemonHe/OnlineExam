package test.service;

import java.util.List;

import test.vo.SimpleTestVO;

public interface GetTestService {

	public List<SimpleTestVO> getUndone(String sid);
	
	public List<SimpleTestVO> getProcessing(String sid);
	
	public List<SimpleTestVO> getAccomplished( String sid);
}
