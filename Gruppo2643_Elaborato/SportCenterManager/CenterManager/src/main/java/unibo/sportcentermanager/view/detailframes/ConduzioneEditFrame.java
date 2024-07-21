package unibo.sportcentermanager.view.detailframes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ConduzioneEditFrame extends JFrame {
    private final JTextField idCorsoField;
    private final JTextField idMembroField;
    private final JCheckBox isCoordinatoreCheckbox;
    private boolean confirmed = false;

    public ConduzioneEditFrame(String[] currentValues) {
        setTitle("Modifica Conduzione");
        setSize(300, 200);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("ID Corso:"));
        idCorsoField = new JTextField(currentValues[0]);
        idCorsoField.setEditable(false); // Non modificabile
        add(idCorsoField);

        add(new JLabel("ID Membro:"));
        idMembroField = new JTextField(currentValues[1]);
        idMembroField.setEditable(false); // Non modificabile
        add(idMembroField);

        add(new JLabel("Is Coordinatore:"));
        isCoordinatoreCheckbox = new JCheckBox();
        isCoordinatoreCheckbox.setSelected(Boolean.parseBoolean(currentValues[2]));
        add(isCoordinatoreCheckbox);

        JButton confirmButton = new JButton("Conferma");
        confirmButton.addActionListener((ActionEvent e) -> {
            confirmed = true;
            dispose();
        });
        add(confirmButton);

        JButton cancelButton = new JButton("Annulla");
        cancelButton.addActionListener((ActionEvent e) -> {
            dispose();
        });
        add(cancelButton);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String[] getValues() {
        return new String[] {
                idCorsoField.getText(),
                idMembroField.getText(),
                Boolean.toString(isCoordinatoreCheckbox.isSelected())
        };
    }
}