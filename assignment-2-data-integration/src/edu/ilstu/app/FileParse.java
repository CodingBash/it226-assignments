package edu.ilstu.app;

//import static csv.CSV.CSVtoArrayList;
import edu.ilstu.app.InputObject;
import edu.ilstu.app.StudentInformation;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileParse {

	public List<StudentInformation> readData(InputObject input) {
		return buffRead(input.getFilename(), input);
	}
	// String file = "380-Fall-2002";
	// String myStr = System.getProperty("user.home") + "/Documents/" + file +
	// ".csv";

	public List<StudentInformation> buffRead(String CSVFile, InputObject input) {
		BufferedReader buff = null;
		List<StudentInformation> result = new ArrayList<StudentInformation>();
		try {
			String line;
			// Not sure if there's a better way to throw in all 3
			buff = new BufferedReader(new FileReader(CSVFile));
			int row = 0;
			List<CSVMetadata> metadata = new ArrayList<CSVMetadata>();
			List<String> headers = new ArrayList<String>();
			List<List<String>> studentData = new ArrayList<List<String>>();
			while ((line = buff.readLine()) != null) {
				if (row == 0) {
					headers = readLine(line);
					// This is the header line
				} else {
					studentData.add(readLine(line));
				}
				row++;
			}

			/*
			 * Adding to metadata list
			 */
			for (String header : headers) {
				String tempHeader = header.toLowerCase();
				if (tempHeader.contains("student id") || tempHeader.contains("user id")) {
					metadata.add(CSVMetadata.ID);
				} else if (tempHeader.contains("first name")) {
					metadata.add(CSVMetadata.FIRST_NAME);
				} else if (tempHeader.contains("last name")) {
					metadata.add(CSVMetadata.LAST_NAME);
				} else if (tempHeader.contains("name")) {
					metadata.add(CSVMetadata.FULL_NAME);
				} else if (tempHeader.contains("comment")) {
					metadata.add(CSVMetadata.ASSIGNMENT_COMMENT);
				} else if (tempHeader.contains("total")) {
					metadata.add(CSVMetadata.TOTAL);
				} else if (tempHeader.contains("grade")) {
					metadata.add(CSVMetadata.LETTER_GRADE);
				} else {
					metadata.add(CSVMetadata.ASSIGNMENT_GRADE);
				}
			}

			for (int row2 = 0; row2 < studentData.size(); row2++) {
				StudentInformation information = new StudentInformation();
				information.setCourse(input.getCourse());
				information.setYear(input.getYear());
				information.setSemester(input.getSemester());
				for (int col = 0; col < studentData.get(row2).size(); col++) {
					switch (metadata.get(col)) {
					case ASSIGNMENT_COMMENT:
						Assignment assignment = new Assignment();
						assignment.setComment(studentData.get(row2).get(col));
						assignment.setGrade(studentData.get(row2).get(col - 1));
						assignment.setAssignmentName(headers.get(col - 1));
						information.addAssignment(assignment);
						break;
					case ASSIGNMENT_GRADE:
						if (!metadata.get(col + 1).equals(CSVMetadata.ASSIGNMENT_COMMENT)) {
							Assignment assignment2 = new Assignment();
							assignment2.setGrade(studentData.get(row2).get(col));
							assignment2.setAssignmentName(headers.get(col));
						}
						break;
					case FIRST_NAME:
						information.setFirstName(studentData.get(row2).get(col));
						break;
					case FULL_NAME:
						String fullName = studentData.get(row2).get(col);
						int commaIndex = fullName.indexOf(",");
						information.setFirstName(fullName.substring(0, commaIndex));
						information.setLastName(fullName.substring(commaIndex + 2));
						break;
					case ID:
						information.setId(studentData.get(row2).get(col));
						break;
					case LAST_NAME:
						information.setLastName(studentData.get(row2).get(col));
						break;
					case LETTER_GRADE:
						information.setLetterGrade(studentData.get(row2).get(col));
						break;
					case TOTAL:
						information.setTotal(studentData.get(row2).get(col));
						break;
					default:
						break;

					}
					
				}
				result.add(information);
			}

		} catch (Exception e) {
			System.err.println("BAD FILENAME");
		} finally {
			try {
				if (buff != null)
					buff.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	private List<String> readLine(String line) {
		List<String> result = new ArrayList<String>();

		if (line != null) {
			result = Arrays.asList(line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1));
		}

		return result;
	}

	/*
	 * // Converts CSV to ArrayList public List<StudentInformation>
	 * csvList(String CSV) { ArrayList<String> result = new ArrayList<String>();
	 * 
	 * if (CSV != null) { // Uses split to read CSV String[] splitData =
	 * CSV.split("\\s*,\\s*"); for (int i = 0; i < splitData.length; i++) { if
	 * (!(splitData[i] == null) || !(splitData[i].length() == 0)) {
	 * result.add(splitData[i].trim()); } } }
	 * 
	 * return result; }
	 */
}
