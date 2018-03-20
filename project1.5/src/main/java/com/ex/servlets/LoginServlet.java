package com.ex.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ex.frontEndLogic.FrontEndLogic;
import com.fasterxml.jackson.databind.ObjectMapper;

import project1.com.objects.User;

@WebServlet("/login")
public class LoginServlet  extends HttpServlet{
FrontEndLogic FEL = new FrontEndLogic();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		System.out.println("In login");
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String json = br.readLine();
		
		System.out.println(json);

		ObjectMapper mapper = new ObjectMapper();
		
		
		String[] userInfo = mapper.readValue(json, String[].class);
		String username = userInfo[0];
		String password = userInfo[1];
		
		 User user = FEL.login(username, password);
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		
		if(user!= null) {
		String userJSON = mapper.writeValueAsString(user);
		
		HttpSession session = req.getSession();
		session.setAttribute("user", user);
		
		out.write(userJSON);
		
		}
		else {
			out.write("null"); 
		}
		
		
	}

}
