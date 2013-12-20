package carShop;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import carShop.DAO.UserOrderDao;
import carShop.entities.User;

public class PersonalPageServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private static UserOrderDao userOrderDao = new UserOrderDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
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
		}else if(showMonthSum !=null){
			HttpSession session =req.getSession();
			User user = (User) session.getAttribute("user");
			String login = user.getLogin();
			Map<String, Long> amount = userOrderDao.getSumGroupByMounth(login);
			req.setAttribute("monthSum", amount);	
		}	
		req.getRequestDispatcher("jsp/user/personalPage.jsp").forward(req, resp);		
	}
	
	

			
	
	
	
/*	
	@SuppressWarnings("unchecked")
	private long countAmount(HttpServletRequest req){				//fast as Ali, but without JPQL)
		long result = 0;										 	 
		HttpSession session =req.getSession();
		List<Car> cars = (List<Car>) session.getAttribute("bucket");	
		for(Car car:cars){
			result=car.getPrice();
		}
		return result;	
	}
*/	
	
	
	
	
	
	
//	private void orderRegistration(HttpServletRequest req){			 																				
//		User user = (User) req.getSession().getAttribute("user");
//		EntitiesManeger  entitiesManeger  = (EntitiesManeger) getServletContext().getAttribute("entitiesManeger");
//		String model = req.getParameter("model");									
//		if((model != null)&&(!model.equals(""))){									// According the logic order is valid if
//			String color = req.getParameter("color");								// field "model" is not null.					
//			Options opt = getOptionsFromRequest(req);;									
//			Car car = new Car(model,color,opt,user);	
//			entitiesManeger.setCar(car);
//			user.car.add(car);	
//		}
//	}
	
//	private Options getOptionsFromRequest (HttpServletRequest req){	
//		Options opt = new Options();
//		if (req.getParameter("conditioner") != null)opt.setConditioner(true);
//		if (req.getParameter("hydroamplifier")!=null)opt.setHydroamplifier(true);
//		if (req.getParameter("automaticTransmission")!=null)opt.setAutomaticTransmission(true);
//		return opt;
//	}
				

	
}