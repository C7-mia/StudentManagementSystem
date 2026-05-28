package sms.ui;

import javax.swing.*;
import java.awt.*;
import sms.model.Course;
import sms.model.Student;
import sms.service.AppDataBridge;
import sms.utils.DialogUtils;
import sms.utils.ValidationUtils;

public class GradePanel extends JPanel {
    private final JTextField txtStudentId;
    private final JComboBox<String> comboCourses;
    private final JComboBox<String> comboGrades;
    private final AppDataBridge dataBridge;

    public GradePanel(AppDataBridge dataBridge) {
        this.dataBridge = dataBridge;
        setBorder(BorderFactory.createTitledBorder("Grade Allocations"));
        setLayout(new GridLayout(4, 2, 8, 8));

        txtStudentId = new JTextField();
        comboCourses = new JComboBox<>();
        for (Course c : dataBridge.getCourseService().getCourses()) {
            comboCourses.addItem(c.getCourseCode());
        }

        comboGrades = new JComboBox<>(new String[]{"A", "B", "C", "D", "F"});
        JButton btnAssign = new JButton("Assign Grade");

        add(new JLabel("Student ID:")); add(txtStudentId);
        add(new JLabel("Course Code:")); add(comboCourses);
        add(new JLabel("Grade:")); add(comboGrades);
        add(new JLabel("")); add(btnAssign);

        btnAssign.addActionListener(e -> processGradeAllocation());
    }

    private void processGradeAllocation() {
        String id = txtStudentId.getText().trim();
        String selectedCourse = (String) comboCourses.getSelectedItem();
        String selectedGrade = (String) comboGrades.getSelectedItem();

        if (ValidationUtils.isEmpty(id)) {
            DialogUtils.showError(this, "Please check Student ID input.");
            return;
        }

        Student student = dataBridge.getStudentService().findStudent(id);
        if (student == null) {
            DialogUtils.showError(this, "No record found for ID: " + id);
            return;
        }

        if (!student.getGrades().containsKey(selectedCourse)) {
            DialogUtils.showError(this, "Student must be enrolled in " + selectedCourse + " first.");
            return;
        }

        student.assignGrade(selectedCourse, selectedGrade);
        DialogUtils.showSuccess(this, "Grade structural system updated!");
        txtStudentId.setText("");
        dataBridge.refreshDataView();
    }
}