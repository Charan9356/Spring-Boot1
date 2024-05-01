package com.jsp.project_basics2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.project_basics2.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer>{
	
	@Query("select a from Student a where a.email=?1")
	public abstract Student fetchEmail(String email) throws Exception;
}
