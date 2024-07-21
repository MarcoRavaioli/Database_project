package unibo.sportcentermanager.view.detailframes;

import javax.swing.*;
import org.springframework.stereotype.Component;
import java.awt.*;

@Component
public class LezioneInputFrame extends JFrame {

    private final JTextField corsoField;
    private final JTextField dataField;
    private final JTextField orarioField;
    private final JTextField descrizioneField;
    private final JTextField maxPersoneField;
    private boolean confirmed;

    public LezioneInputFrame() {
        setTitle("Nuova Lezione");
        setSize(300, 300);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("ID Corso:"));
        corsoField = new JTextField();
        add(corsoField);

        add(new JLabel("Data (yyyy-MM-dd):"));
        dataField = new JTextField();
        add(dataField);

        add(new JLabel("Orario (HH:mm):"));
        orarioField = new JTextField();
        add(orarioField);

        add(new JLabel("Descrizione:"));
        descrizioneField = new JTextField();
        add(descrizioneField);

        add(new JLabel("Max Persone:"));
        maxPersoneField = new JTextField();
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
                corsoField.getText(),
                dataField.getText(),
                orarioField.getText(),
                descrizioneField.getText(),
                maxPersoneField.getText()
        };
    }
}