package TestProject.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "`Manufacturing plant`")
public class ManufacturingPlant implements Serializable {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "`Country`")
	private String country;
	
//	@ManyToMany(cascade=CascadeType.ALL)  
//    @JoinTable(name="auto_factory", joinColumns=@JoinColumn(name="factory_id"), inverseJoinColumns=@JoinColumn(name="auto_id"))  
//	private Set<Automobile> auto;
	
	
	@OneToMany(mappedBy = "factory", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AutomobileManufacturingPlantAdditionalTable> autofactory = new ArrayList<AutomobileManufacturingPlantAdditionalTable>();


	//-----------------------------------Constructors
	public ManufacturingPlant(){
		
	}

	public ManufacturingPlant(String country) {
		this.country = country;
	}


	//-----------------------------------Getters and Setters
	public int getId() {
		return id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
//	public Set<Automobile> getAuto() {
//		return auto;
//	}
//
//	public void setAuto(Set<Automobile> auto) {
//		this.auto = auto;
//	}

	
	public List<AutomobileManufacturingPlantAdditionalTable> getAdditionalTable(){
		return autofactory;
	}
	
	@Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        ManufacturingPlant factory = (ManufacturingPlant) o;
        return Objects.equals(country, factory.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country);
    }

}
