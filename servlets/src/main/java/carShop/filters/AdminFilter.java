package carShop.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import carShop.entities.User;

public class AdminFilter implements Filter{

	  public void doFilter(ServletRequest req, ServletResponse res,
	            FilterChain chain) throws IOException, ServletException {
	 
	        HttpServletRequest request = (HttpServletRequest) req;
	        HttpServletResponse response = (HttpServletResponse) res;
	        HttpSession session = request.getSession(false);
    
	        if (session == null){      	
	        	response.sendRedirect("/servlets/guestWelcomePage.html");
	        	return;    
	        }
	        
	        User user = (User) session.getAttribute("user");
		if (!isAdmin(user)){      	
		    response.sendRedirect("/servlets/welcomePage");
		    return;    
		}
 
	        chain.doFilter(req, res);        
	 }
	  
	   
 	private boolean isAdmin(User user) {
	 	return ((user != null)&&(user.isAdminRights()== true));					
	 }
	  	 
		
	 public void init(FilterConfig filterConfig) throws ServletException {/*NOP*/}
	
		
	 public void destroy() {/*NOP*/}
}
