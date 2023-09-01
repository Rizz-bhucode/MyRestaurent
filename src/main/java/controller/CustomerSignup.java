package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import dao.Mydao;
import dto.Customer;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/Signup")

@MultipartConfig
public class CustomerSignup extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String fullName = req.getParameter("fullname");
		String password = req.getParameter("password");
		long mobile = Long.parseLong(req.getParameter("mobile"));
		String email = req.getParameter("email");
		String gender = req.getParameter("gender");
		String country = req.getParameter("country");
		LocalDate dob = LocalDate.parse(req.getParameter("dob"));
		int age = Period.between(dob, LocalDate.now()).getYears();

		// Logic to Receive image and convert to byte[]
		Part pic = req.getPart("picture");
		byte[] picture = null;
		picture = new byte[pic.getInputStream().available()];
		pic.getInputStream().read(picture);

		Mydao m1 = new Mydao();
		if (m1.fetchByEmail(email) == null && m1.fetchByMobile(mobile) == null) {

			Customer c = new Customer();

			c.setCountry(country);
			c.setDob(dob);
			c.setEmail(email);
			c.setFullName(fullName);
			c.setGender(gender);
			c.setMobile(mobile);
			c.setPassword(password);
			c.setPicture(picture);

			m1.save(c);

			resp.getWriter().print("<h1 style='color:green'>Account created</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		} else {
			resp.getWriter().print("<h1 style='color:red'>Email and phone number should be unique</h1>");
			req.getRequestDispatcher("signup.html").include(req, resp);
		}
	}

}
