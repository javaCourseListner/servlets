package carShop.entities;

import javax.persistence.*;

@Entity
public class Car {

	@Id
	@OneToMany
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int carId;
	
	private String model; 
	
	private String color; 
	
	@Embedded
	private Options options ;
	


	public Car(String model, String color, Options options) { 
		this.model=model;
		this.color=color;
		this.options=options;

	}
	
	public Car() { }
	
	
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
	
	public void setCarId(int carId) {
		this.carId = carId;
	}
	
	public int getCarId() {
		return carId;
	}
			
	public void setOptions(Options options) {
		this.options = options;
	}	
	
	public Options getOptions() {
		return options;
	}
	
}