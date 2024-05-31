package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.beans.reponse.BuildingReponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;


@Service
public class BuildingServiceImpl implements BuildingService{
	@Autowired
	private  BuildingRepository buildingRepository;
	@Override
	public List<BuildingReponseDTO> findAll(String nameBuilding, Long numberOfBasement) {
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(nameBuilding,numberOfBasement);
		List<BuildingReponseDTO>  results = new ArrayList<BuildingReponseDTO>();
		for(BuildingEntity it: buildingEntities) {
			BuildingReponseDTO buildingReponseDTO = new BuildingReponseDTO();
			buildingReponseDTO.setId(it.getId());
			buildingReponseDTO.setName(it.getName());
			buildingReponseDTO.setAddress(it.getStreet()+ "," + it.getWard()+ "," + it.getDistrictId());
			buildingReponseDTO.setRentPrice(it.getRentPrice());
			buildingReponseDTO.setNumberOfBasement(it.getNumberOfBasement());
			results.add(buildingReponseDTO);
		}
		
		
		return results;
	}

}
