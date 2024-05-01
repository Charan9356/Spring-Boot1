package com.jsp.project_basics2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.project_basics2.entity.Student;
import com.jsp.project_basics2.service.StudentService;
import com.jsp.project_basics2.util.ResponseStructure;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	@PostMapping("/student")
	public ResponseEntity<ResponseStructure<Student>> saveStudent(@RequestBody Student student){
		return service.saveStudent(student);
	}
	@GetMapping("/student")
	public ResponseEntity<ResponseStructure<Student>> fetchStudentById(@RequestParam int id){
		return service.fetchByID(id);
	}
	@DeleteMapping("/student")
	public ResponseEntity<ResponseStructure<Student>> deleteStudent(@RequestParam int id){
		return service.deleteStudent(id);
	}
	@PutMapping("/student")
	public ResponseEntity<ResponseStructure<Student>> updateStudent(@RequestBody Student student){
		return service.updateStudent(student);
	}
	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<Student>> loginStudent(@RequestParam String email,@RequestParam String pwd){
		return service.loginStudent(email, pwd);
	}
	@PostMapping("/smail")
	public ResponseEntity<ResponseStructure<Student>> sendEmail(@RequestBody Student student) {
		return service.sendSimpleMail(student);
	}
}
