package com.javaweb.repository;

import java.util.List;

import com.javaweb.repository.entity.RentAreaEntity;

public interface RentAreaRepository {
	public List <RentAreaEntity> findRentArea(Long buildingId);

}
