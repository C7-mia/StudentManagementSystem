package sms.ui;

import javax.swing.*;
import java.awt.*;
import sms.service.AppDataBridge;

public class MainDashboard extends JFrame {
    private final AppDataBridge dataBridge;

    public MainDashboard() {
        this.dataBridge = new AppDataBridge();
        initializeFrame();
        initializeComponents();
    }

    private void initializeFrame() {
        setTitle("Student Management System (Java Swing)");
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void initializeComponents() {
        JLabel header = new JLabel("Academic Administration Control Deck", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 20));
        header.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(header, BorderLayout.NORTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        
        // Form operations block 
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        leftPanel.add(new StudentPanel(dataBridge));
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(new EnrollmentPanel(dataBridge));
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(new GradePanel(dataBridge));

        // Data output visualizer view
        StudentTablePanel tablePanel = new StudentTablePanel();
        dataBridge.setTablePanel(tablePanel); // Bind view to structural changes

        splitPane.setLeftComponent(new JScrollPane(leftPanel));
        splitPane.setRightComponent(tablePanel);
        splitPane.setDividerLocation(400);

        add(splitPane, BorderLayout.CENTER);
    }
}