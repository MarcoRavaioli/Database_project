package unibo.sportcentermanager.view.detailframes;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ConduzioneInputFrame extends JFrame {
    private final JTextField idCorsoField;
    private final JTextField idMembroField;
    private final JCheckBox isCoordinatoreCheckbox;
    private boolean confirmed = false;

    public ConduzioneInputFrame() {
        setTitle("Aggiungi Conduzione");
        setSize(300, 200);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("ID Corso:"));
        idCorsoField = new JTextField();
        add(idCorsoField);

        add(new JLabel("ID Membro:"));
        idMembroField = new JTextField();
        add(idMembroField);

        add(new JLabel("Is Coordinatore:"));
        isCoordinatoreCheckbox = new JCheckBox();
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