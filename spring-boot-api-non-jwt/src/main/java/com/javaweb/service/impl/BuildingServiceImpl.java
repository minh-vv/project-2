package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.DTO.response.BuildingReponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.service.BuildingService;


@Service
public class BuildingServiceImpl implements BuildingService{
	@Autowired
	private BuildingRepository buildingRepository;
	@Autowired
	private DistrictRepository districtRepository;
	@Autowired
	private RentAreaRepository rentAreaRepository;
	@Override
	public List<BuildingReponseDTO> findAll(Map<Object,Object> params, List<String> typeCode) {
		List<BuildingReponseDTO>  results = new ArrayList<BuildingReponseDTO>();
		
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(params,typeCode);
		List <RentAreaEntity> rentAreaEntities;
		
		for(BuildingEntity it: buildingEntities) {
			
			BuildingReponseDTO buildingReponseDTO = new BuildingReponseDTO();

			buildingReponseDTO.setName(it.getName());
			buildingReponseDTO.setAddress(it.getStreet()+ "," + it.getWard()+ "," + districtRepository.findDistrict(it.getDistrictId()).getName());
			
			buildingReponseDTO.setNumberOfBasement(it.getNumberOfBasement());
			
			buildingReponseDTO.setManagerName(it.getManagerName());
			buildingReponseDTO.setManagerPhoneNumber(it.getManagerPhoneNumber());
			
			buildingReponseDTO.setFloorArea(it.getFloorArea());
			buildingReponseDTO.setEmptyArea(null);
			
			rentAreaEntities = rentAreaRepository.findRentArea(it.getId());
			String rentArea ="";
			for(RentAreaEntity ra: rentAreaEntities) {
				rentArea += ra.getValue().toString() ;
				rentArea += ",";
			}
			rentArea = rentArea.substring(0,rentArea.length()-1);
			
			buildingReponseDTO.setRentArea(rentArea);
			
			buildingReponseDTO.setRentPrice(it.getRentPrice());
			
			buildingReponseDTO.setBrokerageFees(null);
			
			results.add(buildingReponseDTO);
		}
		
		
		return results;
	}

}
