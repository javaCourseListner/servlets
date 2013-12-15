package carShop.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
//import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Options {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
			
	
	@ManyToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL,targetEntity=Car.class)
	//@JoinColumn(name="carId")
		@JoinTable(
		name="car_options",		     
		joinColumns={@JoinColumn(name="car_Id", referencedColumnName="carId")},
		inverseJoinColumns={@JoinColumn(name="option_id", referencedColumnName="Id")})
	private List<Car> car ;

	
	private String name;
	
	public Options() {}
	
	public Options(String name, Car car) {
		this.car = car;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}



	public long getId() {
		return Id;
	}



	public void setId(long id) {
		Id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Car getCar() {
		return car;
	}



	public void setCar(Car car) {
		this.car = car;
	}
}
