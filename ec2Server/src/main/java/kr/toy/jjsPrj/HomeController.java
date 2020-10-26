package kr.toy.jjsPrj;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */

@RequestMapping(value = "js/rest")
@Controller
public class HomeController {
	
	@Autowired
	private PrjService prjService;
	
	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public ModelAndView selectALl(Locale locale, Model model) {
		ModelAndView mv = new ModelAndView("jsonView");
		
		mv.addObject("data", prjService.viewAll());
		
		return mv;
	}
}
