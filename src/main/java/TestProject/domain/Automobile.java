package TestProject.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Automobile")
public class Automobile implements Serializable {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "Model")
	private String model;
	
	@Column(name = "`Max Power(kW)`")
	private double maxPower;
	
	@Column(name = "`Max Torque(Nm)`")
	private double maxTorque;
	
	@Column(name = "`Max Speed(km/h)`")
	private double maxSpeed;
	
	@Column(name = "`Acceleration 0-100 km/h (s)`")
	private double acceleration;
	
	@Column(name = "`Fuel consumption(l/100 km)`")
	private double fuelConsumption;
	
	@Column(name = "`Weight(kg)`")
	private double weight;
	
	@OneToMany(mappedBy = "auto", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<CarKit> carKit;
	
//	@ManyToMany(cascade=CascadeType.ALL, mappedBy="auto")
//	private Set<ManufacturingPlant> factories;
//	
	
	@OneToMany(mappedBy = "auto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AutomobileManufacturingPlantAdditionalTable> autofactory = new ArrayList<AutomobileManufacturingPlantAdditionalTable>();
	

	//-----------------------------------Constructors
	public Automobile(){
		
	}

	public Automobile(String model, double maxPower, double maxTorque, double maxSpeed, double acceleration,
			double fuelConsumption, double weight) {
		this.model = model;
		this.maxPower = maxPower;
		this.maxTorque = maxTorque;
		this.maxSpeed = maxSpeed;
		this.acceleration = acceleration;
		this.fuelConsumption = fuelConsumption;
		this.weight = weight;
	}

	//-----------------------------------Getters and Setters
	public int getId() {
		return id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getMaxPower() {
		return maxPower;
	}

	public void setMaxPower(double maxPower) {
		this.maxPower = maxPower;
	}

	public double getMaxTorque() {
		return maxTorque;
	}

	public void setMaxTorque(double maxTorque) {
		this.maxTorque = maxTorque;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public double getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}

	public double getFuelConsumption() {
		return fuelConsumption;
	}

	public void setFuelConsumption(double fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public List<CarKit> getCarKit(){
		return carKit;
	}
	
	public void setCarKit(List<CarKit> kit){
		this.carKit = kit;
	}
	
//	public Set<ManufacturingPlant> getFactories() {
//		return factories;
//	}
//
//	public void setFactories(Set<ManufacturingPlant> factories) {
//		this.factories = factories;
//	}
	
    public void addManufacturingPlant(ManufacturingPlant factory) {
    	AutomobileManufacturingPlantAdditionalTable temp = new AutomobileManufacturingPlantAdditionalTable(this, factory);
    	autofactory.add(temp);
    	factory.getAdditionalTable().add(temp);
    }
    
    public void removeManufacturingPlant(ManufacturingPlant factory) {
    	AutomobileManufacturingPlantAdditionalTable temp = new AutomobileManufacturingPlantAdditionalTable(this, factory);
    	factory.getAdditionalTable().remove(temp);
    	autofactory.remove(temp);
        temp.setAuto(null);
        temp.setFactory(null);
    }


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Automobile)) return false;

		Automobile that = (Automobile) o;

		if (Double.compare(that.getMaxPower(), getMaxPower()) != 0) return false;
		if (Double.compare(that.getMaxTorque(), getMaxTorque()) != 0) return false;
		if (Double.compare(that.getMaxSpeed(), getMaxSpeed()) != 0) return false;
		if (Double.compare(that.getAcceleration(), getAcceleration()) != 0) return false;
		if (Double.compare(that.getFuelConsumption(), getFuelConsumption()) != 0) return false;
		if (Double.compare(that.getWeight(), getWeight()) != 0) return false;
		return getModel() != null ? getModel().equals(that.getModel()) : that.getModel() == null;

	}

	@Override
	public int hashCode() {
		return Objects.hash(model, maxPower, maxTorque, maxSpeed, acceleration, fuelConsumption, weight);
	}

	@Override
	public String toString(){
		return "Model is -> " + getModel() + "\n" +
				"Max power(kW) = " + getMaxPower() + "\n" +
				"Max torque(Nm) = " + getMaxTorque() + "\n" +
				"Max speed(km/h) = " + getMaxSpeed() +  "\n" +
				"Acceleration 0-100 km/h (s) = "+ getAcceleration() + "\n" + 
				"Fuel consumption(l/100 km) = " + getFuelConsumption() +  "\n" +
				"Weight(kg) = " + getWeight();
	}


}
