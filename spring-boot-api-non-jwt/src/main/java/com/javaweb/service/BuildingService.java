package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.DTO.response.BuildingReponseDTO;

public interface BuildingService {
	List <BuildingReponseDTO> findAll(Map<Object,Object> params, List<String> typeCode);
}
