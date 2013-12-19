package carShop.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
public class User {
	
	@Id
	private String login;
	
	private String password;
	
	private boolean adminRights;
	 
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	public List<UserOrder> userOrder = new ArrayList<UserOrder>();
		
	public void setUserOrder(List<UserOrder> userOrder) {
		this.userOrder = userOrder;
	} 
	
	public List<UserOrder> getUserOrder() {
		return userOrder;
	} 
			
	public User(){}
		
	public User(String login, String password, List<UserOrder> userOrder) {
		this.login = login;
		this.password = password;
		this.userOrder = userOrder;
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

	public boolean isAdminRights() {
		return adminRights;
	}

	public void setAdminRights(boolean adminRights) {
		this.adminRights = adminRights;
	}	
}
