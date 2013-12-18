package carShop.DAO;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import carShop.entities.Car;
import carShop.entities.Options;
import carShop.entities.User;
import carShop.entities.UserOrder;


public class CarDao {
	
	private static final String PERSISTENCE_UNIT_NAME = "mySqlUnit";
	private static EntityManagerFactory factory;
 
	
	static{
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);			
	}
	
	
	
	
	public static void main(String[] args) {			
		
//		List<Car> listItem = new CarDao().getCars();
//		if(listItem != null){
//			for(Car i:listItem){
//				System.out.println(i.toString());
//			}
//		}		
	
	CarDao cd =new CarDao();
	cd.setCar(new Car("ostin","red",new Options(true,true,false)));	
	cd.setCar(new Car("BMW","blue",new Options(false,true,false)));
	cd.setCar(new Car("Merss","black",new Options(true,true,true)));
	
	User u = new User();
	u.setLogin("adikrr");
	u.setPassword("xxx");
	Car c=new Car();
	c.setCarId(1);
	u.getUserOrder().add(new UserOrder(c, u, new Date()));
	cd.setUser(u);
	
	//Car c=new Car();
	//c.setCarId(1);
	cd.setUserOrder(new UserOrder(c, u, new Date()));
	}
	
	
	public User getUserById(String login){					
		EntityManager em = factory.createEntityManager();
		return em.find(User.class, login);
	}
	
	
	public void setUser(User user){		
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}
	
	public void setCar(Car car){		
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(car);
		em.getTransaction().commit();
	}

	public void setUserOrder(UserOrder userOrder){		
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(userOrder);
		em.getTransaction().commit();
	}
	
	
	public Car getCarById(int id){					
		EntityManager em = factory.createEntityManager();
		return em.find(Car.class, id);
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
