package carShop.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



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

	@Temporal(TemporalType.DATE)
	private Date date;
	
	public UserOrder(){};

	public UserOrder(Car car, User user, Date date) {
		this.car = car;
		this.user = user;
		this.date = date;
	}

	public int getUserOrderId() {
		return userOrderId;
	}

	public void setUserOrderId(int userOrderId) {
		this.userOrderId = userOrderId;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


}
