package carShop.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Options {
				
	private boolean conditioner;

	private boolean hydroamplifier;

	private boolean automaticTransmission;
		
	public Options() {/*NOP*/}
		
	public Options(boolean conditioner, boolean hydroamplifier, boolean automaticTransmission) {
		this.conditioner = conditioner;
		this.hydroamplifier = hydroamplifier;
		this.automaticTransmission = automaticTransmission;
	}

	public boolean isConditioner() {
		return conditioner;
	}
	public void setConditioner(boolean conditioner) {
		this.conditioner = conditioner;
	}
	public boolean isHydroamplifier() {
		return hydroamplifier;
	}
	public void setHydroamplifier(boolean hydroamplifier) {
		this.hydroamplifier = hydroamplifier;
	}
	public boolean isAutomaticTransmission() {
		return automaticTransmission;
	}
	public void setAutomaticTransmission(boolean automaticTransmission) {
		this.automaticTransmission = automaticTransmission;
	}

	@Override
	public String toString() {
		String result = "";
		if(isConditioner())result+="Conditioner ";
		if(isHydroamplifier())result+="Hydroamplifier ";
		if(isAutomaticTransmission())result+="Automatic transmission ";
		return result;
	}
}
