package bsu.app;

import com.formdev.flatlaf.FlatDarkLaf;
import bsu.controller.AuthController;
import bsu.dao.UserDao;
import bsu.service.UserService;
import bsu.ui.LoginFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Не удалось установить FlatLaf");
            ex.printStackTrace();
        }

        AuthController authController = new AuthController(new UserService(new UserDao()));

        SwingUtilities.invokeLater(() -> {
            new LoginFrame(authController).setVisible(true);
        });
    }
}