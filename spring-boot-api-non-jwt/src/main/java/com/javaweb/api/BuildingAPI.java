package com.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.service.BuildingService;

@RestController
public class BuildingAPI {
	
	@GetMapping(value = "/api/buildings")
	private  Object getBuildings(@RequestParam (required=false) Map<String,String> params,
								@RequestParam(name="typeCode",required=false) List<String> typeCode) {
		// DB
		
		return null;

	}
	
}
