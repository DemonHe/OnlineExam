package presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TimeController {

	@RequestMapping(value = "/aaa", method = RequestMethod.GET)
	public String hello(Model model) {
		model.addAttribute("name", "Dear");
		return "hello";
	}

	@RequestMapping(value = "/bbb", method = RequestMethod.GET)
	public String admintest() {
		return "admin/test_set";
	}
}
