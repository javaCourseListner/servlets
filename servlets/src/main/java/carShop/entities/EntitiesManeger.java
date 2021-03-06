package carShop.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class EntitiesManeger {		
	
	private static final String PERSISTENCE_UNIT_NAME = "CashM";
	private static EntityManagerFactory factory;
	private static EntityManager em;
	
	static {		
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
	}
			
	public Client getClientById(String login){					
		return em.find(Client.class, login);
	}
	
	public void setClient(Client client){		
		em.getTransaction().begin();
		em.persist(client);
		em.getTransaction().commit();
	}
	
	public void setCar(Car car){		
		em.getTransaction().begin();
		em.persist(car);
		em.getTransaction().commit();
	}
	
	public void setOption(Options option){		
		em.getTransaction().begin();
		em.persist(option);
		em.getTransaction().commit();
	}
	
}
