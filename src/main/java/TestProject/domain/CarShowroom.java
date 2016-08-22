package TestProject.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "`Car showroom`")
public class CarShowroom {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "Name")
	private String name;
	
	@Embedded
	private Address address;

	@OneToMany(mappedBy = "showroom", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CarKitCarShowroomAdditionalTable> kitShowrooms = new ArrayList<CarKitCarShowroomAdditionalTable>();
	
	
	//-----------------------------------Constructors
	public CarShowroom(){
		
	}
	
	public CarShowroom(String name, Address address) {
		this.name = name;
		this.address = address;
	}

	//-----------------------------------Getters and Setters
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<CarKitCarShowroomAdditionalTable> getAdditionalTable(){
		return kitShowrooms;
	}

	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		CarShowroom showroom = (CarShowroom) o;
		return Objects.equals(id, showroom.id) &&
				Objects.equals(name, showroom.name)&&
				Objects.equals(address, showroom.address);
	}

	@Override
	public int hashCode() {
		return Objects.hash( id, name, address);
	}

	@Override
	public String toString() {
		return "CarShowroom{" +
				"name='" + name + '\'' +
				", address=" + address +
				'}';
	}
}
