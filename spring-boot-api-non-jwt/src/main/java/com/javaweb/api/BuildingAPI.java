package com.javaweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.beans.BuildingBean;
import com.javaweb.beans.reponse.BuildingReponseDTO;
import com.javaweb.customexception.InvalidDataException;
import com.javaweb.service.BuildingService;

@RestController
public class BuildingAPI {
	
	@Autowired
	private BuildingService buildingService;
	
		
	@GetMapping(value = "/api/buildings")
	private  Object getBuildings(@RequestParam (name="name", required=false) String name,
								@RequestParam(name="numberOfBasement",required=false) Long numberOfBasement) {
		// DB
		List<BuildingReponseDTO> results = buildingService.findAll(name,numberOfBasement);
		return results;

	}
	
	private void validate(BuildingBean buildBuildingDTO) {
		if(buildBuildingDTO.getName() == null || buildBuildingDTO.getName().equals("") || buildBuildingDTO.getRentPrice() == null) {
			throw new InvalidDataException("name or distritctId la null");
		}
	}
	
	@PostMapping(value = "/api/buildings")
	private Object createBuildings(@RequestBody BuildingBean building) {
		validate(building);
		return building;
	}
	@DeleteMapping(value ="/api/buildings/{ids}")
	private void deleteBuildings(@PathVariable List<Long> ids) {
		System.out.print("id")	;
	}
}
