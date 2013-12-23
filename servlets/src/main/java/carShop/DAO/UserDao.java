package carShop.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import carShop.entities.User;

public class UserDao implements Dao{
		
	public User getUserById(String login){					
		EntityManager em = factory.createEntityManager();		
		User user = null;
		try{
			user = em.find(User.class, login);
		}finally{
			em.close();
		}
		return user;
	}
		
	public void setUser(User user){		
		EntityManager em = factory.createEntityManager();
		try{	
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
		}finally{
			em.close();
		}
	}

	public void deleteUser(String login){		
		EntityManager em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try{				
			et.begin();
			Query query = em.createQuery( 
	                "DELETE FROM UserOrder u "
				  + "WHERE u.user.login=:login");						
			query.setParameter("login",login);
			query.executeUpdate();		
			User user = em.getReference(User.class, login);
		    em.remove(user);
			et.commit();
		}catch(Exception e){
			et.rollback();
		}finally{
			em.close();
		}		
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

	public void setValidTrue(String login){		
		EntityManager em = factory.createEntityManager();
		try{
			em.getTransaction().begin();
		    User user = em.getReference(User.class, login);
			user.setValid(true);
			em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
		}finally{
			em.close();
		}	
	}
	
	public void setValidFalse(String login){		
		EntityManager em = factory.createEntityManager();
		try{
			em.getTransaction().begin();
		    User user = em.getReference(User.class, login);
			user.setValid(false);
			em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
		}finally{
			em.close();
		}	
	}
	
	public void setAdminRights(String login){		
		EntityManager em = factory.createEntityManager();
		try{
			em.getTransaction().begin();
		    User user = em.getReference(User.class, login);
			user.setAdminRights(true);
			em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
		}finally{
			em.close();
		}	
	}
	
	public void removeAdminRights(String login){		
		EntityManager em = factory.createEntityManager();
		try{
			em.getTransaction().begin();
		    User user = em.getReference(User.class, login);
			user.setAdminRights(false);
			em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
		}finally{
			em.close();
		}	
	}
	
	public List<User> getInvalidUsers(){		
		EntityManager em = factory.createEntityManager();
		TypedQuery<User> query = em.createQuery(
									"SELECT u FROM User u "
								  + "WHERE u.valid=false",User.class);
		List<User> listItem=null;
		try {
			listItem = query.getResultList();
		}finally{
			em.close();														
		}																																//
		return listItem;
	}
	
	public List<User> getAdminUsers(){		
		EntityManager em = factory.createEntityManager();
		TypedQuery<User> query = em.createQuery(
									"SELECT u FROM User u "
								  + "WHERE u.adminRights=true",User.class);
		List<User> listItem=null;
		try {
			listItem = query.getResultList();
		}finally{
			em.close();														
		}																																//
		return listItem;
	}
}