package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUtil;

@Component // de su dung converter
public class BuildingSearchBuilderConverter {
	public BuildingSearchBuilder toBuildingSearchBuilder(Map<Object,Object> params, List<String> typeCode) {
		BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder()
				.setName(MapUtil.getObject(params, "name", String.class))
				.setFloorArea(MapUtil.getObject(params, "floorArea", Long.class))
				.setWard(MapUtil.getObject(params, "ward", String.class))
				.setStreet(MapUtil.getObject(params, "street", String.class))
				.setDistrictId(MapUtil.getObject(params, "districtId", Long.class))
				.setNumberOfBasement(MapUtil.getObject(params, "numberOfBasement", Long.class))
				.setTypeCode(typeCode)
				.setManagerName(MapUtil.getObject(params, "managerName", String.class))
				.setManagerPhoneNumber(MapUtil.getObject(params, "managerPhoneNumber", String.class))
				.setRentPriceFrom(MapUtil.getObject(params, "rentPriceFrom", Long.class))
				.setRentPriceTo(MapUtil.getObject(params, "rentPriceTo", Long.class))
				.setRentAreaFrom(MapUtil.getObject(params, "rentAreaFrom", Long.class))
				.setRentAreaTo(MapUtil.getObject(params, "rentAreaTo", Long.class))
				.setStaffId(MapUtil.getObject(params, "staffId", Long.class))
				.setLevel(MapUtil.getObject(params, "level", Long.class))
				.build();
		return builder;
	}
}
