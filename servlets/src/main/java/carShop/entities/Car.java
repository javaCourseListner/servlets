package carShop.entities;

import javax.persistence.*;

@Entity
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private long Id;
	
	private String model; 
	
	private String color; 
	
	@Embedded
	private Options options ;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name= "login")
	private Client client;
			
	public Car(String model, String color, Options options, Client client) { 
		this.model=model;
		this.color=color;
		this.options=options;
		this.client=client;
	}
	
	public Car() { }
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
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
			
	public void setOptions(Options options) {
		this.options = options;
	}	
	
	public Options getOptions() {
		return options;
	}
	
}