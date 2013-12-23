package carShop.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import carShop.entities.User;

public class ValidationFilter {
	 
	public void doFilter(ServletRequest req, ServletResponse res,
	            FilterChain chain) throws IOException, ServletException {
	 
	        HttpServletRequest request = (HttpServletRequest) req;
	        HttpServletResponse response = (HttpServletResponse) res;
	        HttpSession session = request.getSession(false);
  
	        if (session == null){      	
	        	response.sendRedirect("guestWelcomePage.html");
	        	return;    
	        }
	        
	        User user = (User) session.getAttribute("user");
		    if (!isValid(user)){      	
		    	response.sendRedirect("welcomePage");
			    return;    
		     }

	        chain.doFilter(req, res);        
	    }
	  
		private boolean isValid(User user) {
			return ((user != null)&&(user.isValid()== true));
					
		}
}
