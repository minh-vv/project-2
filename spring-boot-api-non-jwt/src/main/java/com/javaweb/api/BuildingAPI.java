package com.javaweb.api;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.DTO.response.BuildingReponseDTO;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.service.BuildingService;

@RestController
@PropertySource("classpath:application.properties")
@Transactional

public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;
    
    //@Value()
    //private String tmp; 
    
    @GetMapping(value = "/api/buildings")
    private Object getBuildings(@RequestParam(required = false) Map<Object, Object> params,
                                @RequestParam(name = "typeCode", required = false) List<String> typeCode) {
        List<BuildingReponseDTO> results = buildingService.findAll(params, typeCode);
        return results;
    }
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @PostMapping(value = "/api/buildings")
    public Object createOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO) {
    	
    	// Lam o service
    	BuildingEntity buildingEntity = new BuildingEntity();
    	
    	if (buildingDTO.getId() != null ) {
    		buildingEntity = entityManager.find(BuildingEntity.class, buildingDTO.getId());
    	}
    	buildingEntity.setName(buildingDTO.getName());
    	buildingEntity.setStreet(buildingDTO.getStreet());
    	buildingEntity.setWard(buildingDTO.getWard());
    	buildingEntity.setRentPrice(buildingDTO.getRentPrice());
    	DistrictEntity districtEntity = entityManager.find(DistrictEntity.class, buildingDTO.getDistrictId());
    	buildingEntity.setDistrict(districtEntity);
    	entityManager.merge(buildingEntity);
    	return buildingEntity;
    }
    
    @DeleteMapping(value = "/api/buildings/{ids}")
    public void deleteBuilding(@PathVariable List <Long> ids) {
    	for (Long id : ids) {
    		BuildingEntity buildingEntity = entityManager.find(BuildingEntity.class, id);
    		entityManager.remove(buildingEntity);
    		
    	}
    }
    
    
}
