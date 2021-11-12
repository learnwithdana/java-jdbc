package com.hca.nwind.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hca.nwind.datalayer.NwindDataManager;
import com.hca.nwind.models.Category;
import com.hca.nwind.models.Product;

/**
 * Servlet implementation class ProductsByCategory
 */
@WebServlet("/productsbycategory")
public class ProductsByCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductsByCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// set the content type of the response going back to the browser
		response.setContentType("text/html");
		
		// get our categories
		NwindDataManager dataManager = new NwindDataManager();
		List<Category> list = dataManager.getAllCategories();
		
		// save the categories in the request so that we can send them to the JSP for display purposes
		request.setAttribute("categories", list);
		
		// forward the request to the JSP so that it can produce the HTML markup
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/productsbycategory.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
