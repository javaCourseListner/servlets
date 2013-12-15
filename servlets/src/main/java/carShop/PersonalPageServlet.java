package carShop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import carShop.entities.Car;
import carShop.entities.Client;
import carShop.entities.EntitiesManeger;


public class PersonalPageServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doPost (HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {		
		orderRegistration(req);
		req.getRequestDispatcher("clientPersonalPage.jsp").forward(req, resp);		
	}
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		req.getRequestDispatcher("clientPersonalPage.jsp").forward(req, resp);
	}
			
	private void orderRegistration(HttpServletRequest req){			 																				
		Client client = (Client) req.getSession().getAttribute("client");
		EntitiesManeger  entitiesManeger  = (EntitiesManeger) getServletContext().getAttribute("entitiesManeger ");
		String model = req.getParameter("model");									
		if((model != null)&&(!model.equals(""))){									// According the logic order is valid if
			String color = req.getParameter("color");								// field "model" is not null.		
			String[] options = req.getParameterValues("options");								
			
			Car car = new Car(model,color,options,client);	
			entitiesManeger .setCar(car);
			client.car.add(car);	
		}
	}
		
		
	@Override
	public void init() throws ServletException {									
		EntitiesManeger  entitiesManeger  = (EntitiesManeger ) getServletContext().getAttribute("entitiesManeger ");		
		if (entitiesManeger  == null){
			entitiesManeger  = new EntitiesManeger ();
			getServletContext().setAttribute("entitiesManeger ", entitiesManeger );
	 	}
	}
	
}