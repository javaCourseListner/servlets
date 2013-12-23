import java.util.List;

import org.junit.Test;

import carShop.DAO.UserOrderDao;
import carShop.entities.UserOrder;


public class UserOrderDaoTest {
	
	UserOrderDao usrOrdDao = new UserOrderDao();
	

	@Test
	public void deleteUserOrdersTest(){
		usrOrdDao.deleteAllUserOrders("user");
		List<UserOrder> list = usrOrdDao.getUserOrders("user");
		System.out.println(list.size());
	}

}
