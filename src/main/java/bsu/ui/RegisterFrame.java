package bsu.ui;
import bsu.controller.AuthController;
import javax.swing.*; import java.awt.*;
public class RegisterFrame extends JFrame {
    public RegisterFrame(AuthController auth){
        setTitle("Регистрация"); setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,180); setLocationRelativeTo(null);
        JTextField uF=new JTextField(12); JPasswordField pF=new JPasswordField(12);
        JButton bReg=new JButton("Зарегистрироваться"), bBack=new JButton("Назад");
        bReg.addActionListener(e->{
            String u=uF.getText(), p=new String(pF.getPassword());
            if(auth.register(u,p)) {
                JOptionPane.showMessageDialog(this,"Регистрация прошла успешно.");
                new LoginFrame(auth).setVisible(true); dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Имя занято или некорректно.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });
        bBack.addActionListener(e->{
            new LoginFrame(auth).setVisible(true); dispose();
        });
        JPanel p=new JPanel(new GridLayout(3,2,5,5));
        p.add(new JLabel("Логин:")); p.add(uF);
        p.add(new JLabel("Пароль:")); p.add(pF);
        p.add(bReg); p.add(bBack);
        add(p);
    }
}