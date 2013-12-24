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
import carShop.entities.Options;

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
			findByModel(req, resp, parm);			
		}else if((parm = req.getParameter("allCars"))!= null){			
			allCarsList(req, resp);						
		}else if((parm = req.getParameter("targetCar"))!= null){
			carPage(req, resp, parm);
		}else if((parm = req.getParameter("countCarOrders"))!= null){
			countCarOrders(req, resp, parm);
		}else if((parm = req.getParameter("updatePrice"))!= null){
			updatePrice(req, resp, parm);
		}else if((parm = req.getParameter("updateDescription"))!= null){
			updateDescription(req, resp, parm);
		}else if((parm = req.getParameter("deleteCar"))!= null){			
			deleteCar(req, resp, parm);
		}else if((parm = req.getParameter("unordered"))!= null){			
			unorderedCarList(req, resp);	
		}else if((parm = req.getParameter("createCar"))!= null){			
			carCreationPage(req, resp);	
		}else if((parm = req.getParameter("newModel"))!= null){			
			orderRegistration(req, resp, parm);				
		}	
	}


	private void carCreationPage(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("jsp/admin/carCreation.jsp").forward(req, resp);
	}


	private void carPage(HttpServletRequest req, HttpServletResponse resp,
			String parm) throws ServletException, IOException {
		int id = Integer.parseInt(parm);		
		Car car = carDao.getCarById(id);
		req.setAttribute("car",car);
		req.getRequestDispatcher("jsp/admin/car.jsp").forward(req, resp);
	}


	private void countCarOrders(HttpServletRequest req,
			HttpServletResponse resp, String parm) throws ServletException, IOException {
		int id = Integer.parseInt(parm);		
		Car car = carDao.getCarById(id);
		req.setAttribute("car",car);
		long count = carDao.getCountOrdersOnCar(id);			
		req.setAttribute("countCarOrders", count);
		req.getRequestDispatcher("jsp/admin/car.jsp").forward(req, resp);
	}


	private void updatePrice(HttpServletRequest req, HttpServletResponse resp,
			String parm) throws ServletException, IOException {
		int carId = Integer.parseInt(req.getParameter("car"));			
		if(!priceValidate(parm)){
			Car car = carDao.getCarById(carId);
			req.setAttribute("car",car);
			req.getRequestDispatcher("jsp/admin/car.jsp").forward(req, resp);
		}else{
			int newPrice = Integer.parseInt(parm);					
			carDao.setNewPrice(carId, newPrice);
			Car car = carDao.getCarById(carId);
			req.setAttribute("car",car);
			req.getRequestDispatcher("jsp/admin/car.jsp").forward(req, resp);
		}
	}


	private void updateDescription(HttpServletRequest req,
			HttpServletResponse resp, String parm) throws ServletException,IOException {
		int carId = Integer.parseInt(req.getParameter("car"));								
		carDao.setNewDescription(carId, parm);
		Car car = carDao.getCarById(carId);
		req.setAttribute("car",car);
		req.getRequestDispatcher("jsp/admin/car.jsp").forward(req, resp);
	}


	private void deleteCar(HttpServletRequest req, HttpServletResponse resp,
			String parm) throws ServletException, IOException {
		int carId = Integer.parseInt(parm);											
		long count = carDao.getCountOrdersOnCar(carId);
		if(count==0){
			carDao.deleteCarById(carId);
			allCarsList(req, resp);	
		}else{
			Car car = carDao.getCarById(carId);
			req.setAttribute("car",car);
			req.getRequestDispatcher("jsp/admin/car.jsp").forward(req, resp);
		}
	}


	private void unorderedCarList(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		List<Car> cars = carDao.getUnorderedCars();			
		req.setAttribute("cars", cars);
		req.getRequestDispatcher("jsp/admin/carList.jsp").forward(req, resp);
	}


	private void allCarsList(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Car> cars = carDao.getCars();			
		req.setAttribute("cars", cars);
		req.getRequestDispatcher("jsp/admin/carList.jsp").forward(req, resp);
	}


	private void findByModel(HttpServletRequest req, HttpServletResponse resp,
			String parm) throws ServletException, IOException {
		List<Car> cars = carDao.getCarsByModel(parm);
		if(cars.size() != 0){
			req.setAttribute("cars", cars);
			req.getRequestDispatcher("jsp/admin/carList.jsp").forward(req, resp);
		}else{
			req.getRequestDispatcher("jsp/admin/carPanel.jsp").forward(req, resp);
		}
	}


	private boolean priceValidate(String price) {
		if(price == null)return false;
		Pattern p = Pattern.compile("[0-9]*"); 
	        Matcher m = p.matcher(price); 
	        return m.matches();
	}
		
	private void orderRegistration(HttpServletRequest req, HttpServletResponse resp, String model) 
				throws ServletException, IOException{			 																														
		String strPrice = req.getParameter("price");
		if((model != null)||(!priceValidate(strPrice))){											
			String color = req.getParameter("color");													
			Options opt = getOptionsFromRequest(req);;									
			String desc = req.getParameter("description");	
			int price = Integer.parseInt(strPrice);	
			Car car = new Car(model,color,opt, price, desc);	
			carDao.setCar(car);	
			req.setAttribute("car", car);
			req.getRequestDispatcher("jsp/admin/car.jsp").forward(req, resp);
		}else{
			carCreationPage(req, resp);	
		}
	}
	
	private Options getOptionsFromRequest (HttpServletRequest req){	
		Options opt = new Options();
		if (req.getParameter("conditioner") != null)opt.setConditioner(true);
		if (req.getParameter("hydroamplifier")!=null)opt.setHydroamplifier(true);
		if (req.getParameter("automaticTransmission")!=null)opt.setAutomaticTransmission(true);
		return opt;
	}	
}
