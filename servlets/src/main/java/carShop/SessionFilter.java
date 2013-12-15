package carShop;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionFilter implements Filter{

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub		
	}

	
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest hreq=(HttpServletRequest) req;
		HttpServletResponse hresp=(HttpServletResponse) resp;
		System.out.println("dd");
		if(hreq.getSession(false) == null){		    
			hresp.sendRedirect("guestWelcomePage.jsp");
		}else{
			chain.doFilter(req, resp);}
	}

	
	public void destroy() {
		// TODO Auto-generated method stub		
	}

}
