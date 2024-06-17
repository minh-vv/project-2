package com.javaweb.repository;

import java.util.List;

public interface RentAreaRepository {
	public List <String> findRentArea(Long buildingId);

}
