package carShop.DAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import carShop.entities.Car;
import carShop.entities.Options;
import carShop.entities.User;
import carShop.entities.UserOrder;


public class CarDao implements Dao{


	
			
		public static void main(String[] args) {
//			UserDao ud=new UserDao();
//			CarDao cd=new CarDao();
//			UserOrderDao uod = new UserOrderDao();
//
//		ud.deleteUser("adik");
		  // ud.setUser(new User("adik","adik",null));				
//			User user = ud.getUserById("adik");		
//			Car car = cd.getCarById(1);		
//			uod.setUserOrder(new UserOrder(car, user, new Date()));	
//			uod.deleteUserOrder(6);
		}
//		List<Car> listItem = new CarDao().getCars();
//		if(listItem != null){
//			for(Car i:listItem){
//				System.out.println(i.toString());
//			}
//		}		
	
//   CarDao cd =new CarDao();
//	cd.setCar(new Car("ostin","red",new Options(true,true,false),64645));	
//	cd.setCar(new Car("BMW","blue",new Options(false,true,false),645564));
//	cd.setCar(new Car("Merss","black",new Options(true,true,true),5555));
////	
//	User u = new User();
//	u.setLogin("admin");
//	u.setPassword(getHash("admin"));
//	u.setAdminRights(true);
//	u.setValid(true);
//     cd.setUserOrder(new UserOrder(car, u, date));
//		Car c=new Car();
//	c.setCarId(1);
	//u.getUserOrder().add(new UserOrder(c, u, new Date(43342)));
//	cd.setUser(u);
//	
//	Car c=new Car();
//	c.setCarId(1);
//	cd.setUserOrder(new UserOrder(c, u, new Date(44444)));
	//Long cg=cd.getSum("qq");
	
		//System.out.println(cg);
//cd.getSumGroup("a");
//for(Car car:cd.getCars())System.out.println(car);
 
//for(User u: new UserDao().getUsers())System.out.println(u);
////System.out.println(od.getSumGroupByMounth("wd"));
//	
	
	
	
	

	
	public Car getCarById(int id){					
		EntityManager em = factory.createEntityManager();
		return em.find(Car.class, id);
	}
	
	public void setCar(Car car){		
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(car);
		em.getTransaction().commit();
	}

	
	public List<Car> getCars(){		
		EntityManager em = factory.createEntityManager();
		TypedQuery<Car> query = em.createQuery("SELECT c FROM Car c",Car.class);
		List<Car> listItem=null;
		try {
			listItem=query.getResultList();
		}finally{
			em.close();														
		}																																//
		return listItem;
	}
		
}
