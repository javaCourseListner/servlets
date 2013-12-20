package carShop.DAO;

import java.text.DateFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import carShop.entities.Car;
import carShop.entities.UserOrder;

public class UserOrderDao implements Dao{	
						
	public void setUserOrder(UserOrder userOrder){		
		EntityManager em = factory.createEntityManager();
		try{	
			em.getTransaction().begin();
			em.persist(userOrder);
			em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
		}finally{
			em.close();
		}
	}
		
	public void deleteUserOrder(int id){				
		EntityManager em = factory.createEntityManager();
		try{	
			em.getTransaction().begin();
			UserOrder userOrder = em.getReference(UserOrder.class, id);
			em.remove(userOrder);
			em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
		}finally{
			em.close();
		}
	}

	public UserOrder getUserOrderById(int id) {
		EntityManager em = factory.createEntityManager();
		UserOrder userOrder = null;
		try{
			userOrder =	em.find(UserOrder.class, id);
		}finally{
			em.close();
		}
		return userOrder;
	}
	
	public List<Car> getUserOrderCars(String login){		
		EntityManager em = factory.createEntityManager();
		TypedQuery<Car> query = em.createQuery(
		                "SELECT c FROM Car c, UserOrder u "
					  + "WHERE c.carId=u.car.carId "
					  + "AND u.user.login=:login", Car.class);						
		query.setParameter("login",login);
		List<Car> listItem=null;
		try {
			listItem=query.getResultList();
		}finally{
			em.close();														
		}																																//
		return listItem;
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
		}finally{													//only for hibernate provider, for other					
			em.close();												//use getSumGroupByMounthNative()			
		}																																//			
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
	
}
