package io.nagender.config;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


public class StudentRowMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student student = new Student();
		student.setStudentid(rs.getInt("studentid"));
		student.setFirstName(rs.getString("FirstName"));
		student.setLastName(rs.getString("LastName"));
		student.setBranch(rs.getString("Branch"));
		student.setEmail(rs.getString("Email"));
		student.setPhoneNumber(rs.getString("phonenumber"));
		return student;
	}
		
}
