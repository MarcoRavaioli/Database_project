package unibo.sportcentermanager.view.detailframes;

import javax.swing.*;
import java.awt.*;


public class LezioneEditFrame extends JFrame {

    private final JTextField corsoField;
    private final JTextField dataField;
    private final JTextField orarioField;
    private final JTextField descrizioneField;
    private final JTextField maxPersoneField;
    private boolean confirmed;

    public LezioneEditFrame(String[] currentValues) {
        setTitle("Modifica Lezione");
        setSize(300, 300);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("ID Corso:"));
        corsoField = new JTextField(currentValues[0]);
        add(corsoField);

        add(new JLabel("Data (yyyy-MM-dd):"));
        dataField = new JTextField(currentValues[1]);
        add(dataField);

        add(new JLabel("Orario (HH:mm):"));
        orarioField = new JTextField(currentValues[2]);
        add(orarioField);

        add(new JLabel("Descrizione:"));
        descrizioneField = new JTextField(currentValues[3]);
        add(descrizioneField);

        add(new JLabel("Max Persone:"));
        maxPersoneField = new JTextField(currentValues[4]);
        add(maxPersoneField);

        JButton confirmButton = new JButton("Conferma");
        confirmButton.addActionListener(e -> onConfirm());
        add(confirmButton);

        JButton cancelButton = new JButton("Annulla");
        cancelButton.addActionListener(e -> onCancel());
        add(cancelButton);
    }

    private void onConfirm() {
        confirmed = true;
        setVisible(false);
    }

    private void onCancel() {
        confirmed = false;
        setVisible(false);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String[] getValues() {
        return new String[]{
                corsoField.getText(),
                dataField.getText(),
                orarioField.getText(),
                descrizioneField.getText(),
                maxPersoneField.getText()
        };
    }
}