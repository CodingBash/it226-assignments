package edu.ilstu.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentRepository {
	private List<StudentInformation> dataBank;
	private FileParse fileParse;

	public StudentRepository() {
		dataBank = new ArrayList<StudentInformation>();
		fileParse = new FileParse();
	}

	public void addData(InputObject object) {
		List<StudentInformation> retrievedData = fileParse.readData(object);
		System.out.println("Data Size: " + retrievedData.size());
		dataBank.addAll(retrievedData);
	}

	public List<StudentInformation> getStudentInformation(String id) {
		List<StudentInformation> response = new ArrayList<StudentInformation>();
		for (StudentInformation studentInformation : dataBank) {
			if (studentInformation.getId().equals(id)) {
				response.add(studentInformation);
			}
		}
		return response;
	}

	public int[] gradeSelect(InputObject inputObject) {
		Map<String, Integer> gradeCount = new HashMap<String, Integer>();
		List<StudentInformation> result = dataBank;
		if (inputObject.getSemester() != null) {
			result = getSemesterData(result, inputObject.getSemester());
		}
		if (inputObject.getYear() != null) {
			result = getYearData(result, inputObject.getYear());
		}
		if (inputObject.getCourse() != null) {
			result = getCourseData(result, inputObject.getCourse());
		}

		int aCount = 0;
		int bCount = 0;
		int cCount = 0;
		int dCount = 0;
		int fCount = 0;
		for (StudentInformation info : result) {
			if (info.getLetterGrade().toLowerCase().contains("a")) {
				aCount++;
			} else if (info.getLetterGrade().toLowerCase().contains("b")) {
				bCount++;
			} else if (info.getLetterGrade().toLowerCase().contains("c")) {
				cCount++;
			} else if (info.getLetterGrade().toLowerCase().contains("d")) {
				dCount++;
			} else if (info.getLetterGrade().toLowerCase().contains("f")) {
				fCount++;
			}
		}
		gradeCount.put("A", aCount);
		gradeCount.put("B", bCount);
		gradeCount.put("C", cCount);
		gradeCount.put("D", dCount);
		gradeCount.put("F", fCount);
		
		int[] array = new int[5];
		array[0] = gradeCount.get("A");
		array[1] = gradeCount.get("B");
		array[2] = gradeCount.get("C");
		array[3] = gradeCount.get("D");
		array[4] = gradeCount.get("F");
		
		return array;
	}

	private List<StudentInformation> getSemesterData(List<StudentInformation> data, String semester) {
		List<StudentInformation> response = new ArrayList<StudentInformation>();
		for (StudentInformation studentInformation : data) {
			if (studentInformation.getSemester().equals(semester)) {
				response.add(studentInformation);
			}
		}
		return response;
	}

	private List<StudentInformation> getYearData(List<StudentInformation> data, String year) {
		List<StudentInformation> response = new ArrayList<StudentInformation>();
		for (StudentInformation studentInformation : data) {
			if (studentInformation.getYear().equals(year)) {
				response.add(studentInformation);
			}
		}
		return response;
	}

	private List<StudentInformation> getCourseData(List<StudentInformation> data, String course) {
		List<StudentInformation> response = new ArrayList<StudentInformation>();
		for (StudentInformation studentInformation : data) {
			if (studentInformation.getCourse().equals(course)) {
				response.add(studentInformation);
			}
		}
		return response;
	}
}
