package com.jsp.project_basics2.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentNotFound extends RuntimeException{
	
	private String msg = "not found";
}
