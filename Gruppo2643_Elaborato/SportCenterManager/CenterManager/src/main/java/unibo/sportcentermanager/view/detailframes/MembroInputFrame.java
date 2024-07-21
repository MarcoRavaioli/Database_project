package unibo.sportcentermanager.view.detailframes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MembroInputFrame extends JFrame {
    private final JTextField nomeField;
    private final JTextField cognomeField;
    private final JTextField dataNascitaField;
    private final JTextField emailField;
    private final JTextField documentoField;
    private final JPasswordField passwordField;
    private final JCheckBox istruttoreCheckBox;
    private boolean confirmed = false;

    public MembroInputFrame() {
        setTitle("Inserisci Nuovo Membro");
        setSize(400, 350);  // Aumenta la dimensione per fare spazio alla checkbox
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10)); // Aggiungi una riga in più per la checkbox
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        formPanel.add(nomeField);

        formPanel.add(new JLabel("Cognome:"));
        cognomeField = new JTextField();
        formPanel.add(cognomeField);

        formPanel.add(new JLabel("Data di Nascita:"));
        dataNascitaField = new JTextField("yyyy-MM-dd");
        formPanel.add(dataNascitaField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        
        formPanel.add(new JLabel("Istruttore:"));
        istruttoreCheckBox = new JCheckBox();
        formPanel.add(istruttoreCheckBox);

        /*
        formPanel.add(new JLabel("Documento:"));
        documentoField = new JTextField();
        formPanel.add(documentoField);*/

        
        final JLabel documentoLabel = new JLabel("Documento:");
        documentoLabel.setVisible(false);
        formPanel.add(documentoLabel);
        documentoField = new JTextField();
        documentoField.setVisible(false);  // Inizia nascosto
        formPanel.add(documentoField);

        istruttoreCheckBox.addActionListener((ActionEvent e) -> {
            documentoField.setVisible(istruttoreCheckBox.isSelected());
            documentoLabel.setVisible(istruttoreCheckBox.isSelected());
        });

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton confirmButton = new JButton("Conferma");
        JButton cancelButton = new JButton("Annulla");

        confirmButton.addActionListener((ActionEvent e) -> {
            confirmed = true;
            dispose();
        });

        cancelButton.addActionListener((ActionEvent e) -> {
            dispose();
        });

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String[] getValues() {
        return new String[]{
            nomeField.getText(),
            cognomeField.getText(),
            dataNascitaField.getText(),
            emailField.getText(),
            documentoField.getText(),
            new String(passwordField.getPassword())
        };
    }
}