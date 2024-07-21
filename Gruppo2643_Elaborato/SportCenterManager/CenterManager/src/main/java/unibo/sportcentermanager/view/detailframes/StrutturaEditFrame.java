package unibo.sportcentermanager.view.detailframes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StrutturaEditFrame extends JFrame {
    private final JTextField nomeField;
    private final JTextArea descrizioneField;
    private final JTextField indirizzoIdField;
    private boolean confirmed = false;

    public StrutturaEditFrame(String[] currentValues) {
        setTitle("Modifica Struttura");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField(currentValues[0]);
        formPanel.add(nomeField);

        formPanel.add(new JLabel("Descrizione:"));
        descrizioneField = new JTextArea(currentValues[1]);
        formPanel.add(new JScrollPane(descrizioneField));

        formPanel.add(new JLabel("ID Indirizzo:"));
        indirizzoIdField = new JTextField(currentValues[2]);
        formPanel.add(indirizzoIdField);

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
        return new String[] {
                nomeField.getText(),
                descrizioneField.getText(),
                indirizzoIdField.getText()
        };
    }
}