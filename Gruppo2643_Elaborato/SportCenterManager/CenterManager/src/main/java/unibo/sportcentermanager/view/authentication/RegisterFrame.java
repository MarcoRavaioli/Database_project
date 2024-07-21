package unibo.sportcentermanager.view.authentication;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import unibo.sportcentermanager.service.MembroService.UserAlreadyExistsException;
import unibo.sportcentermanager.view.detailframes.InstructorQuestionFrame;

@Component
public class RegisterFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private final JTextField nomeField;
    private final JTextField cognomeField;
    private final JTextField dataField;
    private final JTextField emailField;
    private final JPasswordField passwordField;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private AuthenticationController authenticationController;

    public RegisterFrame() {
        setTitle("Registrazione");
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        final JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        add(panel);

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        final JButton backButton = new JButton("Indietro");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(backButton, gbc);

        final JLabel titleLabel = new JLabel("Registrazione");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(titleLabel, gbc);

        final JLabel nomeLabel = new JLabel("Nome:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(nomeLabel, gbc);

        nomeField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(nomeField, gbc);

        final JLabel cognomeLabel = new JLabel("Cognome:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(cognomeLabel, gbc);

        cognomeField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(cognomeField, gbc);

        final JLabel dataNascitaLabel = new JLabel("Data di Nascita:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(dataNascitaLabel, gbc);

        dataField = new JTextField("yyyy-mm-dd", 20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        dataField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (dataField.getText().equals("yyyy-mm-dd")) {
                    dataField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (dataField.getText().isEmpty()) {
                    dataField.setText("yyyy-mm-dd");
                }
            }
        });
        panel.add(dataField, gbc);

        final JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(emailLabel, gbc);

        emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(emailField, gbc);

        final JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(passwordField, gbc);

        final JButton registerButton = new JButton("Registrati");
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(registerButton, gbc);

        registerButton.addActionListener(e -> registerUser());

        backButton.addActionListener(e -> openRoleSelectionFrame());
    }

    private void registerUser() {
        final String nome = nomeField.getText();
        final String cognome = cognomeField.getText();
        final String email = emailField.getText();
        final String password = new String(passwordField.getPassword());
        final String dataNascitaStr = dataField.getText();
        try {
            Date dataNascita = new SimpleDateFormat("yyyy-MM-dd").parse(dataNascitaStr);
            final Membro membro = new Membro(nome, cognome, dataNascita, email, null, password);
            authenticationController.registerNewUser(membro);
            JOptionPane.showMessageDialog(null, "Registrazione completata!");
            dispose();
            final InstructorQuestionFrame instructorQFrame = context.getBean(InstructorQuestionFrame.class);
            instructorQFrame.setVisible(true);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Errore nel formato della data. Usa il formato yyyy-mm-dd.");
        } catch (UserAlreadyExistsException ex) {
            JOptionPane.showMessageDialog(null, "Errore: " + ex.getMessage());
        }
    }

    private void openRoleSelectionFrame() {
        dispose();
        final LoginUserFrame loginUserFrame = context.getBean(LoginUserFrame.class);
        loginUserFrame.setVisible(true);
    }
}