package carShop.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import carShop.entities.User;

public class UserDao implements Dao{
	
	
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

	
	public List<User> getUsers(){		
		EntityManager em = factory.createEntityManager();
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u",User.class);
		List<User> listItem=null;
		try {
			listItem = query.getResultList();
		}finally{
			em.close();														
		}																																//
		return listItem;
	}

}
