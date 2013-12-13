package carShop;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import carShop.entities.Client;
import carShop.entities.ClientBase;


public class AuthorizationServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		if(req.getSession(false) != null){
			req.getRequestDispatcher("clientWelcomePage.jsp").forward(req, resp);				
		}else{
			req.getRequestDispatcher("guestWelcomePage.jsp").forward(req, resp);	
		}
	}	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {		
		clientAuthorisation(req, resp);					
	}

	private void clientAuthorisation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		String login = req.getParameter("login");
		String password = getHash(req.getParameter("password"));
		
		ClientBase clientBase = (ClientBase) getServletContext().getAttribute("clientBase");	
		Map<String, Client> clientTable = clientBase.getClientTable();		
		Client client = clientTable.get(login);
		
		if(login == null){
			req.setAttribute("errorMessage" , "input login");
			req.getRequestDispatcher("errorPage.jsp").forward(req, resp);
		}else if(password == null){
			req.setAttribute("errorMessage" , "input password");
			req.getRequestDispatcher("errorPage.jsp").forward(req, resp);
		}else if((client != null)&&(!password.equals(client.getPassword()))){
			req.setAttribute("errorMessage" , "invalid password");
			req.getRequestDispatcher("errorPage.jsp").forward(req, resp);
		}else if((client != null)&&(password.equals(client.getPassword()))){				
			HttpSession session = req.getSession(true);
			session.setAttribute("client",client);
			req.getRequestDispatcher("clientWelcomePage.jsp").forward(req, resp);
		}else if(client == null){
			Client newClient = new Client();
			newClient.setLogin(login);
			newClient.setPassword(password);
			clientTable.put(login, newClient);
			HttpSession session = req.getSession(true);
			session.setAttribute("client", newClient);
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