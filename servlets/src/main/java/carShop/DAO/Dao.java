package carShop.DAO;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public interface Dao {	
	 static EntityManagerFactory factory =
			Persistence.createEntityManagerFactory("hiberUnit");		
}
