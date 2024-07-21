package unibo.sportcentermanager.view.detailframes;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class InstructorQuestionFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private final JRadioButton yesButton;
    private final JRadioButton noButton;
    private final JTextField brevettoField;
    private final JButton nextButton;

    @Autowired
    private ApplicationContext context;

    public InstructorQuestionFrame() {
        setTitle("Sei istruttrice/istruttore?");
        setSize(400, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        final JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        final JLabel questionLabel = new JLabel("Sei istruttrice/istruttore?");
        questionLabel.setBounds(10, 50, 200, 25);
        panel.add(questionLabel);

        yesButton = new JRadioButton("Sì");
        yesButton.setBounds(210, 50, 50, 25);
        panel.add(yesButton);

        noButton = new JRadioButton("No");
        noButton.setBounds(270, 50, 50, 25);
        panel.add(noButton);

        ButtonGroup group = new ButtonGroup();
        group.add(yesButton);
        group.add(noButton);

        brevettoField = new JTextField("Inserisci qui le informazioni del tuo brevetto:", 20);
        brevettoField.setBounds(10, 120, 360, 260);
        brevettoField.setVisible(false); // Nascondi inizialmente
        brevettoField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (brevettoField.getText().equals("Inserisci qui le informazioni del tuo brevetto:")) {
                    brevettoField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (brevettoField.getText().isEmpty()) {
                    brevettoField.setText("Inserisci qui le informazioni del tuo brevetto:");
                }
            }
        });
        panel.add(brevettoField);

        nextButton = new JButton("Avanti");
        nextButton.setBounds(150, 200, 100, 25);
        nextButton.setEnabled(false); // Disabilita inizialmente
        panel.add(nextButton);

        yesButton.addActionListener(e -> {
            brevettoField.setVisible(true);
            nextButton.setEnabled(true);
        });

        noButton.addActionListener(e -> {
            brevettoField.setVisible(false);
            nextButton.setEnabled(true);
        });

        nextButton.addActionListener(e -> proceed());
    }

    private void proceed() {
        if (yesButton.isSelected()) {
            String brevettoInfo = brevettoField.getText();
            // Gestisci le informazioni del brevetto se necessario
        }
        // Chiudi il frame o apri un altro frame come necessario
        JOptionPane.showMessageDialog(null, "Registrazione completata! Grazie per le informazioni.");
        dispose();
        //final LoginUserFrame loginUserFrame = context.getBean(LoginUserFrame.class);
        //loginUserFrame.setVisible(true);
    }
}