package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "building")
public class BuildingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;	
	
	@Column(name = "ward")
	private String ward;
	
	@Column(name = "street")
	private String street;
	
//	@Column(name = "districtid")
//	private Long districtId;
	@ManyToOne
	@JoinColumn(name = "districtid")
	private DistrictEntity district;
	
	@OneToMany(mappedBy = "buildingEntity", fetch = FetchType.LAZY)
	private List<RentAreaEntity> rentAreas = new ArrayList<RentAreaEntity>();
	
	@Column(name = "numberofbasement")
	private Long numberOfBasement;

	@Column(name = "managername")
	private String managerName;

	@Column(name = "managerphonenumber")
	private String managerPhoneNumber;

	@Column(name = "floorarea")
	private Long floorArea;

	@Column(name = "rentprice")
	private Long rentPrice;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Long getNumberOfBasement() {
		return numberOfBasement;
	}

	public void setNumberOfBasement(Long numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhoneNumber() {
		return managerPhoneNumber;
	}

	public void setManagerPhoneNumber(String managerPhoneNumber) {
		this.managerPhoneNumber = managerPhoneNumber;
	}

	public Long getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(Long floorArea) {
		this.floorArea = floorArea;
	}

	public Long getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(Long rentPrice) {
		this.rentPrice = rentPrice;
	}

	public DistrictEntity getDistrict() {
		return district;
	}

	public void setDistrict(DistrictEntity district) {
		this.district = district;
	}

	public List<RentAreaEntity> getRentAreas() {
		return rentAreas;
	}

	public void setRentAreas(List<RentAreaEntity> rentAreas) {
		this.rentAreas = rentAreas;
	}
	
	
}
