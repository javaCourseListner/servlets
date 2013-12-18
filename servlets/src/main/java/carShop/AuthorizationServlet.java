package carShop;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import carShop.entities.User;
import carShop.entities.EntitiesManeger;


public class AuthorizationServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		if(req.getSession(false) != null){
			req.getRequestDispatcher("clientWelcomePage.jsp").forward(req, resp);				
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
		String password = getHash(req.getParameter("password"));
		
		EntitiesManeger entitiesManeger = (EntitiesManeger) getServletContext().getAttribute("entitiesManeger");			
		User user = entitiesManeger.getUserById(login);
		
		if((login == null)||(password == null)){			
			req.getRequestDispatcher("errorPage.html").forward(req, resp);
		}else if((user != null)&&(!password.equals(user.getPassword()))){			
			req.getRequestDispatcher("errorPage.html").forward(req, resp);
		}else if((user != null)&&(password.equals(user.getPassword()))){				
			HttpSession session = req.getSession(true);
			session.setAttribute("user",user);
			req.getRequestDispatcher("clientWelcomePage.jsp").forward(req, resp);
		}else if(user == null){
			User newUser = new User();
			newUser.setLogin(login);
			newUser.setPassword(password);			
			entitiesManeger .setUser(newUser);
			HttpSession session = req.getSession(true);
			session.setAttribute("user", newUser);
			req.getRequestDispatcher("clientWelcomePage.jsp").forward(req, resp);
		}
	}
				
	public String getHash(String str) {	        
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