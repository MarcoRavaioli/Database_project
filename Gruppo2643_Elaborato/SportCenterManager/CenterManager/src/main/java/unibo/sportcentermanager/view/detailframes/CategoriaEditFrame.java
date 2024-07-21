package unibo.sportcentermanager.view.detailframes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class CategoriaEditFrame extends JFrame {
    private final JTextField nomeField;
    private final JTextField descrizioneField;
    private final JTextField prezzoField;
    private boolean confirmed = false;

    public CategoriaEditFrame(String[] currentValues) {
        setTitle("Modifica Categoria");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField(currentValues[0]);
        formPanel.add(nomeField);

        formPanel.add(new JLabel("Descrizione:"));
        descrizioneField = new JTextField(currentValues[1]);
        formPanel.add(descrizioneField);

        formPanel.add(new JLabel("Prezzo:"));
        prezzoField = new JTextField(currentValues[2]);
        formPanel.add(prezzoField);

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
            descrizioneField.getText(),
            prezzoField.getText()
        };
    }
}