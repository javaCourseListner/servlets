package carShop.DAO;

import java.text.DateFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import carShop.entities.UserOrder;


public class UserOrderDao implements Dao{	
						
	public void setUserOrder(UserOrder userOrder){		
		EntityManager em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try{	
			et.begin();
			em.persist(userOrder);
			et.commit();
		}catch(Exception e){
			et.rollback();
		}finally{
			em.close();
		}
	}
		
	
	public void deleteUserOrder(int id){				
		EntityManager em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try{	
			et .begin();
			UserOrder userOrder = em.getReference(UserOrder.class, id);
			em.remove(userOrder);
			et .commit();
		}catch(Exception e){
			et .rollback();
		}finally{
			em.close();
		}
	}

	
	public UserOrder getUserOrderById(int id) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		UserOrder userOrder = null;
		try{
			et .begin();
			userOrder =	em.find(UserOrder.class, id);
			et .commit();
		}finally{
			em.close();
		}
		return userOrder;
	}
	
	
	public List<UserOrder> getUserOrders(String login){		
		EntityManager em = factory.createEntityManager();
		TypedQuery<UserOrder> query = em.createQuery( 
			                  "SELECT u FROM UserOrder u "
					+ "WHERE u.user.login=:login",UserOrder.class);						
		query.setParameter("login",login);
		List<UserOrder> list=null;
		try {
			list=query.getResultList();
		}finally{
			em.close();														
		}																																//								
		return list;		
	}
	
	
	public Long getOrdersAmount(String login){		
		EntityManager em = factory.createEntityManager();
		TypedQuery<Long> query = em.createQuery(
		               		   "SELECT SUM(c.price) FROM Car c, UserOrder u "
					 + "WHERE c.carId=u.car.carId "
					 + "AND u.user.login=:login", Long.class);						
		query.setParameter("login",login);
		Long i =null;
		try {
			 i = query.getSingleResult();
		}finally{
			em.close();														
		}																																//
		return i;
	}
	
	
	public Map<String,Long> getSumGroupByMounth(String login){		
		EntityManager em = factory.createEntityManager();
		Query query = em.createQuery(
		               	       "SELECT month(u.date), sum(c.price) FROM Car c, UserOrder u "
		  		     + "WHERE c.carId=u.car.carId AND u.user.login=:login "		  		     
				     + "GROUP BY month(u.date)");					
		query.setParameter("login", login);									
		List<?> list = null;										
		try {
			 list = query.getResultList();
		}finally{													//only for hibernate provider, where MONTH() 					
			em.close();												//function is supported for other use 			
		}															//nativeSQL																
		Map<String,Long> map = new TreeMap<String,Long>();
			for(Object obj: list){
				Object[] objArr=(Object[]) obj;
				Integer month = (Integer)objArr[0];				
				Locale local = new Locale("en");
				DateFormatSymbols dfs = new DateFormatSymbols(local);
				String strMonth = dfs.getMonths()[month-1];		
				Long amount = (Long)objArr[1];
				map.put(strMonth, amount);
			}						
		return map;
	}
	
	
	public void deleteAllUserOrders(String login){		
		EntityManager em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			Query query = em.createQuery( 
			        		    "DELETE FROM UserOrder u "
						  + "WHERE u.user.login=:login");						
			query.setParameter("login",login);
			query.executeUpdate();
			et.commit();
		}catch(Exception e){
			et.rollback();
		}finally{			
			em.close();														
		}																																//								
		
	}
}
