
import java.util.List;

import org.junit.Test;

import carShop.DAO.UserOrderDao;
import carShop.entities.UserOrder;


public class UserOrderDaoTest {
	
	UserOrderDao usrOrdDao = new UserOrderDao();
	
//	@Test
//	public void getUserOrdersTest(){
//		List<UserOrder> list = usrOrdDao.getUserOrders("lolo");
//		System.out.println(list.size());
//	}

	@Test
	public void deleteUserOrdersTest(){
		usrOrdDao.deleteAllUserOrders("lolo");
		List<UserOrder> list = usrOrdDao.getUserOrders("lolo");
		System.out.println(list.size());
	}


}
