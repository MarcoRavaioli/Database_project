package unibo.sportcentermanager.view.detailframes;

import javax.swing.*;
import java.awt.*;


public class AccessoEditFrame extends JFrame {

    private final JTextField categoriaField;
    private final JTextField strutturaField;
    private boolean confirmed;

    public AccessoEditFrame(String[] currentValues) {
        setTitle("Modifica Accesso");
        setSize(300, 150);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("ID Categoria:"));
        categoriaField = new JTextField(currentValues[0]);
        add(categoriaField);

        add(new JLabel("ID Struttura:"));
        strutturaField = new JTextField(currentValues[1]);
        add(strutturaField);

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
                categoriaField.getText(),
                strutturaField.getText()
        };
    }
}