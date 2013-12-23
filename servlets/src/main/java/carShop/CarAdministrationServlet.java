package carShop;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import carShop.DAO.CarDao;
import carShop.entities.Car;

public class CarAdministrationServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private CarDao carDao = new CarDao();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("jsp/admin/carPanel.jsp").forward(req, resp);;	
	}
	
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String parm = null;
		
		if((parm = req.getParameter("targetCarModel"))!= null){			
			List<Car> cars = carDao.getCarsByModel(parm);
			if(cars.size() != 0){
				req.setAttribute("cars", cars);
				req.getRequestDispatcher("jsp/admin/carList.jsp").forward(req, resp);
			}else{
				req.getRequestDispatcher("jsp/admin/carPanel.jsp").forward(req, resp);
			}			
		}else if((parm = req.getParameter("allCars"))!= null){			
			List<Car> cars = carDao.getCars();			
			req.setAttribute("cars", cars);
			req.getRequestDispatcher("jsp/admin/carList.jsp").forward(req, resp);						
		}else if((parm = req.getParameter("targetCar"))!= null){
			int id = Integer.parseInt(parm);		
			Car car = carDao.getCarById(id);
			req.setAttribute("car",car);
			req.getRequestDispatcher("jsp/admin/car.jsp").forward(req, resp);
		}else if((parm = req.getParameter("countCarOrders"))!= null){
			int id = Integer.parseInt(parm);		
			Car car = carDao.getCarById(id);
			req.setAttribute("car",car);
			long count = carDao.getCountOrdersOnCar(id);			
			req.setAttribute("countCarOrders", count);
			req.getRequestDispatcher("jsp/admin/car.jsp").forward(req, resp);
		}else if((parm = req.getParameter("newPrice"))!= null){
			int carId = Integer.parseInt(req.getParameter("car"));			
			if(!priceValidate(parm)){
				Car car = carDao.getCarById(carId);
				req.setAttribute("car",car);
				req.getRequestDispatcher("jsp/admin/car.jsp").forward(req, resp);
				return;
			}
			int newPrice = Integer.parseInt(parm);					
			carDao.setNewPrice(carId, newPrice);
			Car car = carDao.getCarById(carId);
			req.setAttribute("car",car);
			req.getRequestDispatcher("jsp/admin/car.jsp").forward(req, resp);
		}else if((parm = req.getParameter("newDescription"))!= null){
			int carId = Integer.parseInt(req.getParameter("car"));								
			carDao.setNewDescription(carId, parm);
			Car car = carDao.getCarById(carId);
			req.setAttribute("car",car);
			req.getRequestDispatcher("jsp/admin/car.jsp").forward(req, resp);
		}else if((parm = req.getParameter("deleteCar"))!= null){			
			int carId = Integer.parseInt(parm);											
			long count = carDao.getCountOrdersOnCar(carId);
			if(count==0){
				carDao.deleteCarById(carId);
				List<Car> cars = carDao.getCars();			
				req.setAttribute("cars", cars);
				req.getRequestDispatcher("jsp/admin/carList.jsp").forward(req, resp);	
			}else{
				Car car = carDao.getCarById(carId);
				req.setAttribute("car",car);
				req.getRequestDispatcher("jsp/admin/car.jsp").forward(req, resp);
			}
		}else if((parm = req.getParameter("unordered"))!= null){			
			List<Car> cars = carDao.getUnorderedCars();			
			req.setAttribute("cars", cars);
			req.getRequestDispatcher("jsp/admin/carList.jsp").forward(req, resp);	
		}		
	}


	private boolean priceValidate(String price) {
		if(price == null)return false;
		Pattern p = Pattern.compile("[0-9]*"); 
        Matcher m = p.matcher(price); 
        return m.matches();
	}
	

	
	
	
//	private void orderRegistration(HttpServletRequest req){			 																				
//	User user = (User) req.getSession().getAttribute("user");
//	EntitiesManeger  entitiesManeger  = (EntitiesManeger) getServletContext().getAttribute("entitiesManeger");
//	String model = req.getParameter("model");									
//	if((model != null)&&(!model.equals(""))){									// According the logic order is valid if
//		String color = req.getParameter("color");								// field "model" is not null.					
//		Options opt = getOptionsFromRequest(req);;									
//		Car car = new Car(model,color,opt,user);	
//		entitiesManeger.setCar(car);
//		user.car.add(car);	
//	}
//}

//private Options getOptionsFromRequest (HttpServletRequest req){	
//	Options opt = new Options();
//	if (req.getParameter("conditioner") != null)opt.setConditioner(true);
//	if (req.getParameter("hydroamplifier")!=null)opt.setHydroamplifier(true);
//	if (req.getParameter("automaticTransmission")!=null)opt.setAutomaticTransmission(true);
//	return opt;
//}
	
	
	
	
	
}
