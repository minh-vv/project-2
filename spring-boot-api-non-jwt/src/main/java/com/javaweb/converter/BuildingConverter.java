package com.javaweb.converter;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.DTO.response.BuildingReponseDTO;

import com.javaweb.repository.entity.BuildingEntity;

@Component

public class BuildingConverter {
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	public BuildingReponseDTO toBuildingReponseDTO(BuildingEntity it) {
		BuildingReponseDTO buildingReponseDTO = modelMapper.map(it,BuildingReponseDTO.class);

		buildingReponseDTO.setAddress(it.getStreet()+ "," + it.getWard()+ "," + it.getDistrict().getName());		
		buildingReponseDTO.setRentArea(it.getRentAreas().stream().
				map(i -> i.getValue().toString() ).collect(Collectors.joining(" , "))  );
		
		return buildingReponseDTO;
	}
}
