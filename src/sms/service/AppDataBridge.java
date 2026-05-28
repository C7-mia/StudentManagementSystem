package sms.service;

import sms.ui.StudentTablePanel;

public class AppDataBridge {
    private final StudentService studentService;
    private final CourseService courseService;
    private StudentTablePanel tablePanel;

    public AppDataBridge() {
        this.studentService = new StudentService();
        this.courseService = new CourseService();
    }

    public StudentService getStudentService() { return studentService; }
    public CourseService getCourseService() { return courseService; }
    
    public void setTablePanel(StudentTablePanel tablePanel) {
        this.tablePanel = tablePanel;
    }

    public void refreshDataView() {
        if (tablePanel != null) {
            tablePanel.refreshTable(studentService.getStudents());
        }
    }
}