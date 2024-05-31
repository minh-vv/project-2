package com.javaweb.service;

import java.util.List;

import com.javaweb.beans.reponse.BuildingReponseDTO;

public interface BuildingService {
	List <BuildingReponseDTO> findAll(String nameBuilding, Long numberOfBasement);
}
