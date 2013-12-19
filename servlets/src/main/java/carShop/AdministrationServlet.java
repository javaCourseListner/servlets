package carShop;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import carShop.DAO.UserDao;
import carShop.entities.User;

public class AdministrationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserDao userDao = new UserDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("adminPage.jsp").forward(req, resp);;	
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String parm = null;
		
		if((parm = req.getParameter("targetUser"))!= null){			
			User user = userDao.getUserById(parm);
			if(user != null){
				req.setAttribute("targetUser", user);
				req.getRequestDispatcher("userAdminPage.jsp").forward(req, resp);
			}else{
				req.getRequestDispatcher("adminPage.jsp").forward(req, resp);
			}			
		}else if((parm = req.getParameter("targetUserList"))!= null){			
			List<User> users = userDao.getUsers();
			req.setAttribute("targetUserList", users);
			req.getRequestDispatcher("userListAdminPage.jsp").forward(req, resp);		
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
