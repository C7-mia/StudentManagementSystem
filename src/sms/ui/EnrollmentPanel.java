package sms.ui;

import javax.swing.*;
import java.awt.*;
import sms.model.Course;
import sms.service.AppDataBridge;
import sms.utils.DialogUtils;
import sms.utils.ValidationUtils;

public class EnrollmentPanel extends JPanel {
    private final JTextField txtStudentId;
    private final JComboBox<Course> comboCourses;
    private final AppDataBridge dataBridge;

    public EnrollmentPanel(AppDataBridge dataBridge) {
        this.dataBridge = dataBridge;
        
        setBorder(BorderFactory.createTitledBorder("Course Enrollment"));
        setLayout(new GridLayout(3, 2, 8, 8));

        // 1. Initialize components first
        txtStudentId = new JTextField();
        comboCourses = new JComboBox<>();
        JButton btnEnroll = new JButton("Enroll Student");
        
        // 2. Populate the ComboBox
        if (dataBridge != null && dataBridge.getCourseService() != null) {
            for (Course c : dataBridge.getCourseService().getCourses()) {
                comboCourses.addItem(c);
            }
        }

        // 3. Add components to the layout safely
        add(new JLabel("Student ID:")); 
        add(txtStudentId);
        
        add(new JLabel("Select Course:")); 
        add(comboCourses);
        
        add(new JLabel("")); 
        add(btnEnroll);

        // 4. Attach the listener
        btnEnroll.addActionListener(e -> processEnrollment());
    }

    private void processEnrollment() {
        String id = txtStudentId.getText().trim();
        Course course = (Course) comboCourses.getSelectedItem();

        if (ValidationUtils.isEmpty(id)) {
            DialogUtils.showError(this, "Please enter a valid Student ID.");
            return;
        }
        
        sms.model.Student student = dataBridge.getStudentService().findStudent(id);
        if (student == null) {
            DialogUtils.showError(this, "Student not found in system.");
            return;
        }

        if (course != null) {
            student.enrollCourse(course.getCourseCode());
            DialogUtils.showSuccess(this, "Enrolled successfully in " + course.getCourseCode());
            txtStudentId.setText("");
            dataBridge.refreshDataView();
        }
    }
}