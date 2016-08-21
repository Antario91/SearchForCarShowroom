package TestProject.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
	@Column(name = "Country")
	private String county;
	
	@Column(name = "City")
	private String city;
	
	@Column(name = "Street")
	private String street;
	
	
	//-----------------------------------Constructors
	public Address(){
		
	}
	
	public Address(String county, String city, String street) {
		this.county = county;
		this.city = city;
		this.street = street;
	}

	//-----------------------------------Getters and Setters
	public String getCounty() {
		return county;
	}


	public void setCounty(String county) {
		this.county = county;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}

}
