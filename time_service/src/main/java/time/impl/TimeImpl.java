package time.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import time.repository.TestRepository;
import time.service.TimeService;

@Service
public class TimeImpl implements TimeService {

	@Autowired
	TestRepository testRepo;

	@Override
	public String start(int testID) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long current = System.currentTimeMillis();
		Date dt = new Date(current);
		String date = sdf.format(dt);
		try {
			String start = testRepo.findStartById(testID);
			if (start == null)
				return date;
			Date dt1 = sdf1.parse(start);
			return sdf.format(dt1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	@Override
	public String duration(int testID) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long current = System.currentTimeMillis();
		java.util.Date dt = new Date(current);
		String date = sdf.format(dt);
		try {
			String end = testRepo.findEndById(testID);
			if (end == null)
				return date;
			Date dt1 = sdf1.parse(end);
			return sdf.format(dt1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

}
