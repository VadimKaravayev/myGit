package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.tagext.TryCatchFinally;
import javax.sql.DataSource;

public class StudentDbUtil {
	private DataSource dataSource;

	public StudentDbUtil(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	public List<Student> getStudents() throws Exception {
		List<Student> students = new ArrayList<>();
		Connection DBconnect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		
		try {
			// get a connection
			DBconnect = dataSource.getConnection();
			
			// create SQL query and initialize statement
			String sqlQuery = "SELECT * FROM student ORDER BY last_name";
			statement = DBconnect.createStatement();
			
			//execute query
			resultSet = statement.executeQuery(sqlQuery);
			
			//process result set
			while (resultSet.next()) {
				//retrieve data form result set row
				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String email = resultSet.getString("email");
				
				//create new Student object
				Student tempStudent = new Student(id, firstName, lastName, email);
				
				//add the object to the students List
				students.add(tempStudent);
			}
			
			
			return students;
			
		} finally {
			//close JDBC objects
			close(DBconnect, statement, resultSet);
		}
		
	}

	private void close(Connection DBconnect, Statement statement, ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			
			if (statement != null) {
				statement.close();
			}
			
			if (DBconnect != null) {
				DBconnect.close(); // doesn't really close it, rather puts it back in connection pool  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void addStudent(Student theStudent) throws SQLException {
		
		Connection connection = null;
		PreparedStatement prepStatement = null;
		
		try {
				
			//get DB connection
			connection = dataSource.getConnection();
			// create sql query for insert
			
			
			String sql = "INSERT INTO student VALUES(null, ?, ?, ?)";
			prepStatement = connection.prepareStatement(sql);
			
		
			
			//set the param values for the student
			prepStatement.setString(1, theStudent.getFirstName());
			prepStatement.setString(2, theStudent.getLastName());
			prepStatement.setString(3, theStudent.getEmail());
			
			//execute sql insert
			prepStatement.execute();
			
		} finally {
			//clean up JDBC objects
			close(connection, prepStatement, null);
		}
	}

	public Student getStudent(String theStudentId) throws Exception {
		Student theStudent = null;
		Connection connection = null;
		PreparedStatement prepStatement = null;
		ResultSet resultSet = null;
		int studentId;
		
		try {
			//convert student id to int
			studentId = Integer.parseInt(theStudentId);
			
			//set connection to DB
			connection = dataSource.getConnection();
			
			//create sql to get selected student
			String sql = "SELECT * FROM student WHERE id=?";
			
			//create prepared statement
			prepStatement = connection.prepareStatement(sql);
			
			//set params
			prepStatement.setInt(1, studentId);
			
			// execute statement
			resultSet = prepStatement.executeQuery();
			
			//retrieve data from result set row
			if (resultSet.next()) {
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String email = resultSet.getString("email");
				theStudent = new Student(studentId, firstName, lastName, email);
			} else {
				throw new Exception("Could not find student id: " + studentId);
			}
			return theStudent;
		} finally {
			//clean up JDBC objects
			close(connection, prepStatement, resultSet);
		}
	}

	public void updateStudent(Student theStudent) throws Exception{
		//Prepare JDBC objects and set them to null
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			//get db connection
			myConn = dataSource.getConnection();
			
			//create SQL update statement
			String sql = "UPDATE student SET first_name=?, last_name=?, email=? WHERE id=?";
			
			//prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			myStmt.setInt(4, theStudent.getId());
			
			//execute SQL statement
			myStmt.execute();
		} finally {
			//clean up JDBC objects
			close(myConn, myStmt, null);
		}
		
	}

	public void deleteStudent(String theStudentId) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert student id into int
			int studentId = Integer.parseInt(theStudentId);
			
			// get connection to DB
			myConn = dataSource.getConnection();
			
			// create SQL statement to delete student
			String sql = "DELETE FROM student WHERE id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, studentId);
			
			// execute sql statement
			myStmt.execute();
			
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
		
	}

	public List<Student> searchStudents(String theSearchName) throws SQLException {
		List<Student> students = new ArrayList<>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet resultSet = null;
		int studentId;
		
		try {
			myConn = dataSource.getConnection();
			if (theSearchName != null && theSearchName.trim().length() > 0) {
				String sql = "SELECT * FROM student WHERE LOWER(first_name) LIKE ? OR LOWER(last_name) LIKE ?";
				myStmt = myConn.prepareStatement(sql);
				String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";
				myStmt.setString(1, theSearchNameLike);
                myStmt.setString(2, theSearchNameLike);
				 
			} else {
				// create sql to get all students
                String sql = "SELECT * FROM student ORDER BY last_name";
                // create prepared statement
                myStmt = myConn.prepareStatement(sql);
			}
			
			// execute statement
			resultSet = myStmt.executeQuery();
			// retrieve data from result set row
            while (resultSet.next()) {
                
                // retrieve data from result set row
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                
                // create new student object
                Student tempStudent = new Student(id, firstName, lastName, email);
                
                // add it to the list of students
                students.add(tempStudent);            
            }
            
            return students;
			
		} finally {
			close(myConn, myStmt, resultSet);
		}
		
	}
}
