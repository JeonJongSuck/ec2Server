package kr.toy.jjsPrj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ModelAndView selectALl(Model model) {
		ModelAndView mv = new ModelAndView("jsonView");
		
		mv.addObject("data", prjService.viewAll());
		
		return mv;
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ModelAndView insert(@RequestBody PrjVO vo, Model model) {
		ModelAndView mv = new ModelAndView("jsonView");
		
		System.out.println(vo.getbName());
		System.out.println(vo.getName());
		System.out.println(vo.getPrice());
		System.out.println(vo.getDescription());
		System.out.println(vo.getDistributeCompany());
		System.out.println(vo.getMakeCompany());
		
		prjService.insertProdcut(vo);
		
		return mv;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(@RequestBody PrjVO vo, Model model) {
		ModelAndView mv = new ModelAndView("jsonView");
		
		System.out.println(vo.getbName());
		System.out.println(vo.getName());
		System.out.println(vo.getPrice());
		System.out.println(vo.getDescription());
		System.out.println(vo.getDistributeCompany());
		System.out.println(vo.getMakeCompany());
		
		prjService.updateProduct(vo);
		
		return mv;
	}
}
