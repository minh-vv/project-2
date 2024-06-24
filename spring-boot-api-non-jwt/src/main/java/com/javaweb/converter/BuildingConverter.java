package com.javaweb.converter;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.DTO.response.BuildingReponseDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Component

public class BuildingConverter {
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private RentAreaRepository rentAreaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public BuildingReponseDTO toBuildingReponseDTO(BuildingEntity it) {
		BuildingReponseDTO buildingReponseDTO = modelMapper.map(it,BuildingReponseDTO.class);

		buildingReponseDTO.setAddress(it.getStreet()+ "," + it.getWard()+ "," + districtRepository.findDistrict(it.getDistrictId()).getName());
		
		buildingReponseDTO.setRentArea(rentAreaRepository.findRentArea(it.getId()).stream().
				map(i -> i.getValue().toString() ).collect(Collectors.joining(" , "))  );
		
		return buildingReponseDTO;
	}
}
