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
