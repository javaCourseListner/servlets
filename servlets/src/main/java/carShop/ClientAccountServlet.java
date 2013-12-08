package carShop;

import java.io.IOException;
import java.io.PrintWriter;
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


public class ClientAccountServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {		
		if(req.getSession(false) == null){
			clientAuthorisation(req, resp);
		}else{
			orderRegistration(req);
			printClientPage(req, resp);			
		}
	}

	private void clientAuthorisation(HttpServletRequest req, HttpServletResponse resp) {
		String login = req.getParameter("login");
		String password =req.getParameter("password");
		
		ClientBase clientBase = (ClientBase) getServletContext().getAttribute("clientBase");	
		Map<String, Client> clientTable = clientBase.getClientTable();		
		Client client = clientTable.get(login);
		
		if(login == null){
			printErrorPage(resp);
		}else if(password == null){
			printErrorPage(resp);
		}else if((client != null)&&(!password.equals(client.getPassword()))){
			printErrorPage(resp);
		}else if((client != null)&&(password.equals(client.getPassword()))){
			HttpSession session = req.getSession(true);
			session.setAttribute("client",client);
			printClientPage(req,resp);
		}else if(client == null){
			Client newClient = new Client();
			newClient.setLogin(login);
			newClient.setPassword(password);
			clientTable.put(login, newClient);
			HttpSession session = req.getSession(true);
			session.setAttribute("client", newClient);
			printClientPage(req,resp);
		}
	}
	
	private void printClientPage(HttpServletRequest req, HttpServletResponse resp) {	
		Client client =	(Client) req.getSession().getAttribute("client");
		PrintWriter out = null;		
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}			
		out.print("<html> <body>"); 		
		out.print("<title>Client page</title>"); 	
		out.print("<h3>you are wellcome)  " + client.getLogin()+"</h3>");	
		out.print("<form action=\"servlets\" method=\"get\">");
		out.print("<input type=\"submit\" value=\"logout\"></form>");
		printClientInformation(out, client);
		printInputForm(out);		 
		out.print("</body></html>");  
		out.print("</body></html> "); 
		out.close();
	}
		
	private void printClientInformation(PrintWriter out, Client client){					
		if(client.car.size()>0){
			out.print("<h3>Your orders </h3>");
			String toPrint = "";
			for(Car car: client.car){
				out.print("<br><h4>Car model:</h4>");
				if((toPrint = car.getModel())!= null) out.print(toPrint);			
				
				if((toPrint = car.getColor())!= null){ 
					out.print("<br><h4>Color:</h4>");
					out.print(toPrint);	
				}
				
				if(car.getOptions().size() > 0){
					out.print("<br><h4>Options:</h4>");
					for(String option : car.getOptions()){
						 out.print(option+" ");
					}		
				}
				out.print("<br>***");
			}
		}
	}
	
	private void printInputForm(PrintWriter out){
		out.print("<h3>Complete the form </h3>");
		out.print("<form action = \"clientAccount\" method=\"POST\">");
		out.print("<br> <h4>Input car model:</h4> ");
		out.print("<br> <input type = \"text\" name = \"model\" />");
		out.print("<br> <h4>Choose color:</h4> ");
		out.print("<br> <label> red <input type = \"radio\" name = \"color\" value= \"red\" /></label>");
		out.print(" <label> blue <input type = \"radio\" name = \"color\" value= \"red\" /></label>");
		out.print(" <label> green <input type = \"radio\" name = \"color\" value= \"red\" /></label>");
		out.print("<label> black <input type = \"radio\" name = \"color\" value= \"red\" /></label>");
		out.print("<br> <h4>Select options:</h4>");
		out.print("<br> <label> conditioner <input type = \"checkbox\" name = \"options\" value= \"conditioner\"/></label>");
		out.print(" <label> hydroamplifier <input type = \"checkbox\" name = \"options\" value= \"hydroamplifier\"/></label>");
		out.print(" <label> automatic transmission <input type = \"checkbox\" name = \"options\" value= \"automatic transmission\"/></label>");	
		out.print("<br><input type = \"submit\" value=\"Submit\"/>");
		out.print("</form>");
	}
	
	private void orderRegistration(HttpServletRequest req){			 																				
		String model = req.getParameter("model");									
		if(model != null){															// According the logic order is valid if
			String color = req.getParameter("color");								// field "model" is not null.		
			String[] options = req.getParameterValues("options");			
			Car car = new Car(model,color,options);	
			Client client =	(Client) req.getSession().getAttribute("client");	
			List<Car> cars = client.car;
			cars.add(car)	;	
		}
	}
	
	
	private void printErrorPage(HttpServletResponse resp) {
		PrintWriter out = null;		
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print("<html><body>"); 
		out.print("<h2>invalid password<h2>"); 
		out.print("</body></html>"); 		
		out.close();
	}
	
}
