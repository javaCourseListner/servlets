import java.util.Date;

import org.junit.Test;

import carShop.DAO.CarDao;
import carShop.DAO.UserDao;
import carShop.DAO.UserOrderDao;
import carShop.entities.Car;
import carShop.entities.Options;
import carShop.entities.User;
import carShop.entities.UserOrder;


public class RemoveTest {

	private static UserDao userDao = new UserDao();
	private static CarDao carDao = new CarDao();
	
	
	@Test
	public void ValidTest(){
		String login = "testUser";
		User toSet = new User(login, "password", null);
		userDao.setUser(toSet);
		userDao.setValidTrue(login);
		User get = userDao.getUserById(login);
		System.out.println(get.isValid());
//		userDao.deleteUser(login);
//		cd.setCar(new Car("ostin","red",new Options(true,true,false),64645));	
//		cd.setCar(new Car("BMW","blue",new Options(false,true,false),645564));
//		cd.setCar(new Car("Merss","black",new Options(true,true,true),5555));			
//	    ud.setUser(new User("adik","adik",null));				
//		User user = ud.getUserById("adik");		
//		Car car = cd.getCarById(1);		
//		uod.setUserOrder(new UserOrder(car, user, new Date()));				
//		ud.deleteUser("adik");
			
	}
}
