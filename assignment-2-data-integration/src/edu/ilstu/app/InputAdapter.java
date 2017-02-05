package edu.ilstu.app;

import java.util.Scanner;

public class InputAdapter {
	public Response pollInput() {
		String response = getUserResponse("Enter a request: ");
		switch (response.toLowerCase()) {
		case "a":
			return Response.ADD_DATA;
		case "s":
			return Response.SAVE_DATA;
		case "g":
			return Response.GRADE_SELECT;
		case "e":
			return Response.EXIT;
		default:
			return pollInput();

		}
	}

	public InputObject addDataObject() {
		InputObject inputObject = new InputObject();
		inputObject.setFilename(getUserResponse("Enter filename: "));
		inputObject.setSemester(getUserResponse("Enter semester: "));
		inputObject.setYear(getUserResponse("Enter year: "));
		inputObject.setCourse(getUserResponse("Enter course: "));
		return inputObject;
	}

	public String getStudentId() {
		String studentIdResponse = getUserResponse("Enter student ID: ");
		return studentIdResponse;
	}

	public InputObject getGradeSelectRequest() {
		InputObject inputObject = new InputObject();
		String semester = getUserResponse("Enter semester: ");
		String year = getUserResponse("Enter year: ");
		String course = getUserResponse("Enter course: ");
		
		if (!semester.toLowerCase().trim().equals("none")) {
			inputObject.setSemester(semester);
		}
		if (!year.toLowerCase().trim().equals("none")) {
			inputObject.setYear(year);
		}
		if (!course.toLowerCase().trim().equals("none")) {
			inputObject.setCourse(course);
		}
		return inputObject;
	}

	private String getUserResponse(String message) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.print(message);
		String input = scanner.nextLine();
		return input;
	}

	public String getFileName() {
		return getUserResponse("Enter filename: ");
	}

}
