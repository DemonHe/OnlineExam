package test.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.repository.TestSearchRepository;
import test.service.GetTestService;
import test.vo.SimpleTestVO;

@Service
public class GetTestImpl implements GetTestService {

	@Autowired
	private TestSearchRepository testSearchRepo;

	@Override
	public List<SimpleTestVO> getUndone(String sid) {
		List<SimpleTestVO> list = new ArrayList<SimpleTestVO>();

		long time = System.currentTimeMillis();

		list = testSearchRepo.findUndone(sid, getDate(time, (long) (30 * 60 * 1000)));
		return list;
	}

	@Override
	public List<SimpleTestVO> getProcessing(String sid) {
		List<SimpleTestVO> list = new ArrayList<SimpleTestVO>();

		long time = System.currentTimeMillis();

		list = testSearchRepo.findProcessing(sid, getDate(time, (long) (30 * 60 * 1000)), getDate(time, 0));
		return list;
	}

	@Override
	public List<SimpleTestVO> getAccomplished(String sid) {
		List<SimpleTestVO> list = new ArrayList<SimpleTestVO>();

		long time = System.currentTimeMillis();

		list = testSearchRepo.findAccomplished(sid, getDate(time, 0));
		return list;
	}

	private String getDate(long time, long delay) {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		Date dt = new Date(time + delay);
		String date = sdf.format(dt);
		return date;
	}

}
