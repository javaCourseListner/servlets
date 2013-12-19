package carShop.entities;

import javax.persistence.*;

@Entity
public class Car {

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int carId;
	
	private String model; 
	
	private String color; 
	
	@Embedded
	private Options options ;
	
	private int price;

	public Car(String model, String color, Options options, int price) { 
		this.model=model;
		this.color=color;
		this.options=options;
		this.price=price;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}