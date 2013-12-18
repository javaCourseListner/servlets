package carShop;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import carShop.DAO.CarDao;
import carShop.entities.Car;

public class CarManegerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CarDao carDao =  new CarDao();
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{		
		List<Car> listItem = carDao.getCars();
//		if(listItem != null){
//			for(Car i:listItem){
//				i.toString());
//			}
//		}

		req.getServletContext().setAttribute("cars",listItem);
		req.getRequestDispatcher("carListPage.jsp").forward(req, resp);;
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {		
		int id = Integer.parseInt(req.getParameter("car"));		
		Car car = carDao.getCarById(id);
		req.getServletContext().setAttribute("car",car);
		req.getRequestDispatcher("carUserPage.jsp").forward(req, resp);;	
	}
	
	
	
	
	
}
