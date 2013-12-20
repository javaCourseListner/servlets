import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import carShop.DAO.CarDao;
import carShop.DAO.UserDao;
import carShop.DAO.UserOrderDao;
import carShop.entities.Car;
import carShop.entities.Options;
import carShop.entities.User;
import carShop.entities.UserOrder;


public class UserDaoTest {

	private static UserDao userDao = new UserDao();
//	private static CarDao carDao = new CarDao();
	
	
	@Test
	public void ValidTestTrue(){
		String login = "testUser";
		User usTo = new User(login, "password", null);
		userDao.setUser(usTo);
		userDao.setValidTrue(login);
		User usFrom = userDao.getUserById(login);
		Assert.assertTrue(usFrom.isValid());
		userDao.deleteUser(login);			
	}

	@Test
	public void ValidTestFalse(){
		String login = "testUser";
		User usTo = new User(login, "password", null);
		usTo.setValid(true);
		userDao.setUser(usTo);
		userDao.setValidFalse(login);
		User usFrom = userDao.getUserById(login);
		Assert.assertFalse(usFrom.isValid());
		userDao.deleteUser(login);				
	}

	@Test
	public void setAdminRights(){
		String login = "testUser";
		User usTo = new User(login, "password", null);
		userDao.setUser(usTo);
		userDao.setAdminRights(login);
		User usFrom = userDao.getUserById(login);
		Assert.assertTrue(usFrom.isAdminRights());
		userDao.deleteUser(login);			
	}

	@Test
	public void removeAdminRights(){
		String login = "testUser";
		User usTo = new User(login, "password", null);
		usTo.setAdminRights(true);
		userDao.setUser(usTo);
		userDao.removeAdminRights(login);
		User usFrom = userDao.getUserById(login);
		Assert.assertFalse(usFrom.isAdminRights());
		userDao.deleteUser(login);				
	}






}
