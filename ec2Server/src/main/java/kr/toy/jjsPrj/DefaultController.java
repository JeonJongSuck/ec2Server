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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.toy.jjsPrj.product.vo.ProductVO;

/**
 * Handles requests for the application home page.
 */

@RequestMapping(value = "/rest", method = RequestMethod.POST)
@Controller
public class DefaultController {
	
	@Resource
	private ProductService productService;
	
	@RequestMapping(value = "/selectAll")
	public ModelAndView selectALl(@RequestBody ProductVO vo) {
		System.out.println("/selectAll called");
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		mv.addObject("data", productService.viewAll(vo));

		return mv;
	}
	
	@RequestMapping(value = "/insert")
	public ModelAndView insert(@RequestBody ProductVO vo, Model model) {
		System.out.println("/insert called");

		ModelAndView mv = new ModelAndView("jsonView");
		
		productService.insertProdcut(vo);

		return mv;
	}
	
	@RequestMapping(value = "/update")
	public ModelAndView update(@RequestBody ProductVO vo, Model model) {
		System.out.println("/update called");

		ModelAndView mv = new ModelAndView("jsonView");
		
		productService.updateProduct(vo);
		
		return mv;
	}
	
	@RequestMapping(value = "/delete")
	public ModelAndView delete(@RequestBody ProductVO vo, Model model) {
		System.out.println("/delete called");

		ModelAndView mv = new ModelAndView("jsonView");
		
		productService.deleteProduct(vo);
		
		return mv;
	}

	@RequestMapping(value = "/csrf-token")
	public @ResponseBody String getCsrfToken(HttpServletRequest request) {
		CsrfToken token = (CsrfToken)request.getAttribute(CsrfToken.class.getName());
		System.out.println(CsrfToken.class.getName());
		return token.getToken();
	}
}
