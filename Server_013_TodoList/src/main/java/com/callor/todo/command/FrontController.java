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

@WebServlet("/")
public class FrontController extends HttpServlet{

	protected Map<String, TodoCommand> commands;
	
	// FrontController가 최초 호출될 때 한 번 실행되어서 여러가지 변수 등을 초기화하는 코드
	@Override
	public void init(ServletConfig config) throws ServletException {
		commands = new HashMap<String, TodoCommand>();
		commands.put("/", new HomeCommandImplV1()); // home으로 요청하면 HomeCommandImplV1()에 대신 요청을 시키겠다...?
	}
	
	// doGet(), doPost()로 분리하여 요청을 처리하던 방식을 한 개의 method에서 동시에 처리하기
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String urlPath = req.getRequestURI();
		String path = urlPath.substring(req.getContextPath().length());
		
		TodoCommand subCommand = commands.get(path);
		if(subCommand != null) {
			subCommand.execute(req, resp);
		}
	}
}
