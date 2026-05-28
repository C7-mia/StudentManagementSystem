package sms.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Student {
    private final String studentId;
    private String fullName;
    private String email;
    private final Map<String, String> grades; // Maps CourseCode -> Grade

    public Student(String studentId, String fullName, String email) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.email = email;
        this.grades = new LinkedHashMap<>();
    }

    public String getStudentId() { return studentId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public Map<String, String> getGrades() { return grades; }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }

    public void enrollCourse(String courseCode) {
        if (!grades.containsKey(courseCode)) {
            grades.put(courseCode, "Pending");
        }
    }

    public void assignGrade(String courseCode, String grade) {
        if (grades.containsKey(courseCode)) {
            grades.put(courseCode, grade);
        }
    }

    public String getCoursesString() {
        return String.join(", ", grades.keySet());
    }

    public String getGradesString() {
        StringBuilder sb = new StringBuilder();
        grades.forEach((course, grade) -> sb.append(course).append(":").append(grade).append(" "));
        return sb.toString().trim();
    }

    @Override
    public String toString() {
        return studentId + " - " + fullName;
    }
}