package com.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.DTO.response.BuildingReponseDTO;
import com.javaweb.service.BuildingService;

@RestController
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;

    @GetMapping(value = "/api/buildings")
    private Object getBuildings(@RequestParam(required = false) Map<Object, Object> params,
                                @RequestParam(name = "typeCode", required = false) List<String> typeCode) {
        List<BuildingReponseDTO> results = buildingService.findAll(params, typeCode);
        return results;
    }
}
