package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Mydao;
import dto.FoodItem;
@WebServlet("/delete")
public class DeleteItem extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//Getting Id from the URL
			int id=Integer.parseInt(req.getParameter("id"));
			Mydao dao=new Mydao();
			//Finding object because remove method accepts object
			FoodItem item=dao.find(id);
			dao.delete(item);
			resp.getWriter().print("<h1 style='color:green'>Data Deleted Successfully</h1>");
			req.getRequestDispatcher("viewmenu").include(req, resp);
}
}
