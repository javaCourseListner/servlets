package carShop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

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