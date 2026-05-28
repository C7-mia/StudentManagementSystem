package sms.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import sms.model.Student;

public class StudentTablePanel extends JPanel {
    private final DefaultTableModel tableModel;

    public StudentTablePanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Registered Students Records"));

        String[] columns = {"Student ID", "Full Name", "Email", "Enrolled Courses", "Grades"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void refreshTable(List<Student> students) {
        tableModel.setRowCount(0); // Clear current view
        for (Student s : students) {
            tableModel.addRow(new Object[]{
                s.getStudentId(),
                s.getFullName(),
                s.getEmail(),
                s.getCoursesString(),
                s.getGradesString()
            });
        }
    }
}