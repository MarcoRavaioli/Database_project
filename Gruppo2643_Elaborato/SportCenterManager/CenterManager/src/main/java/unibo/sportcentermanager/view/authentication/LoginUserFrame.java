package unibo.sportcentermanager.view.authentication;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import unibo.sportcentermanager.controller.api.AuthenticationController;
import unibo.sportcentermanager.entity.Membro;
import unibo.sportcentermanager.service.MembroService.UserNotFoundException;
import unibo.sportcentermanager.service.MembroService.WrongPasswordException;
import unibo.sportcentermanager.view.mainframes.UserMainFrame;

@Component
public class LoginUserFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private final JTextField emailField;
    private final JPasswordField passwordField;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private AuthenticationController authenticationController;

    public LoginUserFrame() {
        setTitle("Login");
        setSize(300, 200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        final JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        final JButton backButton = new JButton("Indietro");
        backButton.setBounds(10, 10, 80, 25);
        panel.add(backButton);

        final JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 50, 80, 25);
        panel.add(emailLabel);

        emailField = new JTextField(20);
        emailField.setBounds(100, 50, 165, 25);
        panel.add(emailField);

        final JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 80, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 80, 165, 25);
        panel.add(passwordField);

        final JButton loginButton = new JButton("Accedi");
        loginButton.setBounds(10, 110, 80, 25);
        panel.add(loginButton);

        final JButton registerButton = new JButton("Registrati");
        registerButton.setBounds(180, 110, 100, 25);
        panel.add(registerButton);

        loginButton.addActionListener(e -> loginUser());

        registerButton.addActionListener(e -> openRegisterFrame());

        backButton.addActionListener(e -> openRoleSelectionFrame());
    }

    private void loginUser() {
        final String email = emailField.getText();
        final String password = new String(passwordField.getPassword());
        try {
            final Membro membro = authenticationController.authenticateUser(email, password);
            final UserMainFrame userMainFrame = context.getBean(UserMainFrame.class);
            userMainFrame.setMembro(membro);
            System.out.println(membro.getNome());
            userMainFrame.setVisible(true);
            dispose();
        } catch (WrongPasswordException | UserNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Errore: " + ex.getMessage());
        }
    }

    private void openRegisterFrame() {
        dispose();
        final RegisterFrame registerFrame = context.getBean(RegisterFrame.class);
        registerFrame.setVisible(true);
    }

    private void openRoleSelectionFrame() {
        dispose();
        final RoleSelectionFrame roleSelectionFrame = context.getBean(RoleSelectionFrame.class);
        roleSelectionFrame.setVisible(true);
    }
}
