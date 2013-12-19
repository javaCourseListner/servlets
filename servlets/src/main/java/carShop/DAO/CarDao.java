package carShop.DAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import carShop.entities.Car;
import carShop.entities.Options;
import carShop.entities.User;
import carShop.entities.UserOrder;


public class CarDao implements Dao{

	public static String getHash(String str) {	        
		if (str == null) return null;
		MessageDigest md5 ;        
	    StringBuffer  hexString = new StringBuffer();	        
	    try {	                                    
	    	md5 = MessageDigest.getInstance("md5");	            
	    	md5.reset();
	    	md5.update(str.getBytes()); 	                        	                        
	    	byte messageDigest[] = md5.digest();	                        
	    	for (int i = 0; i < messageDigest.length; i++) {
	    		hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
	    	}	                                                                                        
	    }catch (NoSuchAlgorithmException e) {                        
	    	e.printStackTrace();
	    }	        
	    return hexString.toString();
	}
		
	
	public static void main(String[] args) {			
		
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
//	
	User u = new User();
	u.setLogin("admin");
	u.setPassword(getHash("admin"));
	u.setAdminRights(true);
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
 
new UserDao().setUser(u);
UserOrderDao od=new UserOrderDao();
System.out.println(od.getSumGroupByMounth("wd"));
	
	
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

	public List<Car> getUserOrderCars(String login){		
		EntityManager em = factory.createEntityManager();
		
		TypedQuery<Car> query = 
		em.createQuery(" SELECT c FROM Car c, UserOrder u "
					  +" WHERE c.carId=u.car.carId"
					  +" AND u.user.login=:login", Car.class);				
		
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
		TypedQuery<Long> query = 
		em.createQuery(" SELECT SUM(c.price) FROM Car c, UserOrder u "
					  +" WHERE c.carId=u.car.carId"
					  +" AND u.user.login=:login", Long.class);						
		query.setParameter("login",login);
		Long i =null;
		try {
			 i = query.getSingleResult();
		}finally{
			em.close();														
		}																																//
		return i;
	}
	
	
	
	
//	TypedQuery<Integer> query = 
//	em.createQuery(" SELECT o.order_header_id FROM order_header o, customer c "
//				  +" WHERE c.customer_id=o.customer_id.customer_id"
//				  +" AND c.customer_name='Customer1'",Integer.class);		
//	List<Integer> listM=null;
//	try {
//		listM=query.getResultList();
//	}finally{
//		em.close();													
//	}																	
//	if(listM != null){
//		for(Integer i:listM){
//			System.out.println(i.toString());
//		}

//	TypedQuery<Integer> query = 
//	em.createQuery(" SELECT o.order_header_id FROM order_header o, customer c "
//				  +" WHERE c.customer_id=o.customer_id.customer_id"
//				  +" AND c.customer_name='Customer1'",Integer.class);		
//	List<Integer> listM=null;
//	try {
//		listM=query.getResultList();
//	}finally{
//		em.close();													
//	}																	
//	if(listM != null){
//		for(Integer i:listM){
//			System.out.println(i.toString());
//		}

	
}
