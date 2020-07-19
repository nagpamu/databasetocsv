package io.nagender.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import io.nagender.config.Student;

public class StudentProcessor implements ItemProcessor<Student,Student> {

	@Override
	public Student process(Student student) throws Exception {
		// TODO Auto-generated method stub
		return student;
	}
	
	

}
