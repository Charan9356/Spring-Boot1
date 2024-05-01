package com.jsp.project_basics2.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.project_basics2.entity.Student;
import com.jsp.project_basics2.repo.StudentRepo;

@Repository
public class StudentDao {
	
	@Autowired
	private StudentRepo repo;
	
	public Student saveStudent(Student student) {
		return repo.save(student);
	}
	public Student fetchByID(int id) {
		Optional<Student> db = repo.findById(id);
		if(db.isPresent()) {
			return db.get();
		}
		else {
			return null;
		}
	}
	public Student deleteStudentByID(int id) {
		Optional<Student> db = repo.findById(id);
		if(db.isPresent()) {
			repo.deleteById(id);
			return db.get();
		}
		else {
			return null;
		}
	}
	public Student updateStudent(Student student) {
		Optional<Student> db = repo.findById(student.getId());
		if(db.isPresent()) {
			Student s = db.get();
			if(student.getName()==null) {
				student.setName(s.getName());
			}
			if(student.getEmail()==null) {
				student.setEmail(s.getEmail());
			}
			if(student.getPwd()==null) {
				student.setPwd(s.getPwd());
			}
			if(student.getAddress()==null) {
				student.setAddress(s.getAddress());
			}
			if(student.getGender()==null) {
				student.setGender(s.getGender());
			}
			if(student.getPhone()==0) {
				student.setPhone(s.getPhone());
			}
			return repo.save(student);
		}
		else {
			return null;
		}
	}
}
