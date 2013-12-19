package carShop.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import carShop.entities.UserOrder;

public class UserOrderDao {	
			
	private static EntityManagerFactory factory =
		Persistence.createEntityManagerFactory("mySqlUnit");			
	
	public void setUserOrder(UserOrder userOrder){		
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(userOrder);
		em.getTransaction().commit();
	}
		
}
