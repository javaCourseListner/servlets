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
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import carShop.entities.ClientBase;


public class AuthorisationServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {						
		HttpSession session = req.getSession(false);
		if ( session != null) session.invalidate();			
		PrintWriter out = resp.getWriter();
		printAuthorisationForm(out);
		out.close();
	}	
  	
	
	private void printAuthorisationForm(PrintWriter out){    	
		out.print("<html> <body>");  	   	
    	out.print("<pre>");
    	out.print("<title>Authorisation page</title>");
    	out.print("<h3> Authorisation page </h3>");
    	out.print("<form action = \"clientAccount\" method=\"POST\">");
    	out.print("<br> <label> Input your login:    <input type = \"text\" name = \"login\" /></label>");
    	out.print("<br> <label> Input your password: <input type = \"password\" name = \"password\" /></label>");
    	out.print("<br>");
    	out.print("<br> <input type = \"submit\" value=\"Submit\"/>");
    	out.print("</form>");
    	out.print("<pre>");
    	out.print("</body> </html>"); 	
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
		URL url =  AuthorisationServlet.class.getResource("clientBase.xml"); 
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
			is = AuthorisationServlet.class.getResourceAsStream("clientBase.xml");	
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
