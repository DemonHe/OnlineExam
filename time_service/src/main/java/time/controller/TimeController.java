package time.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import time.service.TimeService;

@RestController
public class TimeController {
	
	@Autowired
	TimeService timeService;

	@RequestMapping(value="/start")
	public String getStart(@RequestParam("testID") int testId) {
		return timeService.start(testId);
	}
	
	@RequestMapping(value="/duration")
	public String getDuration(@RequestParam("testID") int testId) {
		
		return timeService.duration(testId);
	}
}
