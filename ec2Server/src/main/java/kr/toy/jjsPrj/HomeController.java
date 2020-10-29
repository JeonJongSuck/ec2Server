package kr.toy.jjsPrj;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */

@RequestMapping(value = "js/rest")
@Controller
public class HomeController {
	
	@Resource
	private PrjService prjService;
	
	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public ModelAndView selectALl(Model model) {
		System.out.println("/selectAll called");
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		mv.addObject("data", prjService.viewAll());
		
		return mv;
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ModelAndView insert(@RequestBody PrjVO vo, Model model) {
		System.out.println("/insert called");

		ModelAndView mv = new ModelAndView("jsonView");
		
		prjService.insertProdcut(vo);

		return mv;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(@RequestBody PrjVO vo, Model model) {
		System.out.println("/update called");

		ModelAndView mv = new ModelAndView("jsonView");
		
		prjService.updateProduct(vo);
		
		return mv;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(@RequestBody PrjVO vo, Model model) {
		System.out.println("/delete called");

		ModelAndView mv = new ModelAndView("jsonView");
		
		prjService.deleteProduct(vo);
		
		return mv;
	}

	@RequestMapping(value = "/csrf-token", method = RequestMethod.GET)
	public @ResponseBody String getCsrfToken(HttpServletRequest request) {
		CsrfToken token = (CsrfToken)request.getAttribute(CsrfToken.class.getName());
		System.out.println(CsrfToken.class.getName());
		return token.getToken();
	}
}
