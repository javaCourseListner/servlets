package carShop.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
public class Client {
	
	@Id
	private String login;
	
	private String password;
	
	 
	@OneToMany(mappedBy = "client")
	public List<Car> car = new ArrayList<Car>();
	
	
		public void setCar(List<Car> car) {
			this.car = car;
		} 
	
		public List<Car> getCar() {
			return car;
		} 
		
	public Client(){}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	} 
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}	

	@Override
	public String toString() {
		return login.toString();
	}

	
	
}
