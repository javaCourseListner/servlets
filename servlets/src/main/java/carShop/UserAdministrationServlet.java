package carShop;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import carShop.DAO.UserDao;
import carShop.DAO.UserOrderDao;
import carShop.entities.User;
import carShop.entities.UserOrder;

public class UserAdministrationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UserDao userDao = new UserDao();
	private UserOrderDao userOrderDao = new UserOrderDao() ;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("jsp/admin/userPanel.jsp").forward(req, resp);;	
	}
		
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		String parm = null;		
		if((parm = req.getParameter("targetUser"))!= null){			
			findUser(req, resp, parm);			
		}else if((parm = req.getParameter("targetUserList"))!= null){			
			allUsersList(req, resp);		
		}else if((parm = req.getParameter("setAdmRts"))!= null){		
			setAdminRights(req, resp, parm);		
		}else if((parm = req.getParameter("delete"))!= null){
			deleteUser(req, resp, parm);
		}else if((parm = req.getParameter("valid"))!= null){		
			validateUser(req, resp, parm);	
		}else if((parm = req.getParameter("invalid"))!= null){		
			invalidaeUser(req, resp, parm);	
		}else if((parm = req.getParameter("rmvAdmRts"))!= null){		
			removeAdminRights(req, resp, parm);	
		}else if((parm = req.getParameter("admnList"))!= null){		
			adminUsersList(req, resp);		
		}else if((parm = req.getParameter("invldList"))!= null){		
			invalidUsersList(req, resp);		
		}else if((parm = req.getParameter("userOrders"))!= null){		
			getUserOrders(req, resp, parm);	
		}else if((parm = req.getParameter("dltOrder"))!= null){		
			deleteOrder(req, resp, parm);			
		}
	}

	
	private void deleteOrder(HttpServletRequest req, HttpServletResponse resp,
			String parm) throws ServletException, IOException {
		String userLogin = req.getParameter("user");
		int userOrderId = Integer.parseInt(parm);			
		userOrderDao.deleteUserOrder(userOrderId);			
		User user = userDao.getUserById(userLogin);			
		List<UserOrder> list = user.getUserOrder();
		req.setAttribute("targetUser", user);
		req.setAttribute("orders", list);
		req.getRequestDispatcher("jsp/admin/user.jsp").forward(req, resp);
	}

	
	private void getUserOrders(HttpServletRequest req,
			HttpServletResponse resp, String parm) throws ServletException,
			IOException {
		User user = userDao.getUserById(parm);
		req.setAttribute("targetUser", user);	
		List<UserOrder> list = user.getUserOrder();	
		req.setAttribute("orders", list);
		req.getRequestDispatcher("jsp/admin/user.jsp").forward(req, resp);
	}

		
	private void deleteUser(HttpServletRequest req, HttpServletResponse resp,
			String parm) throws ServletException, IOException {
		userDao.deleteUser(parm);
		req.getRequestDispatcher("jsp/admin/userPanel.jsp").forward(req, resp);
	}

	
	private void invalidUsersList(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		List<User> users = userDao.getInvalidUsers();
		req.setAttribute("targetUserList", users);
		req.getRequestDispatcher("jsp/admin/userList.jsp").forward(req, resp);
	}

	
	private void adminUsersList(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<User> users = userDao.getAdminUsers();
		req.setAttribute("targetUserList", users);
		req.getRequestDispatcher("jsp/admin/userList.jsp").forward(req, resp);
	}

	
	private void removeAdminRights(HttpServletRequest req,
			HttpServletResponse resp, String parm) throws ServletException, IOException {
		userDao.removeAdminRights(parm);
		User user = userDao.getUserById(parm);
		req.setAttribute("targetUser", user);			
		req.getRequestDispatcher("jsp/admin/user.jsp").forward(req, resp);
	}

	
	private void invalidaeUser(HttpServletRequest req,
			HttpServletResponse resp, String parm) throws ServletException, IOException {
		userDao.setValidFalse(parm);
		User user = userDao.getUserById(parm);
		req.setAttribute("targetUser", user);			
		req.getRequestDispatcher("jsp/admin/user.jsp").forward(req, resp);
	}

	
	private void validateUser(HttpServletRequest req, HttpServletResponse resp,
			String parm) throws ServletException, IOException {
		userDao.setValidTrue(parm);
		User user = userDao.getUserById(parm);
		req.setAttribute("targetUser", user);			
		req.getRequestDispatcher("jsp/admin/user.jsp").forward(req, resp);
	}

	
	private void setAdminRights(HttpServletRequest req,
			HttpServletResponse resp, String parm) throws ServletException,IOException {
		userDao.setAdminRights(parm);
		User user = userDao.getUserById(parm);
		req.setAttribute("targetUser", user);			
		req.getRequestDispatcher("jsp/admin/user.jsp").forward(req, resp);
	}

	
	private void allUsersList(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<User> users = userDao.getUsers();
		req.setAttribute("targetUserList", users);
		req.getRequestDispatcher("jsp/admin/userList.jsp").forward(req, resp);
	}

	
	private void findUser(HttpServletRequest req, 
			HttpServletResponse resp, String parm) throws ServletException, IOException {
		User user = userDao.getUserById(parm);
		if(user != null){
			req.setAttribute("targetUser", user);
			req.getRequestDispatcher("jsp/admin/user.jsp").forward(req, resp);
		}else{
			req.getRequestDispatcher("jsp/admin/userPanel.jsp").forward(req, resp);
		}
	}	
}