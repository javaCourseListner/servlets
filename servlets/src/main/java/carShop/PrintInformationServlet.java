package carShop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import carShop.entities.Car;
import carShop.entities.Client;
import carShop.entities.ClientBase;

public class PrintInformationServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost (HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {		
		Client client =	(Client) req.getSession().getAttribute("client");
		PrintWriter out = null;		
		out = resp.getWriter();			
		out.print("<html> <body>"); 		
		out.print("<title>Client page</title>"); 	
		out.print("<h3>you are wellcome)  " + client.getLogin()+"</h3>");	
		out.print("<form action=\"logout\" method=\"get\">");
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
		out.print("<form action = \"myAccount\" method=\"POST\">");
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
		
	@Override
	public void init() throws ServletException {									
		ClientBase clientBase = (ClientBase) getServletContext().getAttribute("clientBase");		
		if (clientBase == null){
			clientBase = getBase();
			clientBase.initClientTable();
			getServletContext().setAttribute("clientBase", clientBase);
	 	}
	}
	
	@Override
	public void destroy() {		
		setBase();
	}
			
	private void setBase() {
		OutputStream os = null;
		try {
		JAXBContext jc =JAXBContext.newInstance(ClientBase.class);
		Marshaller m=jc.createMarshaller();		
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);		
		URL url =  AuthorizationServlet.class.getResource("clientBase.xml"); 
		os = new FileOutputStream(new File(url.toURI()));
		ClientBase clientBase = (ClientBase) getServletContext().getAttribute("clientBase");	
		clientBase.setClientTableToBase();
		m.marshal(clientBase, os);
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {		
			closeQuietly(os);		
		}
	}

	private ClientBase getBase(){				
		InputStream is = null;	
		ClientBase clientBase = null;
		try {		
			is = AuthorizationServlet.class.getResourceAsStream("clientBase.xml");	
			JAXBContext jc = JAXBContext.newInstance(ClientBase.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();		
			clientBase =(ClientBase)unmarshaller.unmarshal(is);
		} catch (JAXBException e) {		
			e.printStackTrace();
		}finally{			
			closeQuietly(is);	
		}
		return clientBase;	
	}
	
	private void closeQuietly(InputStream is){
		try {
			if (is != null) is.close();
		}catch (IOException e){/*NOP*/}	
	}	
		
	private void closeQuietly(OutputStream os){
		try {
			if (os != null) os.close();
		}catch (IOException e){/*NOP*/}	
	}	
}
