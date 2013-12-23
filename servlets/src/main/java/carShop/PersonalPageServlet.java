package carShop;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import carShop.DAO.UserOrderDao;
import carShop.entities.User;
import carShop.entities.UserOrder;

public class PersonalPageServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private static UserOrderDao userOrderDao = new UserOrderDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		HttpSession session =req.getSession();
		User user = (User) session.getAttribute("user");
		List<UserOrder> orderList = user.getUserOrder();
		req.setAttribute("orderList", orderList);
		req.getRequestDispatcher("jsp/user/personalPage.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doPost (HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {		
		String showSum = req.getParameter("showOrdersSum");
		String showMonthSum = req.getParameter("showMonthSum");
		if(showSum != null){
			HttpSession session =req.getSession();
			User user = (User) session.getAttribute("user");
			String login = user.getLogin();						
			Long amount = userOrderDao.getOrdersAmount(login);
			req.setAttribute("sum", amount);	
			List<UserOrder> orderList = user.getUserOrder();
			req.setAttribute("orderList", orderList);
			req.getRequestDispatcher("jsp/user/personalPage.jsp").forward(req, resp);
		}else if(showMonthSum !=null){
			HttpSession session =req.getSession();
			User user = (User) session.getAttribute("user");
			String login = user.getLogin();
			List<UserOrder> orderList = user.getUserOrder();
			req.setAttribute("orderList", orderList);
			Map<String, Long> amount = userOrderDao.getSumGroupByMounth(login);
			req.setAttribute("monthSum", amount);	
			req.getRequestDispatcher("jsp/user/personalPage.jsp").forward(req, resp);
		}else{		
			HttpSession session =req.getSession();
			User user = (User) session.getAttribute("user");
			List<UserOrder> orderList = user.getUserOrder();			
			req.setAttribute("orderList", orderList);
			req.getRequestDispatcher("jsp/user/personalPage.jsp").forward(req, resp);			
		}
	}
	

/*	
	@SuppressWarnings("unchecked")
	private long countAmount(HttpServletRequest req){				//fast as, but without JPQL)
		long result = 0;										 	 
		HttpSession session =req.getSession();
		User user = (User) session.getAttribute("user");
		List<UserOrder> orderList = user.getUserOrder();	
		for(UserOrder userOrder:orderList){
			result=userOrder.getCar().getPrice();
		}
		return result;	
	}
*/	
		
}