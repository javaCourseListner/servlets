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
	 
	private boolean valid;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	public List<UserOrder> userOrder = new ArrayList<UserOrder>();			// engineering duty!			
		
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

	public boolean isAdminRights() {
		return adminRights;
	}

	public void setAdminRights(boolean adminRights) {
		this.adminRights = adminRights;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}	


	@Override
	public boolean equals(Object obj) {
		if (obj==null)return false;
		if (obj==this)return true;
		if (obj.getClass()!= this.getClass())return false;
		User user=(User)obj;
		return login.equals(user.getLogin());
	}
	
	@Override
	public int hashCode() {
		if(login==null) return 0;
		return login.hashCode();
	}

	@Override
	public String toString() {
		return login;
	}
}
