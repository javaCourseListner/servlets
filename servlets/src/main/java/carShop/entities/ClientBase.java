package carShop.entities;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlType
@XmlRootElement
public class ClientBase {		
	
	@XmlElement(name= "client")
	private List <Client> clientList = new LinkedList<Client>() ;
		
	private Map<String, Client> clientTable = null;
			
	synchronized  public void initClientTable(){					  
		if(clientTable == null){									// Method must be called after 
			 clientTable = new Hashtable<String, Client>();			// unmarshalling to initialize map.
			for(Client element : clientList){
				clientTable.put(element.getLogin(), element);		
			}	
		}
	}
		
	synchronized public void setClientTableToBase(){			
		clientList = new LinkedList<Client>();							// Method must be called before 			
		for(Entry<String, Client> entry : clientTable.entrySet()){		// marshalling to set data  to the list.
			clientList.add(entry.getValue()) ;							
		}	
	}	
	
	public Map<String, Client> getClientTable(){
		return clientTable;	
	} 
	
	
}
