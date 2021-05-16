package com.callor.diet.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.callor.diet.model.FoodDTO;
import com.callor.diet.model.MyFoodVO;
import com.callor.diet.service.FoodService;
import com.callor.diet.service.MyFoodService;
import com.callor.diet.service.impl.FoodServiceImplV1;
import com.callor.diet.service.impl.MyFoodServiceImplV1;

@WebServlet("/food/*")
public class FoodController extends HttpServlet{

	private static final long serialVersionUID = 5430871336219122803L;
	protected FoodService fdService;
	protected MyFoodService mfService;
	public FoodController() {
		fdService = new FoodServiceImplV1();
		mfService = new MyFoodServiceImplV1();
	}

	// anchor link를 클릭했을 때 처리할 method
	// a tag : <a href>
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 요청주소에 /food 다음에 오는 sub 요청문자열을 추출
		// /food/search라고 요청을 보내면 /search 문자열 추출
		String subPath = req.getPathInfo();
		
		if(subPath == null || subPath.equals("")) {
			System.out.println("요청 subPath 없음!");
		} else if(subPath.equals("/search")) {
			
			// 식품검색 화면 보여주기
			ReqController.forward(req, resp, "search");
			
		} else if(subPath.equals("/insert")) {
			
			ReqController.forward(req, resp, "insert");
		
			String mf_code = req.getParameter("fd_code");
			
			FoodDTO foodDTO = fdService.findById(mf_code);
			req.setAttribute("FOOD", foodDTO);
			
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			String today = sd.format(date);
			
			req.setAttribute("TODAY", today);
			
			ReqController.forward(req, resp, "insert");
		
		} else if(subPath.equals("/insert")) {
			
			String strFcode = req.getParameter("mf_code");
			String strDate = req.getParameter("mf_date");
			String strAmt = req.getParameter("mf_amt");
			
			MyFoodVO myFoodVO = new MyFoodVO();
			myFoodVO.setMf_fcode(strFcode);
			myFoodVO.setMf_date(strDate);
			myFoodVO.setMf_amt(Float.valueOf(strAmt));
			
			int result = mfService.insert(myFoodVO);
			if(result > 0) {
				System.out.println("추가 성공");
				resp.sendRedirect("/diet/");
			} else {
				System.out.println("추가 실패");
			}
			
		}
		
	}

	// form에서 input Box에 입력한 데이터를 전송했을 때 method를 POST로 지정하면 처리하는 함수 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String subPath = req.getPathInfo();
		
		if(subPath == null || subPath.equals("")) {
			
			System.out.println("요청 subPath가 없음");
			
		} else if(subPath.equals("/search")) {
			
			// form에 입력된 데이터를 추출(Parameter를 Get)하고 
			String f_name = req.getParameter("f_name");
			// DB에서 조회하여 다시 Web에 보여주기
			List<FoodDTO> foodList = fdService.findByFName(f_name);
			
			req.setAttribute("FOODS", foodList);
			
			ReqController.forward(req, resp, "search");
			
		} else if(subPath.equals("/insert")) {
			String strFcode = req.getParameter("mf_code");
			String strDate= req.getParameter("mf_date");
			String strAmt = req.getParameter("mf_amt");

			MyFoodVO myFoodVO = new MyFoodVO();
			myFoodVO.setMf_fcode(strFcode);
			myFoodVO.setMf_date(strDate);
			myFoodVO.setMf_amt(Float.valueOf(strAmt));

			int result = mfService.insert(myFoodVO);
			if(result > 0) {
				System.out.println("입력 성공");
				resp.sendRedirect("/diet/");
			} else {
				System.out.println("입력 실패");
			}
		}
		
	}

	
	
}
