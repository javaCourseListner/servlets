package carShop.DAO;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import carShop.entities.Car;

public class CarDao implements Dao{
	
	public Car getCarById(int id){					
		EntityManager em = factory.createEntityManager();
		Car car = null;
		try{	
			car = em.find(Car.class, id);		
		}finally{
			em.close();
		}
		return car;
	}
	
	
	public void setCar(Car car){		
		EntityManager em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try{	
			et.begin();
			em.persist(car);
			et.commit();
		}catch(Exception e){
			et.rollback();
		}finally{
			em.close();
		}	
	}

	
	public List<Car> getCars(){		
		EntityManager em = factory.createEntityManager();
		TypedQuery<Car> query = em.createQuery("SELECT c FROM Car c",Car.class);
		List<Car> listItem=new LinkedList<Car>();
		try {
			listItem=query.getResultList();
		}finally{
			em.close();														
		}																																//
		return listItem;
	}
		
	
	public List<Car> getUnorderedCars() {
		EntityManager em = factory.createEntityManager();
		TypedQuery<Car> query = em.createQuery(
								    "SELECT c FROM Car c "
								  + "WHERE c.carId NOT IN"
								  + "(SELECT u.car.carId from UserOrder u)",Car.class);
		List<Car> listItem=new LinkedList<Car>();
		try {
			listItem = query.getResultList();
		}finally{
			em.close();														
		}																																//
		return listItem;
	}
	
	
	public void setNewPrice(int carId,int price){		
		EntityManager em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try{
			et.begin();
		    Car car = em.getReference(Car.class, carId);
			car.setPrice(price);
			et.commit();
		}catch(Exception e){
			et.rollback();
		}finally{
			em.close();
		}	
	}
	
	
	public void setNewDescription(int id,String description){		
		EntityManager em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try{
			et.begin();
		    Car car = em.getReference(Car.class,id);
			car.setDescription(description);
			et.commit();
		}catch(Exception e){
			et.rollback();
		}finally{
			em.close();
		}	
	}


	public List<Car> getCarsByModel(String model){		
		EntityManager em = factory.createEntityManager();
		TypedQuery<Car> query = em.createQuery(
								    "SELECT c FROM Car c "
								  + "WHERE c.model=:model",Car.class);
		query.setParameter("model", model);
		List<Car> listItem=new LinkedList<Car>();
		try {
			listItem = query.getResultList();
		}finally{
			em.close();														
		}																																//
		return listItem;
	}
	

	public List<Car> getCarsByColor(String color){		
		EntityManager em = factory.createEntityManager();
		TypedQuery<Car> query = em.createQuery(
								    "SELECT c FROM Car c "
								  + "WHERE c.model=:color",Car.class);
		query.setParameter("color", color);
		List<Car> listItem=new LinkedList<Car>();
		try {
			listItem = query.getResultList();
		}finally{
			em.close();														
		}																																//
		return listItem;
	}

	
	public void deleteCarsByModel(String model){		
		EntityManager em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			Query query = em.createQuery(
								  "DELETE FROM Car c "
								+ "WHERE c.model=:model");
			query.setParameter("model", model);
			query.executeUpdate();
			et.commit();
		}catch(Exception e){
			et.rollback();
		}finally{
			em.close();														
		}																																//
	}


	public long getCountOrdersOnCar(int id) {
		EntityManager em = factory.createEntityManager();
		TypedQuery<Long> query = em.createQuery( 
			                	    "SELECT COUNT(u) FROM UserOrder u "
						  + "WHERE u.car.carId=:carId ",Long.class);						
		query.setParameter("carId",id);
		long count = 0;
		try {
			count=query.getSingleResult();
		}finally{
			em.close();														
		}																																//								
		return count;	
	}


	public void deleteCarById(int carId) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try{				
			et.begin();		
			Car car = em.getReference(Car.class, carId);
		    em.remove(car);
			et.commit();
		}catch(Exception e){
			et.rollback();
		}finally{
			em.close();
		}
		
	}
}
