
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import carShop.DAO.UserDao;
import carShop.entities.User;

public class UserDaoTest {

	private static UserDao userDao = new UserDao();
	private static String login1 = "testUser1";
	private static String login2 = "testUser2";
	
	
	@Test	
	public void createAdmin(){
		
		userDao.deleteUser("kiki");	
		User usTo = new User("kiki", getHash("kiki"), null);
		usTo.setAdminRights(true);
		userDao.setUser(usTo);
	}
	
	
	@Test
	public void ValidTestTrue(){
		User usTo = new User(login1, null, null);
		userDao.setUser(usTo);
		userDao.setValidTrue(login1);
		User usFrom = userDao.getUserById(login1);
		Assert.assertTrue(usFrom.isValid());
		userDao.deleteUser(login1);			
	}

	@Test
	public void ValidTestFalse(){
		User usTo = new User(login1, null, null);
		usTo.setValid(true);
		userDao.setUser(usTo);
		userDao.setValidFalse(login1);
		User usFrom = userDao.getUserById(login1);
		Assert.assertFalse(usFrom.isValid());
		userDao.deleteUser(login1);				
	}

	@Test
	public void setAdminRights(){
		User usTo = new User(login1,null, null);
		userDao.setUser(usTo);
		userDao.setAdminRights(login1);
		User usFrom = userDao.getUserById(login1);
		Assert.assertTrue(usFrom.isAdminRights());
		userDao.deleteUser(login1);			
	}

	@Test
	public void removeAdminRights(){
		User usTo = new User(login1,null, null);
		usTo.setAdminRights(true);
		userDao.setUser(usTo);
		userDao.removeAdminRights(login1);
		User usFrom = userDao.getUserById(login1);
		Assert.assertFalse(usFrom.isAdminRights());
		userDao.deleteUser(login1);				
	}

	
	@Test
	public void getInvalidUsers(){
		User user1 = new User(login1, null, null);
		User user2 = new User(login2, null, null);
		user1.setValid(true);		
		userDao.setUser(user1);
		userDao.setUser(user2);		
		List<User> uslist = userDao.getInvalidUsers();		
		Assert.assertFalse(uslist.contains(user1));
		Assert.assertTrue(uslist.contains(user2));		
		userDao.deleteUser(login1);
		userDao.deleteUser(login2);
	}

	@Test
	public void getAdminUsers(){	
		User user1 = new User(login1, null, null);
		User user2 = new User(login2, null, null);
		user2.setAdminRights(true);		
		userDao.setUser(user1);
		userDao.setUser(user2);		
		List<User> uslist = userDao.getAdminUsers();	
		Assert.assertFalse(uslist.contains(user1));
		Assert.assertTrue(uslist.contains(user2));		
		userDao.deleteUser(login1);
		userDao.deleteUser(login2);
	}
	
	private String getHash(String str) {	        
		if (str == null) return null;
		MessageDigest md5 ;        
	    StringBuffer  hexString = new StringBuffer();	        
	    try {	                                    
	    	md5 = MessageDigest.getInstance("md5");	            
	    	md5.reset();
	    	md5.update(str.getBytes()); 	                        	                        
	    	byte messageDigest[] = md5.digest();	                        
	    	for (int i = 0; i < messageDigest.length; i++) {
	    		hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
	    	}	                                                                                        
	    }catch (NoSuchAlgorithmException e) {                        
	    	e.printStackTrace();
	    }	        
	    return hexString.toString();
	}
}
