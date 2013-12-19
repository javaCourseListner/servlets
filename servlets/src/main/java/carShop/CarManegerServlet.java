package carShop;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import carShop.DAO.CarDao;
import carShop.DAO.UserOrderDao;
import carShop.entities.Car;
import carShop.entities.User;
import carShop.entities.UserOrder;

public class CarManegerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CarDao carDao =  new CarDao();
	private UserOrderDao userOrderDao =  new UserOrderDao();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{		
		List<Car> listItem = carDao.getCars();
		req.getServletContext().setAttribute("cars",listItem);
		req.getRequestDispatcher("carListPage.jsp").forward(req, resp);;
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {		
	
		String carToLook = req.getParameter("carToLook");
		String carToBuy = req.getParameter("carToBuy");
			
		if(carToLook !=null){	
			int id = Integer.parseInt(carToLook);		
			Car car = carDao.getCarById(id);
			req.getServletContext().setAttribute("car",car);
			System.out.println("nn");
			req.getRequestDispatcher("carUserPage.jsp").forward(req, resp);	
		}else if (carToBuy != null){	
			orderRegistration(req, carToBuy);			
			req.getRequestDispatcher("clientPersonalPage.jsp").forward(req, resp);
		}		
	}	
	
	private void orderRegistration(HttpServletRequest req, String carToBuy) {
		HttpSession session =  req.getSession();					
		int id = Integer.parseInt(carToBuy);		
		Car car = carDao.getCarById(id);							//	Method gets car by id from base 			
																	//	and user from session, than create
		User user = (User) session.getAttribute("user");			//	new userOrder and sets it to base.																
		UserOrder userOrder = new UserOrder(car, user, new Date());	
		userOrderDao.setUserOrder(userOrder);	
		
		List<UserOrder> ls = user.getUserOrder();
		ls.add(userOrder);
		
		@SuppressWarnings("unchecked")								
		List<Car> bucket = (List<Car>)session.getAttribute("bucket");
		bucket.add(car);
	}
		
}
