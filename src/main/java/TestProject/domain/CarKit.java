package TestProject.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "`Car kit`")
public class CarKit implements Serializable{
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "`Window tinting`")
	private boolean windowTinting;
	
	@Column(name = "`Alloy wheels`")
	private boolean alloyWheels;
	
	@Column(name = "Immobiliser")
	private boolean immobiliser;
	
	@Column(name = "`Radio equipment`")
	private boolean radioEquipment;
	
	@Column(name = "`Cruise control`")
	private boolean cruiseControl;

	@Column(name = "Cost")
	private int cost;

	@Column(name = "Description")
	private String description;
	
	@ManyToOne
	private Automobile auto;

	@OneToMany(mappedBy = "kit", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CarKitCarShowroomAdditionalTable> kitShowrooms = new ArrayList<CarKitCarShowroomAdditionalTable>();
	
	//-----------------------------------Constructors
	public CarKit(){
		
	}

	public CarKit(boolean windowTinting, boolean alloyWheels, boolean immobiliser, boolean radioEquipment,
			boolean cruiseControl, int cost) {
		this.windowTinting = windowTinting;
		this.alloyWheels = alloyWheels;
		this.immobiliser = immobiliser;
		this.radioEquipment = radioEquipment;
		this.cruiseControl = cruiseControl;
		this.cost = cost;
		description = "windowTinting-" + windowTinting + " " +
						"alloyWheels-" + alloyWheels + " " +
						"immobiliser-" + immobiliser + " " +
						"radioEquipment-" + radioEquipment + " " +
						"cruiseControl-" + cruiseControl;
	}
	
	//-----------------------------------Getters and Setters
	public int getId() {
		return id;
	}

	public boolean isWindowTinting() {
		return windowTinting;
	}

	public void setWindowTinting(boolean windowTinting) {
		this.windowTinting = windowTinting;
		setDescription(this.windowTinting, alloyWheels, immobiliser, radioEquipment, cruiseControl);
	}

	public boolean isAlloyWheels() {
		return alloyWheels;
	}

	public void setAlloyWheels(boolean alloyWheels) {
		this.alloyWheels = alloyWheels;
		setDescription(windowTinting, this.alloyWheels, immobiliser, radioEquipment, cruiseControl);
	}

	public boolean isImmobiliser() {
		return immobiliser;
	}

	public void setImmobiliser(boolean immobiliser) {
		this.immobiliser = immobiliser;
		setDescription(windowTinting, alloyWheels, this.immobiliser, radioEquipment, cruiseControl);
	}

	public boolean isRadioEquipment() {
		return radioEquipment;
	}

	public void setRadioEquipment(boolean radioEquipment) {
		this.radioEquipment = radioEquipment;
		setDescription(windowTinting, alloyWheels, immobiliser, this.radioEquipment, cruiseControl);
	}

	public boolean isCruiseControl() {
		return cruiseControl;
	}

	public void setCruiseControl(boolean cruiseControl) {
		this.cruiseControl = cruiseControl;
		setDescription(windowTinting, alloyWheels, immobiliser, radioEquipment, this.cruiseControl);
	}
	
	public int getCost() { return cost;}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getDescription() { return  description;}

	public void setDescription (boolean windowTinting, boolean alloyWheels, boolean immobiliser, boolean radioEquipment,
								boolean cruiseControl){
		description = "windowTinting-" + windowTinting + " " +
				"alloyWheels-" + alloyWheels + " " +
				"immobiliser-" + immobiliser + " " +
				"radioEquipment-" + radioEquipment + " " +
				"cruiseControl-" + cruiseControl;
	}

	public Automobile getAutomobile(){
		return auto;
	}
	
	public void setAutomobile(Automobile auto){
		this.auto = auto;
	}

	public void addCarShowroom(CarShowroom showroom) {
		CarKitCarShowroomAdditionalTable temp = new CarKitCarShowroomAdditionalTable(this, showroom);
		kitShowrooms.add(temp);
		showroom.getAdditionalTable().add(temp);
	}

	public void removeCarShowroom(CarShowroom showroom) {
		CarKitCarShowroomAdditionalTable temp = new CarKitCarShowroomAdditionalTable(this, showroom);
		showroom.getAdditionalTable().remove(temp);
		kitShowrooms.remove(temp);
		temp.setKit(null);
		temp.setShowroom(null);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CarKit)) return false;

		CarKit carKit = (CarKit) o;

		if (isWindowTinting() != carKit.isWindowTinting()) return false;
		if (isAlloyWheels() != carKit.isAlloyWheels()) return false;
		if (isImmobiliser() != carKit.isImmobiliser()) return false;
		if (isRadioEquipment() != carKit.isRadioEquipment()) return false;
		return isCruiseControl() == carKit.isCruiseControl();

	}

	@Override
	public int hashCode() {
		return Objects.hash(windowTinting, alloyWheels, immobiliser, radioEquipment, cruiseControl);
	}

	@Override
	public String toString(){
		return "Window tinting -> " + isWindowTinting() + "\n" +
				"Alloy wheels -> " + isAlloyWheels() + "\n" +
				"Immobiliser -> " + isImmobiliser() + "\n" +
				"Radio equipment -> " + isRadioEquipment() + "\n" +
				"Cruise control -> " + isCruiseControl() + "\n" +
				"Cost -> " + getCost();
	}
	

}
