package com.callor.todo.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.callor.todo.command.impl.HomeCommandImplV1;
import com.callor.todo.command.impl.TodoCommandImplV1;

/*
 * Servlet App에서는 Servlet(Controller) 클래스를 다수 선언하고, 필요할 때마다 URI(URL)을 Mapping하여 기능을 수행할 수 있도록 한다
 * 
 * 하지만, 프로젝트가 커지면 다수의 Controller가 생성되고 그 때마다 URI Mapping을 하는 데 많은 어려움을 겪을 수 있다
 * 똑같은 객체를 상속받고, 같은 method(doGet, doPost)를 사용하여 코드를 작성하는데 관리가 어려워지기 시작한다
 * 
 * URI Mapping을 한 곳으로 집중하고
 * POJO(Plan Old Java Object, 어떤 클래스를 상속받지 않은 일반적인 자바 클래스 형식의 코드)를 사용하여 프로젝트를 진행하도록 하는 것
 */
@WebServlet("/")
public class FrontController extends HttpServlet{

	// URI mapping을 선언하여 URI에 대한 객체를 준비하여 보관한 장소
	// TodoCommand Interface는 command 객체들의 prototype으로 선언되어 있고 여기의 commands mapper 객체에는 여러가지 command 객체들을 저장해 둘 수 있다.
	// Interface를 사용하지 않고 Object를 사용할 수 있지만 Object 클래스는 상대적으로 연산비용이 많이 소요되는 클래스 객체이므로 
	// 		조금 더 효율적으로 사용하기 위하여 Interface를 선언해 두었다
	protected Map<String, TodoCommand> commands;
	
	// FrontController가 최초 호출될 때 한 번 실행되어서 여러가지 변수 등을 초기화하는 코드
	@Override
	public void init(ServletConfig config) throws ServletException {
		commands = new HashMap<String, TodoCommand>();
		/*
		 * 만약 http://localhost:8080/todo/로 요청이 오면 HomeCommandImplV1 객체를 사용하여 요청을 처리하기 위한 준비
		 */
		commands.put("/", new HomeCommandImplV1()); 
		// 만약 http://localhost:8080/todo/insert로 요청이 오면 TodoCommandImplV1 객체를 사용하여 요청을 처리하기 위한 준비
		// 		를 commands라는 Map에 저장을 해놓는다.
		commands.put("/insert", new TodoCommandImplV1());
		commands.put("/expire", new TodoCommandImplV1());
	}
	
	// doGet(), doPost()로 분리하여 요청을 처리하던 방식을 한 개의 method에서 동시에 처리하기
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		// Web에서 요청한 path 가져오기
		String urlPath = req.getRequestURI();
		String path = urlPath.substring(req.getContextPath().length());
		
		// req된 URI 중에서 실제 subPath 부분을 사용하여 처리할 객체를 Map으로부터 추출하기
		TodoCommand subCommand = commands.get(path);
		if(subCommand != null) {
			// 각 Command 객체의 
			subCommand.execute(req, resp);
		}
	}
}
