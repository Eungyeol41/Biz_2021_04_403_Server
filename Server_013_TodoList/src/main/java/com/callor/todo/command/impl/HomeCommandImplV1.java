package com.callor.todo.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.callor.todo.command.ReqCommand;
import com.callor.todo.command.TodoCommand;

public class HomeCommandImplV1 implements TodoCommand{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReqCommand.forward(req, resp, "home");
	}

}
