package unibo.sportcentermanager.view.detailframes;

import javax.swing.*;
import java.awt.*;

public class AttrezzoInputFrame extends JFrame {

    private final JTextField nomeField;
    private final JTextField descrizioneField;
    private final JTextField quantitaField;
    private final JTextField corsoField;
    private boolean confirmed;

    public AttrezzoInputFrame() {
        setTitle("Nuovo Attrezzo");
        setSize(300, 300);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Nome:"));
        nomeField = new JTextField();
        add(nomeField);

        add(new JLabel("Descrizione:"));
        descrizioneField = new JTextField();
        add(descrizioneField);

        add(new JLabel("Quantità:"));
        quantitaField = new JTextField();
        add(quantitaField);

        add(new JLabel("ID Corso:"));
        corsoField = new JTextField();
        add(corsoField);

        JButton confirmButton = new JButton("Conferma");
        confirmButton.addActionListener(e -> onConfirm());
        add(confirmButton);

        JButton cancelButton = new JButton("Annulla");
        cancelButton.addActionListener(e -> onCancel());
        add(cancelButton);
    }

    private void onConfirm() {
        confirmed = true;
        dispose();
    }

    private void onCancel() {
        confirmed = false;
        dispose();
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String[] getValues() {
        return new String[]{
                nomeField.getText(),
                descrizioneField.getText(),
                quantitaField.getText(),
                corsoField.getText()
        };
    }
}