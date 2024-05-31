package com.javaweb.beans;

import java.util.List;

public class BuildingBean {
	private String name;
	private Long DistrictId;
	private String ward;
	private String street;
	private Long rentPrice;
	private List<String> typeCode;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getDistrictId() {
		return DistrictId;
	}
	public void setDistrictId(Long districtId) {
		DistrictId = districtId;
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
	public Long getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(Long rentPrice) {
		this.rentPrice = rentPrice;
	}
	public List<String> getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(List<String> typeCode) {
		this.typeCode = typeCode;
	}
	
	
}