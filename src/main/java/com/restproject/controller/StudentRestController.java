package com.restproject.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restproject.exception.StudentNotFoundException;
import com.restproject.model.Student;
import com.restproject.model.StudentErrorResponse;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	@GetMapping("/students")
	public List<Student> getStudents(){
		List<Student> theStudents = new ArrayList<>();
		theStudents.add(new Student("Mrinal", "Shubham"));
		theStudents.add(new Student("Ankit", "Kumar"));
		theStudents.add(new Student("Aman", "Sinha"));
		return theStudents ;
	}
	
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) throws StudentNotFoundException {
		List<Student> theStudents = new ArrayList<>();
		theStudents.add(new Student("Mrinal", "Shubham"));
		theStudents.add(new Student("Ankit", "Kumar"));
		theStudents.add(new Student("Aman", "Sinha"));
		if(studentId>= theStudents.size() || studentId<0 ) {
			throw new StudentNotFoundException("Student with the id: "+studentId+" does not exist");
		}
		return theStudents .get(studentId);
	}
	
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException ex){
		StudentErrorResponse error = new StudentErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(ex.getMessage());
		error.setTimeStamp(LocalDateTime.now());
		return new ResponseEntity<StudentErrorResponse>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(Exception ex){
		StudentErrorResponse error = new StudentErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		error.setTimeStamp(LocalDateTime.now());
		return new ResponseEntity<StudentErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}

}
