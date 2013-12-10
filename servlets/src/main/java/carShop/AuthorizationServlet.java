package carShop;


import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import carShop.entities.Car;
import carShop.entities.Client;
import carShop.entities.ClientBase;


public class AuthorizationServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {		
		if(req.getSession(false) == null){
			clientAuthorisation(req, resp);
		}else{
			orderRegistration(req);
			req.getRequestDispatcher("/printInformation").forward(req, resp);		
		}
	}

	private void clientAuthorisation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		String login = req.getParameter("login");
		String password = getHash(req.getParameter("password"));
		
		ClientBase clientBase = (ClientBase) getServletContext().getAttribute("clientBase");	
		Map<String, Client> clientTable = clientBase.getClientTable();		
		Client client = clientTable.get(login);
		
		if(login == null){
			printErrorPage(resp, "input login");
		}else if(password == null){
			printErrorPage(resp, "input password");
		}else if((client != null)&&(!password.equals(client.getPassword()))){
			printErrorPage(resp, "invalid password");
		}else if((client != null)&&(password.equals(client.getPassword()))){				
			HttpSession session = req.getSession(true);
			session.setAttribute("client",client);
			req.getRequestDispatcher("/printInformation").forward(req, resp);
		}else if(client == null){
			Client newClient = new Client();
			newClient.setLogin(login);
			newClient.setPassword(password);
			clientTable.put(login, newClient);
			HttpSession session = req.getSession(true);
			session.setAttribute("client", newClient);
			req.getRequestDispatcher("/printInformation").forward(req, resp);
		}
	}
		
	private void orderRegistration(HttpServletRequest req){			 																				
		String model = req.getParameter("model");									
		if((model != null)&&(!model.equals(""))){									// According the logic order is valid if
			String color = req.getParameter("color");								// field "model" is not null.		
			String[] options = req.getParameterValues("options");			
			Car car = new Car(model,color,options);	
			Client client =	(Client) req.getSession().getAttribute("client");	
			List<Car> cars = client.car;
			cars.add(car);	
		}
	}
		
	private void printErrorPage(HttpServletResponse resp,String massage) throws IOException {
		PrintWriter out = null;		
		out = resp.getWriter();
		out.print("<html><body>"); 
		out.print("<h2>"+massage+"</h2>"); 
		out.print("</body></html>"); 		
		out.close();
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