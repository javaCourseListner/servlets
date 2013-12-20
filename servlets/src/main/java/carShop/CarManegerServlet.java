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
		req.getRequestDispatcher("jsp/user/carList.jsp").forward(req, resp);
	}
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {		
		String parm = null;			
		if((parm=req.getParameter("carToLook"))!=null){	
			int id = Integer.parseInt(parm);		
			Car car = carDao.getCarById(id);
			req.setAttribute("car",car);
			req.getRequestDispatcher("jsp/user/car.jsp").forward(req, resp);	
		}else if((parm=req.getParameter("carToBuy"))!=null){	
			orderRegistration(req, parm);			
			resp.sendRedirect("personalPage");
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
		List<UserOrder> bucket = (List<UserOrder>)session.getAttribute("bucket");		
		userOrder.setUser(null);                         // We use "bucket" as container for cars and date,
		bucket.add(userOrder);		                     // that's why we don't need to keep reference on User
	}								   
		
}
