package carShop.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import carShop.entities.User;

public class UserDao {
	
	private static EntityManagerFactory factory =
	  Persistence.createEntityManagerFactory("mySqlUnit");			
		
	
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
}
