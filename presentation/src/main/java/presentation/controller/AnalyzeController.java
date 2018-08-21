package presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kimone.
 */
@Controller
public class AnalyzeController {

    @RequestMapping(value = "/testInfo")
    public String getTestInfo() {
        return "admin/testInfo";
    }

    @RequestMapping(value = "/markList")
    public ModelAndView getMarkList(int testID) {
        ModelAndView modelAndView = new ModelAndView("admin/markList");
        Integer id = new Integer(testID);
        modelAndView.addObject("testID",id);
        System.out.println("testID="+id);
        return modelAndView;
    }

    @RequestMapping(value = "/testPapers/{testID}/{sids}")
    public ModelAndView getTestPaper(@PathVariable Integer testID, @PathVariable String sids) {
        ModelAndView modelAndView = new ModelAndView("admin/testPapers");
        String[] stus = sids.split("-");
        List<String> sidList = new ArrayList<>();
        for (int i = 1; i < stus.length; i++) {
            sidList.add(stus[i]);
            System.out.println("sid="+stus[i]);
        }
//        Integer id = new Integer(testID);
        modelAndView.addObject("testID", testID);
        modelAndView.addObject("sidList", sidList);
        return modelAndView;
    }

    @RequestMapping(value = "/testPaperDetail/{testID}/{sid}/{name}")
    public ModelAndView getTestPaperDetail(@PathVariable Integer testID, @PathVariable String sid, @PathVariable String name) {
        ModelAndView modelAndView = new ModelAndView("admin/testPaperDetail");
        modelAndView.addObject("testID", testID);
        modelAndView.addObject("sid", sid);
        modelAndView.addObject("name", name);
        return modelAndView;
    }
}
