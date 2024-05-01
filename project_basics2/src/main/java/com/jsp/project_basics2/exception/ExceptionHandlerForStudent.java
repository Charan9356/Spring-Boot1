package com.jsp.project_basics2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.project_basics2.util.ResponseStructure;

@RestControllerAdvice
public class ExceptionHandlerForStudent {
	
	@ExceptionHandler(StudentNotFound.class)
	public ResponseEntity<ResponseStructure<String>> studentNotFound(StudentNotFound snf){
		ResponseStructure<String> s = new ResponseStructure<String>();
		s.setData("no student for given id");
		s.setMsg(snf.getMsg());
		s.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(s,HttpStatus.NOT_FOUND);
	}
}
