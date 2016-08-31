package SearchForCarShowroom.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "`Auto Factory`")
public class AutomobileManufacturingPlantAdditionalTable implements Serializable {
//	@Id
//	@Column(name = "ID")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
	@Id
	@ManyToOne
	private Automobile auto;
	@Id
    @ManyToOne
	private ManufacturingPlant factory;
	
	//-----------------------------------Constructors
	public AutomobileManufacturingPlantAdditionalTable(){
		
	}

	public AutomobileManufacturingPlantAdditionalTable(Automobile auto, ManufacturingPlant factory) {
		this.auto = auto;
		this.factory = factory;
	}

	//-----------------------------------Getters and Setters
	public Automobile getAuto() {
		return auto;
	}

	public void setAuto(Automobile auto) {
		this.auto = auto;
	}

	public ManufacturingPlant getFactory() {
		return factory;
	}

	public void setFactory(ManufacturingPlant factory) {
		this.factory = factory;
	}

	 @Override
	    public boolean equals(Object o) {
	        if ( this == o ) {
	            return true;
	        }
	        if ( o == null || getClass() != o.getClass() ) {
	            return false;
	        }
	        AutomobileManufacturingPlantAdditionalTable that = (AutomobileManufacturingPlantAdditionalTable) o;
	        return Objects.equals( auto, that.auto ) &&
	                Objects.equals( factory, that.factory );
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash( auto, factory );
	    }
	
}
