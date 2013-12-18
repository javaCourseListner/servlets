package carShop;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import carShop.entities.User;

public class AdministrationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user =	(User) session.getAttribute("user");
		if((user != null)&&(user.getLogin().equals("admin"))){
		req.getRequestDispatcher("adminPage.jsp").forward(req, resp);;	
		}else{
			resp.sendRedirect("welcomePage");			
		}
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Map m =req.getParameterMap();
		for(Object str: m.keySet()){			
			System.out.println(str);
		}
		for(Object str: m.values()){			
			for(Object s: (String[])str)System.out.println(s);
		}
		
	}
	
	
	
	
	
	
	
}
