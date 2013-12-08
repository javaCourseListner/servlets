package carShop.entities;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@XmlType
@XmlRootElement
public class Car {
	
	private String model; 
	
	private String color; 
	
	private List<String> options = new ArrayList<String>();
			
	public Car(){}
	
	public Car(String model,String color,String[] options){
		this.model = model;
		this.color = color;		
		if(options != null){
			for(String str : options){
			this.options.add(str);
			}
		}		
	}
		
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
	
	public void setOptions(List<String> options) {
		this.options = options;
	}
	
	public List<String> getOptions() {
		return options;
	}
	
}
