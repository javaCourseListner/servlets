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

	
	@Test
	public void removeTest(){
		UserDao ud=new UserDao();
		CarDao cd=new CarDao();
		UserOrderDao uod = new UserOrderDao();
		
uod.deleteUserOrder(1);
		
		
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
