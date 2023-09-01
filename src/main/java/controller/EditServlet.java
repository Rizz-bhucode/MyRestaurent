package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Mydao;
import dto.FoodItem;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id"));
		Mydao dao = new Mydao();
		FoodItem item = dao.find(id);

		req.setAttribute("item", item);
		req.getRequestDispatcher("Edit.jsp").forward(req, resp);

	}

}
