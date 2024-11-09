package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.DTO.response.BuildingReponseDTO;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;


@Service
public class BuildingServiceImpl implements BuildingService{
	@Autowired
	private BuildingRepository buildingRepository;

	@Autowired
	private BuildingConverter buidingConverter;
	
	@Autowired
	private BuildingSearchBuilderConverter buildingSearchBuilderConverter;
	
	@Override
	public List<BuildingReponseDTO> findAll(Map<Object,Object> params, List<String> typeCode) {
		
		//chuyen data gui ve qua builder pattern	
		BuildingSearchBuilder builder = buildingSearchBuilderConverter.toBuildingSearchBuilder(params, typeCode);
		List<BuildingReponseDTO>  results = new ArrayList<BuildingReponseDTO>();
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(builder);
		
 		for(BuildingEntity it: buildingEntities) {
			BuildingReponseDTO buildingReponseDTO = buidingConverter.toBuildingReponseDTO(it) ;
			results.add(buildingReponseDTO);
		}
        return results;
	}

}
