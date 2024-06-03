package com.javaweb.DTO.response;

public class BuildingReponseDTO {

	private String name;
	
	private String address;
	
	private Long numberOfBasement;
	
	private String managerName;
	private String managerPhoneNumber;
	
	private Long floorArea;
	private Long emptyArea;
	private String RentArea;
	
	private Long rentPrice;
	private Long brokerageFees;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public Long getEmptyArea() {
		return emptyArea;
	}
	public void setEmptyArea(Long emptyArea) {
		this.emptyArea = emptyArea;
	}
	public String getRentArea() {
		return RentArea;
	}
	public void setRentArea(String rentArea) {
		RentArea = rentArea;
	}
	public Long getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(Long rentPrice) {
		this.rentPrice = rentPrice;
	}
	public Long getBrokerageFees() {
		return brokerageFees;
	}
	public void setBrokerageFees(Long brokerageFees) {
		this.brokerageFees = brokerageFees;
	}
	
}

	

