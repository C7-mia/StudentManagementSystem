package sms.app;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import sms.ui.MainDashboard;

public class AppLauncher {
    public static void main(String[] args) {
        // Enforce smooth operating system visual patterns
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            new MainDashboard().setVisible(true);
        });
    }
}