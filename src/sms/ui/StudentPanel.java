package sms.ui;

import javax.swing.*;
import java.awt.*;
import sms.model.Student;
import sms.service.AppDataBridge;
import sms.utils.DialogUtils;
import sms.utils.ValidationUtils;

public class StudentPanel extends JPanel {
    private final JTextField txtId, txtName, txtEmail;
    private final AppDataBridge dataBridge;

    public StudentPanel(AppDataBridge dataBridge) {
        this.dataBridge = dataBridge;
        setBorder(BorderFactory.createTitledBorder("Student Registration"));
        setLayout(new GridLayout(4, 2, 8, 8));

        txtId = new JTextField();
        txtName = new JTextField();
        txtEmail = new JTextField();
        JButton btnAdd = new JButton("Add Student");

        add(new JLabel("Student ID:")); add(txtId);
        add(new JLabel("Full Name:")); add(txtName);
        add(new JLabel("Email:")); add(txtEmail);
        add(new JLabel("")); add(btnAdd);

        btnAdd.addActionListener(e -> processAddStudent());
    }

    private void processAddStudent() {
        String id = txtId.getText().trim();
        String name = txtName.getText().trim();
        String email = txtEmail.getText().trim();

        if (ValidationUtils.isEmpty(id) || ValidationUtils.isEmpty(name)) {
            DialogUtils.showError(this, "ID and Name fields cannot be empty.");
            return;
        }
        if (!ValidationUtils.isValidEmail(email)) {
            DialogUtils.showError(this, "Please insert a valid email address.");
            return;
        }
        if (dataBridge.getStudentService().findStudent(id) != null) {
            DialogUtils.showError(this, "Student ID already exists.");
            return;
        }

        Student student = new Student(id, name, email);
        dataBridge.getStudentService().addStudent(student);
        DialogUtils.showSuccess(this, "Student registered successfully!");
        
        // Clean fields and refresh table view
        txtId.setText(""); txtName.setText(""); txtEmail.setText("");
        dataBridge.refreshDataView();
    }
}