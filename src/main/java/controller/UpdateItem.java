package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.Mydao;
import dto.FoodItem;
@WebServlet("/update")
public class UpdateItem extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		double price = Double.parseDouble(req.getParameter("price"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		String type = req.getParameter("type");
		Part part = req.getPart("pic");
		byte[] picture=null;
		Mydao dao=new Mydao();
		FoodItem item1=dao.find(id);
		if(part==null)
		{
			picture=item1.getPicture();
		}
		else {
		picture = new byte[part.getInputStream().available()];
		part.getInputStream().read(picture);
		}
		
		FoodItem item = new FoodItem();
		item.setId(id);
		item.setName(name);
		item.setPicture(picture);
		item.setPrice(price);
		item.setQuantity(quantity);
		item.setType(type);
		
		dao.update(item);
		resp.getWriter().print("<h1 style='color:green'>Data Updated Successfully</h1>");
		req.getRequestDispatcher("viewmenu").include(req, resp);
	}

}
