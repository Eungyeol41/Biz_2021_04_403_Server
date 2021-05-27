package com.callor.todo.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReqController {

	private final static String prefix = "/WEB-INF/views/";
	private final static String surfix = ".jsp";

	/*
	 * Controller에서 전달받은 jsp 파일을 forwarding하기 위한 Command
	 */
	public static void forward(HttpServletRequest req, HttpServletResponse resp, String file) throws ServletException, IOException {
		
		String viewFile = prefix + file + surfix;
		req.getRequestDispatcher(viewFile).forward(req, resp);
		
	}
	
}
