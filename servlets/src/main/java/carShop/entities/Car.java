package carShop.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Cacheable
public class Car implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int carId;
	
	private String model; 
	
	private String color; 
	
	@Embedded
	private Options options ;
	
	private int price;

	@Lob
	private String description;
	
	
	public Car(String model, String color, Options options,
			int price, String description) {
		this.model = model;
		this.color = color;
		this.options = options;
		this.price = price;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}