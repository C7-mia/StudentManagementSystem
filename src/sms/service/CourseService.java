package sms.service;

import sms.model.Course;
import java.util.ArrayList;
import java.util.List;

public class CourseService {
    private final List<Course> courses = new ArrayList<>();

    public CourseService() {
        // Pre-populate core catalog
        courses.add(new Course("CSC101", "Introduction to Programming"));
        courses.add(new Course("MTH121", "Calculus"));
        courses.add(new Course("BUS110", "Business Communication"));
    }

    public List<Course> getCourses() {
        return courses;
    }
}