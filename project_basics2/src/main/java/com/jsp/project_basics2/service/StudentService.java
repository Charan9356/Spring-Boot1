package com.jsp.project_basics2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.jsp.project_basics2.dao.StudentDao;
import com.jsp.project_basics2.entity.Student;
import com.jsp.project_basics2.exception.StudentNotFound;
import com.jsp.project_basics2.repo.StudentRepo;
import com.jsp.project_basics2.util.ResponseStructure;

@Service
public class StudentService {
	
	@Autowired
	private StudentDao dao;
	
	@Autowired
	private StudentRepo repo;
	
	
	
	public ResponseEntity<ResponseStructure<Student>> saveStudent(Student student){
		ResponseStructure<Student> s = new ResponseStructure<Student>();
		s.setData(dao.saveStudent(student));
		s.setMsg("Student saved successfully");
		s.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Student>>(s,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Student>> fetchByID(int id){
		Student db = dao.fetchByID(id);
		if(db!=null) {
		ResponseStructure<Student> s = new ResponseStructure<Student>();
		s.setData(db);
		s.setMsg("Student found successfully");
		s.setStatus(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<Student>>(s,HttpStatus.FOUND);
		}
		else {
			throw new StudentNotFound("student not found for your search:"+id);
		}
	}
	public ResponseEntity<ResponseStructure<Student>> deleteStudent(int id){
		Student db = dao.deleteStudentByID(id);
		if(db!=null) {
			ResponseStructure<Student> s = new ResponseStructure<Student>();
			s.setData(db);
			s.setMsg("deleted");
			s.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Student>>(s,HttpStatus.FOUND);
		}
		else {
			throw new StudentNotFound("student not found for your search:"+id);
		}
	}
	public ResponseEntity<ResponseStructure<Student>> updateStudent(Student student){
		Student db = dao.fetchByID(student.getId());
		if(db!=null) {
			ResponseStructure<Student> s = new ResponseStructure<Student>();
			s.setData(dao.updateStudent(student));
			s.setMsg("updated");
			s.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Student>>(s,HttpStatus.FOUND);
		}
		else {
			throw new StudentNotFound("student not found for your search:"+student.getId());
		}
	}
	public ResponseEntity<ResponseStructure<Student>> loginStudent(String email,String pwd){
		try {
			Student db = repo.fetchEmail(email);
				if(db.getPwd().equals(pwd)) {
					ResponseStructure<Student> s = new ResponseStructure<Student>();
					s.setData(db);
					s.setMsg("login success");
					s.setStatus(HttpStatus.OK.value());
					return new ResponseEntity<ResponseStructure<Student>>(s,HttpStatus.OK);
				}
				else {
					ResponseStructure<Student> s = new ResponseStructure<Student>();
					s.setMsg("password wrong");
					s.setStatus(HttpStatus.OK.value());
					return new ResponseEntity<ResponseStructure<Student>>(s,HttpStatus.OK);
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ResponseStructure<Student> s = new ResponseStructure<Student>();
			s.setMsg("email wrong");
			s.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Student>>(s,HttpStatus.OK);
		}
	}
	
	@Autowired
	private JavaMailSenderImpl javaMailSenderImpl;
	
	public ResponseEntity<ResponseStructure<Student>> sendSimpleMail(Student student) {
		ResponseStructure<Student> s = new ResponseStructure<Student>();
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("sakhamuricharanraju@gmail.com");
		simpleMailMessage.setTo(student.getEmail());
		simpleMailMessage.setText("Thank you for your registration details are mentioned below \n"+student);
		simpleMailMessage.setSubject("successfully registered");
		s.setData(student);
		s.setMsg("mail sent");
		s.setStatus(HttpStatus.ACCEPTED.value());
		javaMailSenderImpl.send(simpleMailMessage);
		return new ResponseEntity<ResponseStructure<Student>>(s,HttpStatus.ACCEPTED);
	}
}
