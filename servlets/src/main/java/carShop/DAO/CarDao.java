package carShop.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import carShop.entities.Car;


public class CarDao {
	
	private static final String PERSISTENCE_UNIT_NAME = "mySqlUnit";
	private static EntityManagerFactory factory;
 
	
	static{
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);			
	}
	
	
	
	
//	public static void main(String[] args) {			
//		List<Car> listItem = new CarDao().getCars();
//		if(listItem != null){
//			for(Car i:listItem){
//				System.out.println(i.toString());
//			}
//		}		
//	}
	
	
	
	
	
	
	

	
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
			em.close();														// 
		}																	//															//
		return listItem;
	}




}
