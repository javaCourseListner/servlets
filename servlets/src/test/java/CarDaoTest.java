
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import carShop.DAO.CarDao;
import carShop.entities.Car;
import carShop.entities.Options;




public class CarDaoTest {
	
	private CarDao carDao =new CarDao();
	
	@Test
	public void getDelCarsByModelTest(){
		carDao.setCar(new Car("modelForTesting","red",new Options(true,true,false),666,"nice car"));	
		carDao.setCar(new Car("modelForTesting","red",new Options(true,true,false),666,"nice car"));
		List<Car> car = carDao.getCarsByModel("modelForTesting");
		Assert.assertTrue(car.size()==2);
		carDao.deleteCarsByModel("modelForTesting");
		car = carDao.getCarsByModel("modelForTesting");
		Assert.assertTrue(car.size()==0);
	}

	@Test
	public void setCarPrice(){
		try{
			carDao.setCar(new Car("modelForTesting","red",new Options(true,true,false),666,"nice car"));
			List<Car> cars = carDao.getCarsByModel("modelForTesting");
			Car car = cars.get(0);
			Assert.assertTrue(car.getPrice()== 666);
			int id=car.getCarId();
			carDao.setNewPrice(id,888);
			cars = carDao.getCarsByModel("modelForTesting");
			car = cars.get(0);
			Assert.assertTrue(car.getPrice()== 888);
		}finally{
			carDao.deleteCarsByModel("modelForTesting");
		}
	}
	
	@Test
	public void setCarDescription(){
		try{
			carDao.setCar(new Car("modelForTesting","red",new Options(true,true,false),666,"nice car"));
			List<Car> cars = carDao.getCarsByModel("modelForTesting");
			Car car = cars.get(0);
			Assert.assertTrue(car.getDescription().equals("nice car"));
			int id=car.getCarId();
			carDao.setNewDescription(id, "bad car");
			cars = carDao.getCarsByModel("modelForTesting");
			car = cars.get(0);
			Assert.assertTrue(car.getDescription().equals("bad car"));
		}finally{
			carDao.deleteCarsByModel("modelForTesting");
		}
	}

}
