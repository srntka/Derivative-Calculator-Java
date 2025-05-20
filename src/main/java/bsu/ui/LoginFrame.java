package bsu.ui;
import bsu.controller.AuthController;
import javax.swing.*; import java.awt.*;

public class LoginFrame extends JFrame {
    private final AuthController auth;
    public LoginFrame(AuthController auth) {
        this.auth=auth;
        setTitle("Вход"); setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,180); setLocationRelativeTo(null);
        JLabel uL=new JLabel("Логин:"), pL=new JLabel("Пароль:");
        JTextField uF=new JTextField(12); JPasswordField pF=new JPasswordField(12);
        JButton bLogin=new JButton("Войти"), bReg=new JButton("Регистрация");
        bLogin.addActionListener(e->{
            String u=uF.getText(), p=new String(pF.getPassword());
            if(!auth.userExists(u)) {
                JOptionPane.showMessageDialog(this,
                        "Пользователь не найден. Пожалуйста, зарегистрируйтесь.",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
            else if(auth.login(u,p)) {
                new CalculatorFrame(u).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Неправильный пароль.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });
        bReg.addActionListener(e->{
            new RegisterFrame(auth).setVisible(true); dispose();
        });
        JPanel p=new JPanel(new GridLayout(3,2,5,5));
        p.add(uL); p.add(uF); p.add(pL); p.add(pF);
        p.add(bLogin); p.add(bReg);
        add(p);
    }
}