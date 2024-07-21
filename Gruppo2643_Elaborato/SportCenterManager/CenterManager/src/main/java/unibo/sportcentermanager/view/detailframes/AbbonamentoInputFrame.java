package unibo.sportcentermanager.view.detailframes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AbbonamentoInputFrame extends JFrame {

    private final JTextField membroField;
    private final JTextField dataFineField;
    private final JTextField categoriaField;
    private boolean confirmed = false;

    public AbbonamentoInputFrame() {
        setTitle("Inserisci Nuovo Abbonamento");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("ID Membro:"));
        membroField = new JTextField();
        formPanel.add(membroField);

        formPanel.add(new JLabel("Data Fine (yyyy-MM-dd):"));
        dataFineField = new JTextField();
        formPanel.add(dataFineField);

        formPanel.add(new JLabel("ID Categoria:"));
        categoriaField = new JTextField();
        formPanel.add(categoriaField);

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
            membroField.getText(),
            dataFineField.getText(),
            categoriaField.getText()
        };
    }
}