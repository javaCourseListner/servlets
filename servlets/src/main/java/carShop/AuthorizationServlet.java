package carShop;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import carShop.DAO.UserDao;
import carShop.DAO.UserOrderDao;
import carShop.entities.Car;
import carShop.entities.User;



public class AuthorizationServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static UserDao userDao = new UserDao();
	private static UserOrderDao userOrderDao = new UserOrderDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		if(req.getSession(false) != null){
			HttpSession session =req.getSession();
			User user = (User) session.getAttribute("user");
			if(user.isAdminRights()){
				req.getRequestDispatcher("jsp/admin/generalPanel.jsp").forward(req, resp);	
			}else{
				req.getRequestDispatcher("jsp/user/welcomePage.jsp").forward(req, resp);}
		}else{
			req.getRequestDispatcher("guestWelcomePage.html").forward(req, resp);
		}
	}	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {		
		clientAuthorisation(req, resp);					
	}

	private void clientAuthorisation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		
		String login = req.getParameter("login");		
		String password = req.getParameter("password");											
		if(!isAuthInputValid(login)){			
			resp.sendRedirect("errorPage.html");
			return;
		}else if(!isAuthInputValid(password)){			
			resp.sendRedirect("errorPage.html");
			return;
		}
		
		String passwordHash = getHash(password);
		User user = userDao.getUserById(login);	
		
		if((user != null)&&(!passwordHash.equals(user.getPassword()))){						
			resp.sendRedirect("errorPage.html");
		}else if(isValidSuperUser(passwordHash, user)){
			 HttpSession session = req.getSession(true);
			 session.setAttribute("user",user);
			 req.getRequestDispatcher("jsp/admin/generalPanel.jsp").forward(req, resp);;			
		}else if(isValidUser(passwordHash, user)){				
			 HttpSession session = req.getSession(true);
			 session.setAttribute("user",user);			
			 List<Car> ls = userOrderDao.getUserOrderCars(login);
			 session.setAttribute("bucket",ls);
			 req.getRequestDispatcher("jsp/user/welcomePage.jsp").forward(req, resp);
		}else if(user == null){
			User newUser = new User();
			newUser.setLogin(login);
			newUser.setPassword(passwordHash);			
			userDao.setUser(newUser);
			HttpSession session = req.getSession(true);			
			session.setAttribute("user", newUser);
			session.setAttribute("bucket", new LinkedList<Car>());
			req.getRequestDispatcher("jsp/user/welcomePage.jsp").forward(req, resp);
		}
	}

	
	
	
	
	
	
	private boolean isAuthInputValid(String authInpt){
		if(authInpt == null)return false;
		Pattern p = Pattern.compile("[a-zA-Z0-9]{4,12}"); 
        Matcher m = p.matcher(authInpt); 
        return m.matches(); 
	}
	
	private boolean isValidUser(String passwordHash, User user) {
		return (user != null)&&(passwordHash.equals(user.getPassword()));
	}
				
	private boolean isValidSuperUser(String passwordHash, User user) {
		return ((user != null)&&
				(user.isAdminRights()== true)&&
				(passwordHash.equals(user.getPassword())));
	}
	
	private String getHash(String str) {	        
		if (str == null) return null;
		MessageDigest md5 ;        
	    StringBuffer  hexString = new StringBuffer();	        
	    try {	                                    
	    	md5 = MessageDigest.getInstance("md5");	            
	    	md5.reset();
	    	md5.update(str.getBytes()); 	                        	                        
	    	byte messageDigest[] = md5.digest();	                        
	    	for (int i = 0; i < messageDigest.length; i++) {
	    		hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
	    	}	                                                                                        
	    }catch (NoSuchAlgorithmException e) {                        
	    	e.printStackTrace();
	    }	        
	    return hexString.toString();
	}
	

}