package carShop.entities;

import javax.persistence.*;

@Entity
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int Id;
	
	private String model; 
	
	private String color; 
	
	@Embedded
	private Options options ;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name= "login")
	private User user;
			
	public Car(String model, String color, Options options, User user) { 
		this.model=model;
		this.color=color;
		this.options=options;
		this.user=user;
	}
	
	public Car() { }
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	
	public void setId(int id) {
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