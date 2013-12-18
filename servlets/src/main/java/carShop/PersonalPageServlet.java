package carShop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import carShop.entities.Car;
import carShop.entities.User;
import carShop.entities.EntitiesManeger;
import carShop.entities.Options;


public class PersonalPageServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
//	@Override
//	protected void doPost (HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {		
//		orderRegistration(req);
//		req.getRequestDispatcher("clientPersonalPage.jsp").forward(req, resp);		
//	}
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		req.getRequestDispatcher("clientPersonalPage.jsp").forward(req, resp);
	}
			
	
	
	
	
	
	
	
	
	
	
	
	
	
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
				
	@Override
	public void init() throws ServletException {									
		EntitiesManeger  entitiesManeger  = (EntitiesManeger)getServletContext().getAttribute("entitiesManeger");		
		if (entitiesManeger == null){
			entitiesManeger = new EntitiesManeger ();
			getServletContext().setAttribute("entitiesManeger", entitiesManeger );
	 	}
	}
	
}