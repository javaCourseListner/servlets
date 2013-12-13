package carShop.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlType
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Client {
	
	private String login;
	
	private String password;
	
	public List<Car> car = new ArrayList<Car>();
	
	
		public void setCar(List<Car> car) {
			this.car = car;
		} 
	
		public List<Car> getCar() {
			return car;
		} 
		
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
