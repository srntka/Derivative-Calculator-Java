package bsu.ui;

import bsu.service.DerivativeCalculator;

import javax.swing.*;
import java.awt.*;

public class CalculatorFrame extends JFrame {
    private final JTextField inputField = new JTextField();
    private final JTextField resultField = new JTextField();

    public CalculatorFrame(String username) {
        setTitle("Калькулятор - Пользователь: " + username);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        resultField.setEditable(false);

        JLabel labelInput = new JLabel("f(x) =");
        JLabel labelResult = new JLabel("f'(x) =");
        JLabel hintLabel = new JLabel("<html><small>Поддерживается только переменная x.<br>Умножение должно быть явным (напр. 2*x).</small></html>");
        hintLabel.setForeground(Color.LIGHT_GRAY);

        JButton computeButton = new JButton("Вычислить производную");
        JButton clearButton = new JButton("Очистить");
        JButton exitButton = new JButton("Выход");

        computeButton.addActionListener(e -> {
            String input = inputField.getText().trim();

            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Введите выражение", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                String result = DerivativeCalculator.differentiate(input);
                resultField.setText(result);
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(this,
                        "Ошибка: " + ex.getMessage(),
                        "Ошибка при вычислении", JOptionPane.ERROR_MESSAGE);
            }
        });

        clearButton.addActionListener(e -> {
            inputField.setText("");
            resultField.setText("");
        });

        exitButton.addActionListener(e -> System.exit(0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        add(labelInput, gbc);
        gbc.gridx = 1;
        add(inputField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(labelResult, gbc);
        gbc.gridx = 1;
        add(resultField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        add(hintLabel, gbc);

        gbc.gridy = 3;
        add(computeButton, gbc);
        gbc.gridy = 4;
        add(clearButton, gbc);
        gbc.gridy = 5;
        add(exitButton, gbc);
    }
}