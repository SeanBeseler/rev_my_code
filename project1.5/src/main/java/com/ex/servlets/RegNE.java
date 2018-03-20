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

import project1.com.objects.User;

@WebServlet("/RegNE")
public class RegNE extends HttpServlet{
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
			String json = br.readLine();
			FrontEndLogic FEL = new FrontEndLogic();
			///System.out.println(json);
			
			ObjectMapper mapper = new ObjectMapper();
			String[]  in = mapper.readValue(json, String[].class);
			System.out.println(in);
			User u = new User(in[2], in[0], in[1], in[3], Integer.parseInt(in[4]), Integer.parseInt(in[5]), Integer.parseInt(in[6]), in[7]);
			System.out.println(u.toString());
			u = FEL.addUser(u);
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
