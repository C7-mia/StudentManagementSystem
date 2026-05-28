package sms.utils;

import javax.swing.JOptionPane;
import java.awt.Component;

public class DialogUtils {
    public static void showError(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void showSuccess(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}