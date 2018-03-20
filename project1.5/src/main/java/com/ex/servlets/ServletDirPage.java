package com.ex.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.view")
public class ServletDirPage extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = process(req, resp);
		req.getRequestDispatcher(page).forward(req, resp);
	}
	
	static String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getRequestURI()) {
		
		case("/project1.5/loadLanding.view"):
			return "parshals/login.html";
		case("/project1.5/loadnav.view"):
			return "parshals/navbar.html";
		case("/project1.5/loadhome.view"):
			return"parshals/home.html";
		case("/project1.5/RNE.view"):
			return"parshals/NewEmp.html";
		case("/project1.5/RF.view"):
			return"parshals/ReberForm.html";
		case("/project1.5/PR.view"):
			return"parshals/PR.html";
		case("/project1.5/NA.view"):
			return"parshals/NA.html";
		default:
			return "index.html";
		}
		//return req.getRequestURI();
	}

}
