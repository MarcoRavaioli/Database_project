package unibo.sportcentermanager.view.authentication;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import unibo.sportcentermanager.controller.api.AuthenticationController;
import unibo.sportcentermanager.service.MembroService.AdminNotFoundException;
import unibo.sportcentermanager.service.MembroService.WrongPasswordException;
import unibo.sportcentermanager.view.mainframes.AdminMainFrame;

@Component
public class LoginAdminFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private final JPasswordField passwordField;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private AuthenticationController authenticationController;

    public LoginAdminFrame() {
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

        final JLabel adminLabel = new JLabel("Admin");
        adminLabel.setBounds(100, 10, 100, 25);
        adminLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(adminLabel);

        final JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        final JButton loginButton = new JButton("Login");
        loginButton.setBounds(110, 100, 80, 25);
        panel.add(loginButton);

        loginButton.addActionListener(e -> openAdminMainFrame());

        backButton.addActionListener(e -> {
            dispose();
            final RoleSelectionFrame roleSelectionFrame = context.getBean(RoleSelectionFrame.class);
            roleSelectionFrame.setVisible(true);
        });
    }

    private void openAdminMainFrame() {
        final String password = new String(passwordField.getPassword());
        try {
            authenticationController.authenticateAdmin(password);
            final AdminMainFrame adminMainFrame = context.getBean(AdminMainFrame.class);
            adminMainFrame.setVisible(true);
            dispose();
        } catch (WrongPasswordException | AdminNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Errore: " + ex.getMessage());
        }
    }
}
