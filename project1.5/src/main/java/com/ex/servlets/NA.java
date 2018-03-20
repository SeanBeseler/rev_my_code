package com.ex.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ex.frontEndLogic.FrontEndLogic;
import com.fasterxml.jackson.databind.ObjectMapper;

import project1.com.objects.User;

@WebServlet("/NA")
public class NA extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String json = br.readLine();
		ObjectMapper mapper = new ObjectMapper();
		String[] userInfo = mapper.readValue(json, String[].class);
		String em = userInfo[0];
		int RT = Integer.parseInt(userInfo[1]);
		FrontEndLogic FEL = new FrontEndLogic();
		User u1 = FEL.getUserID(RT);
		User u2 = FEL.getUser(em);
		FEL.upDateBoss(u1, u2);
	}

}
