package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Mydao;
import dto.FoodItem;
@WebServlet("/viewmenu")
public class AdminViewMenu extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Logic to fetch Data from Database
		Mydao dao = new Mydao();
		List<FoodItem> items = dao.fetchAllFoodItem();
		
		if(items.isEmpty()) {
			resp.getWriter().print("<h1 style='color:red'>No Items Found</h1>");
			req.getRequestDispatcher("AdminHome.html").include(req, resp);
		}
		else {
		req.setAttribute("list", items);
		req.getRequestDispatcher("ViewMenu.jsp").include(req, resp);
		}

	}
}
