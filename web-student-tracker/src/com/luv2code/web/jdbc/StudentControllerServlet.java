package com.luv2code.web.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private StudentDbUtil studentDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	
	@Override
	public void init() throws ServletException {
		
		//create student DB util
		try {
			studentDbUtil = new StudentDbUtil(dataSource);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//list the students in MVC fashion
		
		try {
			//read the "command" parameter
			String theCommand = request.getParameter("command");
			
			//if command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			//route to the appropriate method
			switch(theCommand) {
				case "LIST":
					listStudents(request, response);
					break;
					
				case "ADD":
					addStudent(request, response);
					break;
				
				case "LOAD":
					loadStudent(request, response);
					break;
				
				case "UPDATE":
					updateStudent(request, response);
					break;
					
				case "DELETE":
					deleteStudent(request, response);
					break;
					
				case "SEARCH":
					searchStudents(request, response);
					break;
				default:
					listStudents(request, response);
			}
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void searchStudents(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String theSearchName = request.getParameter("theSearchName");
		
		List<Student> students = studentDbUtil.searchStudents(theSearchName);
		
		request.setAttribute("student_list", students);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read student id from form data
		String theStudentId = request.getParameter("studentId");
		
		// delete student from database
		studentDbUtil.deleteStudent(theStudentId);
		
		// send them back to "students list" page
		listStudents(request, response);
		
		
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read student info from form data
		int id = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		// create a new Student object
		Student theStudent = new Student(id, firstName, lastName, email);
		
		// perform update on database
		studentDbUtil.updateStudent(theStudent);
		
		// send them back to the "list students" page
		listStudents(request, response);
	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read student id from form data
		String theStudentId = request.getParameter("studentId");
		
		//get the student from the db (db util)
		Student theStudent = studentDbUtil.getStudent(theStudentId);
		
		//place the stundent into request attribute
		request.setAttribute("THE_STUDENT", theStudent);
		
		// send to jsp page: update-student-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
		dispatcher.forward(request, response);
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read student info from form data
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		// create a new Student object
		Student theStudent = new Student(firstName, lastName, email);
		
		// add the student to the database
		studentDbUtil.addStudent(theStudent);
		
		
		// send back to the main page
		listStudents(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get students from DB util
		List<Student> students = studentDbUtil.getStudents();
		
		//add students to the request
		request.setAttribute("student_list", students);
		
		//send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);
		
	}

}
