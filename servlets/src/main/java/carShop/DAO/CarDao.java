package carShop.DAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import carShop.entities.Car;
import carShop.entities.Options;
import carShop.entities.User;
import carShop.entities.UserOrder;


public class CarDao implements Dao{


	
			
		public static void main(String[] args) {

			CarDao cd =new CarDao();
			cd.setCar(new Car("opel","red",new Options(true,true,false),666,"nice car"));	
			cd.setCar(new Car("opel","red",new Options(true,true,false),666,"nice car"));
//			List<Car> car = cd.getCarsByModel("modelForTesting");
//			System.out.println(car.size());
//			cd.deleteCarsByModel("modelForTesting");	
//			car = cd.getCarsByModel("modelForTesting");
//			System.out.println(car.size());
			
			//				UserOrderDao usrOrdDao = new UserOrderDao();
//				List<UserOrder> list = usrOrdDao.getUserOrders("popo");
//				System.out.println(list.size());
//				UserOrder uo= list.get(1);
//				usrOrdDao.deleteUserOrder(uo.getUserOrderId());
						
//			User user = ud.getUserById("adik");		
//			Car car = cd.getCarById(1);		
//			uod.setUserOrder(new UserOrder(car, user, new Date()));	
		//	ud.deleteUser("kiki");
		
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
}	
	
	
	

	
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


	public List<Car> getUnorderedCars() {
		// TODO Auto-generated method stub
		return null;
	}






}
