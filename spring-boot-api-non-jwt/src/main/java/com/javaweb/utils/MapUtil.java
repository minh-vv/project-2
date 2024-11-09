package com.javaweb.utils;

import java.util.Map;

public class MapUtil {
	public static <T> T getObject(Map<Object,Object> params, String key,Class<T> tClass) {
		Object obj = params.getOrDefault(key, null);
		if (obj != null) {
			if (tClass.getTypeName().equals("java.lang.Long")) {
				obj = obj != "" ? Long.valueOf(obj.toString()) : null; 
			}
			else if(tClass.getTypeName().equals("java.lang.Float")) {
				obj = obj != "" ? Float.valueOf(obj.toString()) : null;
			}
			else if(tClass.getTypeName().equals("java.lang.String")) {
				obj = obj != "" ? obj.toString() : null;
			}
			return tClass.cast(obj);
		}
		return null;
	}
}
