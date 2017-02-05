package edu.ilstu.app;

import java.util.ArrayList;
import java.util.List;

public class StudentInformation {
	/*
	 * Student Information
	 */
	private String id;
	private String firstName;
	private String lastName;
	private List<Assignment> assignmentList;
	private String total;
	private String letterGrade;

	/*
	 * Metadata
	 */
	private String semester;
	private String year;
	private String course;

	public StudentInformation() {
		assignmentList = new ArrayList<Assignment>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Assignment> getAssignmentList() {
		return assignmentList;
	}

	public void setAssignmentList(List<Assignment> assignmentList) {
		this.assignmentList = assignmentList;
	}
	
	public void addAssignment(Assignment assignment){
		this.assignmentList.add(assignment);
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getLetterGrade() {
		return letterGrade;
	}

	public void setLetterGrade(String letterGrade) {
		this.letterGrade = letterGrade;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

}