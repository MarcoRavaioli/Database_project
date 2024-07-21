package unibo.sportcentermanager.view.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class RoleSelectionFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    @Autowired
    private ApplicationContext context;

    public RoleSelectionFrame() {
        setTitle("Seleziona Ruolo");
        setSize(300, 150);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        final JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(final JPanel panel) {
        panel.setLayout(null);

        final JLabel userLabel = new JLabel("Seleziona Ruolo");
        userLabel.setBounds(100, 10, 100, 25);
        panel.add(userLabel);

        final JButton userButton = new JButton("Membro");
        userButton.setBounds(50, 50, 80, 25);
        panel.add(userButton);

        final JButton adminButton = new JButton("Admin");
        adminButton.setBounds(150, 50, 80, 25);
        panel.add(adminButton);

        userButton.addActionListener(e -> openLoginUserFrame());

        adminButton.addActionListener(e -> openLoginAdminFrame());
    }

    private void openLoginUserFrame() {
        dispose();
        final LoginUserFrame loginUserFrame = context.getBean(LoginUserFrame.class);
        loginUserFrame.setVisible(true);
    }

    private void openLoginAdminFrame() {
        dispose();
        final LoginAdminFrame loginAdminFrame = context.getBean(LoginAdminFrame.class);
        loginAdminFrame.setVisible(true);
    }
}
