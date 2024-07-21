package unibo.sportcentermanager.view.detailframes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class CorsoInputFrame extends JFrame {
    private final JTextField titoloField;
    private final JTextField descrizioneField;
    private final JTextField fasciaOrariaField;
    private final JTextField strutturaIdField;
    private boolean confirmed = false;

    public CorsoInputFrame() {
        setTitle("Inserisci Nuovo Corso");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("Titolo:"));
        titoloField = new JTextField();
        formPanel.add(titoloField);

        formPanel.add(new JLabel("Descrizione:"));
        descrizioneField = new JTextField();
        formPanel.add(descrizioneField);

        formPanel.add(new JLabel("Fascia Oraria:"));
        fasciaOrariaField = new JTextField();
        formPanel.add(fasciaOrariaField);

        formPanel.add(new JLabel("ID Struttura:"));
        strutturaIdField = new JTextField();
        formPanel.add(strutturaIdField);

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
            titoloField.getText(),
            descrizioneField.getText(),
            fasciaOrariaField.getText(),
            strutturaIdField.getText()
        };
    }
}