package carShop.entities;
//import java.util.ArrayList;
//import java.util.List;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private long Id;
	
	private String model; 
	
	private String color; 
	
	@ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name= "login")
	private Client client;
	
	@ManyToMany(mappedBy = "car",cascade=CascadeType.ALL)
	private List<Options> option = new ArrayList<Options>();
	
	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}

	Car(){}
	
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	} 
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}	
	
	public void setId(long id) {
		this.Id = id;
	}
	
	public long getId() {
		return Id;
	}
	public String getSringForPrint(){
		return " Id:"+ Id+" model:="+model+" color:"+color;		
	}
	


			

	public Car(String model,String color, String[] options,Client client){
		this.model = model;
		this.color = color;		
		this.client = client;	
		if(options != null){
			for(String str : options){
			Options opt = new Options(str,this);
				this.option.add(opt);
			}
		}		
	}
		

	
	public void setOptions(List<Options> options) {
		this.option = options;
	}
	
	public List<Options> getOptions() {
		return option;
	}
	
}
