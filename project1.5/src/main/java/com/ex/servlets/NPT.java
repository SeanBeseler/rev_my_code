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

import com.ex.frontEndLogic.FrontEndLogic;
import com.fasterxml.jackson.databind.ObjectMapper;

import project1.com.objects.NA;
import project1.com.objects.PendingTransactions;
import project1.com.objects.User;
@WebServlet("/NPT")
public class NPT extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String json = br.readLine();
		FrontEndLogic FEL = new FrontEndLogic();
		///System.out.println(json);
		
		ObjectMapper mapper = new ObjectMapper();
		String[]  in = mapper.readValue(json, String[].class);
		System.out.println(in);
		User u = FEL.getUser(in[0]);
		User uu =FEL.getUserID(u.getReportsto());
		double cost = FEL.rightCost(u, Integer.parseInt(in[3]), Double.parseDouble(in[1]));
		PendingTransactions PT = new PendingTransactions(cost, in[2], Integer.parseInt(in[3]), in[4], in[5], in[6], in[7]);
		NA na = new NA(cost, in[2], Integer.parseInt(in[3]), in[4], in[5], in[6], in[7]);
		PT = FEL.addPT(u, PT);
		System.out.println(uu.toString());
		na = FEL.addNA(uu, na);
		//System.out.println(u.toString());
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		if(u != null) {
			String uJSON = mapper.writeValueAsString(u);
			out.write(uJSON);
		}
		else {
			out.write("null");
		}
		
	}

}
