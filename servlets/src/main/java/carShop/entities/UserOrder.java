package carShop.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class UserOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)		
	private int userOrderId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CarId")
	private Car car;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "UserId")
	private User user;

	
	public UserOrder(){};

	public UserOrder(Car car, User user) {
		this.car = car;
		this.user = user;
	}


}
