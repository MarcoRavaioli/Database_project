package unibo.sportcentermanager.view.detailframes;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unibo.sportcentermanager.controller.impl.VisualizationController;
import unibo.sportcentermanager.entity.Corso;
import unibo.sportcentermanager.entity.Lezione;
import unibo.sportcentermanager.entity.Membro;

@Component
public class PrenotaLezioneFrame extends JFrame {

    @Autowired
    private VisualizationController visController;

    private Membro loggedInUser;
    private final JPanel contentPanel;

    public PrenotaLezioneFrame() {
        setTitle("Prenota Lezione");
        setSize(800, 600);
        setLayout(new BorderLayout());

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(contentPanel), BorderLayout.CENTER);

        JButton closeButton = new JButton("Chiudi");
        closeButton.addActionListener(e -> onClose());
        add(closeButton, BorderLayout.SOUTH);
    }

    public void setMembro(Membro membro) {
        this.loggedInUser = membro;
        showLezioni();
    }

    private void onClose() {
        dispose();
    }

    private void showLezioni() {
        contentPanel.removeAll();

        List<Corso> corsi = visController.getCorsiDisponibili(loggedInUser.getId());
        LocalDate today = LocalDate.now();
        for (Corso corso : corsi) {
            JPanel corsoPanel = new JPanel(new BorderLayout());
            corsoPanel.add(new JLabel("Corso: " + corso.getTitolo()), BorderLayout.NORTH);

            JPanel lezionePanel = new JPanel();
            lezionePanel.setLayout(new BoxLayout(lezionePanel, BoxLayout.Y_AXIS));

            List<Lezione> lezioni = visController.getLezioniByCorso(corso.getId());
            for (Lezione lezione : lezioni) {
                LocalDate dataLezione = convertToLocalDateViaSqlDate(lezione.getData());
                if (!dataLezione.isBefore(today.plusDays(1))) {  // Filtra le lezioni che avvengono da domani in poi
                    JPanel lezioneSubPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    lezioneSubPanel.add(new JLabel("Lezione: " + lezione.getDescrizione() + " - " + lezione.getData() + " " + lezione.getOrario()));

                    JButton prenotaButton = new JButton("Prenota");
                    if (visController.isIscritto(loggedInUser.getId(), lezione.getId())) {
                        prenotaButton.setText("Disdici");
                    }
                    prenotaButton.addActionListener(new PrenotaButtonActionListener(loggedInUser.getId(), lezione.getId(), prenotaButton));
                    lezioneSubPanel.add(prenotaButton);

                    lezionePanel.add(lezioneSubPanel);
                }
            }

            corsoPanel.add(lezionePanel, BorderLayout.CENTER);
            contentPanel.add(corsoPanel);
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    private class PrenotaButtonActionListener implements ActionListener {
        private final int membroId;
        private final int lezioneId;
        private final JButton button;

        public PrenotaButtonActionListener(int membroId, int lezioneId, JButton button) {
            this.membroId = membroId;
            this.lezioneId = lezioneId;
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (button.getText().equals("Prenota")) {
                visController.prenotaLezione(membroId, lezioneId);
                button.setText("Disdici");
            } else {
                visController.disdiciLezione(membroId, lezioneId);
                button.setText("Prenota");
            }
        }
    }
}