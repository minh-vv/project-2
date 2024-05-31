package com.javaweb.controlleradvice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.javaweb.beans.ErrorResponseBean;
import com.javaweb.customexception.InvalidDataException;


@ControllerAdvice
public class controllerAdvisor extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ArrayIndexOutOfBoundsException.class)
	public ResponseEntity<Object> handleArrayIndexOutOfBoundsException(ArrayIndexOutOfBoundsException ex){
		
		ErrorResponseBean errorResponsBean = new ErrorResponseBean();
		errorResponsBean.setError(ex.getMessage());
		List<String> details= new ArrayList<>();
		details.add("Ban dang truy cap vao phan tu khong co trong mang");
		errorResponsBean.setDetail(details);
		
		return new ResponseEntity<>(errorResponsBean, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	 
	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<Object> handleInvalidDataException(InvalidDataException ex){
			
			ErrorResponseBean errorResponsBean = new ErrorResponseBean();
			errorResponsBean.setError(ex.getMessage());
			List<String> details= new ArrayList<>();
			details.add("Thieu Data");
			errorResponsBean.setDetail(details);
			
			return new ResponseEntity<>(errorResponsBean, HttpStatus.SERVICE_UNAVAILABLE);
		}
	
}
