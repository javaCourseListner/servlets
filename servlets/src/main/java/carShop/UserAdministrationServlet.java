package carShop;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import carShop.DAO.UserDao;
import carShop.entities.User;

public class UserAdministrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao = new UserDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("jsp/admin/userPanel.jsp").forward(req, resp);;	
	}
	
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String parm = null;
		
		if((parm = req.getParameter("targetUser"))!= null){			
			User user = userDao.getUserById(parm);
			if(user != null){
				req.setAttribute("targetUser", user);
				req.getRequestDispatcher("jsp/admin/user.jsp").forward(req, resp);
			}else{
				req.getRequestDispatcher("jsp/admin/userPanel.jsp").forward(req, resp);
			}			
		}else if((parm = req.getParameter("targetUserList"))!= null){			
			List<User> users = userDao.getUsers();
			req.setAttribute("targetUserList", users);
			req.getRequestDispatcher("jsp/admin/userList.jsp").forward(req, resp);		
		}else if((parm = req.getParameter("validate"))!= null){
		
						
		}else if((parm = req.getParameter("delete"))!= null){
			userDao.deleteUser(parm);
			req.getRequestDispatcher("jsp/admin/userPanel.jsp").forward(req, resp);
		}else if((parm = req.getParameter("adminRights"))!= null){
		
		
		}
		
	}
	
	
	

	
	
	
	
	
	
	
	
}
