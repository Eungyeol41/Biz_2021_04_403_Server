package com.callor.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.callor.spring.model.MyVO;
import com.callor.spring.service.HomeService;

@Controller
public class HomeController {

	/*
	 * Controller에서 HomeService interface를 상속받은 MyServiceImplV1 클래스를 사용하여 findById() method를 호출한 후 VO 데이터를 받고 싶다
	 * 
	 * 전통적인 Java 클래스 간의 연결은 interface로 객체를 선언하고 클래스로 객체를 생성(초기화)하여 가지고 잇다가 필요한 method를 호출할 수 있도록 만들어진다
	 * 하지만 SpringFramework 환경에서는 객체를 미리 Spring이 만들어 보관하고 있다가 필요한 곳이 있으면 그때그때 배분하여 사용할 수 있도록 만들어준다.
	 * 
	 * 프로젝트가 커져서 많은 객체가 필요할 때 전통적 프로그래밍은 모든 객체를 선언, 생성하여 가지고 (메모리에 보관) 있어야 하기 때문에 어느정도 프로젝트가 커지면 여러가지 관리해야 할 일들이 많아진다
	 * 
	 * SpringFramework 환경에서는 그러한 것들을 극복하기 위하여 미리 객체를 만들어서 Spring 컨테이너라는 곳에 보관해두고 필요한 곳에 적절히 주입을 해준다
	 * 
	 * 이러한 개념을 DI(Dependency Inject)라고 하며 전통 방식과 반대되는 개념이어서 제어의 역전(IOC)라고 부른다.
	 */
	private final HomeService hService;
	
	@Autowired
	public HomeController(HomeService hService) {
		this.hService = hService;
	}
	
	@ResponseBody
	@RequestMapping(value = "/")
	public String index() {
		return "Hello Korea"; // ResponseBody가 있으면 문자열 그대로 웹에 나타남
	}
	
	@RequestMapping(value = "/see", method = RequestMethod.GET) // method = GET(Ctrl + Space) / doGet
	public String home(Model model) {

		model.addAttribute("MY", hService.findById());
		
		// ResponseBody가 없으면 dispatcher가 받아서 InternalResourceViewResolve에 보내서 prefix + "home" + suffix해서 사용자에게 Rendering해라
		return "home";
	}
	
	@RequestMapping(value = "/see", method = RequestMethod.POST) // method = POST(Ctrl + Space) / doPost
	public String home(MyVO vo, Model model) {
		System.out.println(vo.toString());
		
		model.addAttribute("MY", vo);
		return "home";
	}
	
}
