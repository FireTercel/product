package com.clothes.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.WebRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import sun.security.action.PutAllAction;

import com.clothes.entity.User;

@Controller
@RequestMapping(value = "/main")
public class MainController {

	@RequestMapping(value = "/toMain", method = RequestMethod.GET)
	public String toMain() {

		System.out.println("GET+");

		return "main/mainPage";
	}

	@RequestMapping(value = "/toMain", method = RequestMethod.POST)
	public String toMain_POST() {

		System.out.println("POST+");

		return "main/mainPage";
	}

	// 初始化绑定
	@InitBinder
	public void initBinder(WebRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), false));
	}

	/**
	 *  session 前提条件，当前请求session必须可用。
	 *  request.getSession();session.setAttribute();
	 * @param request
	 * @param response
	 * @param session
	 */
	/*public void testAllArguments(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@PathVariable AnyType xxx,@RequestParam AnyTpye id,
			@CookieValue AnyType cookieName,@RequestHeader("") anyType abc) {

	}*/
	
	/*@RequestMapping()
	public String testAllArguments(PrintWriter out,Map model){
		//response.getWrite();
		model.put("", "");
		return "viewName";
		
	}*/
	
	@RequestMapping("/xxx")
	public String testCommand(User user,BindingResult result){
		
		return "xxx";
	}
	
	@RequestMapping("/xxx")
	public void testVoid(PrintWriter out){
		//生成隐含的viewName  ${appName}/test/xxx.do --> 真正的视图名称test/xxx -->找到了这个jsp文件：/WEB-INF/page/test/xxx.jsp
		
	}
	
	public List<User> queryUser(){
		
		return null;
	}

}
