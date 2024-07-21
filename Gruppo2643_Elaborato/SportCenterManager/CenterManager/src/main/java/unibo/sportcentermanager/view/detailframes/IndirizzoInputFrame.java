package unibo.sportcentermanager.view.detailframes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class IndirizzoInputFrame extends JFrame {
    private final JTextField statoField;
    private final JTextField viaField;
    private final JTextField numeroField;
    private final JTextField cittaField;
    private final JTextField capField;
    private boolean confirmed = false;

    public IndirizzoInputFrame() {
        setTitle("Inserisci Nuovo Indirizzo");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("Stato:"));
        statoField = new JTextField();
        formPanel.add(statoField);
        
        formPanel.add(new JLabel("Città:"));
        cittaField = new JTextField();
        formPanel.add(cittaField);

        formPanel.add(new JLabel("Via:"));
        viaField = new JTextField();
        formPanel.add(viaField);

        formPanel.add(new JLabel("Numero:"));
        numeroField = new JTextField();
        formPanel.add(numeroField);

        formPanel.add(new JLabel("CAP:"));
        capField = new JTextField();
        formPanel.add(capField);

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
            statoField.getText(),
            cittaField.getText(),
            viaField.getText(),
            numeroField.getText(),
            capField.getText()
        };
    }
}