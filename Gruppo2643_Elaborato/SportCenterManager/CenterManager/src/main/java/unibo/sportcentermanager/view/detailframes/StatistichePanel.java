package unibo.sportcentermanager.view.detailframes;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unibo.sportcentermanager.controller.impl.VisualizationController;
import unibo.sportcentermanager.entity.Corso;
import unibo.sportcentermanager.entity.Lezione;

@Component
public class StatistichePanel extends JPanel {

    @Autowired
    private VisualizationController visController;

    private final JTextArea textArea;

    public StatistichePanel() {
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton closeButton = new JButton("Chiudi");
        closeButton.addActionListener(e -> onClose());
        add(closeButton, BorderLayout.SOUTH);
    }

    private void onClose() {
        Window parent = SwingUtilities.getWindowAncestor(this);
        if (parent != null) {
            parent.dispose();
        }
    }

    public void showStatistiche() {
        StringBuilder sb = new StringBuilder();

        sb.append("Corsi e Lezioni (ordinati per media partecipazione):\n");
        List<Map.Entry<Corso, Double>> corsiOrdinatiPerMedia = visController.getCorsiOrdinatiPerMediaPartecipazione();
        for (Map.Entry<Corso, Double> entry : corsiOrdinatiPerMedia) {
            Corso corso = entry.getKey();
            double mediaPartecipazione = entry.getValue();
            sb.append("Corso: ").append(corso.getTitolo()).append(" - Media Partecipazione: ")
                    .append(String.format("%.2f", mediaPartecipazione)).append("\n");
            List<Lezione> lezioni = visController.getLezioniByCorso(corso.getId());
            if (lezioni != null) {  // Controllo se lezioni è null
                for (Lezione lezione : lezioni) {
                    int numeroPartecipanti = visController.getNumeroPartecipanti(lezione);
                    sb.append("\tLezione: ").append(lezione.getData()).append(", ").append(lezione.getOrario())
                            .append(" - Partecipanti: ").append(numeroPartecipanti).append("/")
                            .append(lezione.getMaxPersone()).append("\n");
                }
            }
            sb.append("\n");
        }

        sb.append("Entrate ottenute dagli abbonamenti:\n");
        sb.append(visController.getEntrateAbbonamenti()).append("\n\n");

        sb.append("Strutture disponibili:\n");
        visController.getStruttureDisponibili().forEach(struttura -> sb.append(struttura.getNome()).append(", presso: ")
                .append(struttura.getIndirizzo().getCitta()).append(", ").append(struttura.getIndirizzo().getVia())
                .append(", ").append(struttura.getIndirizzo().getNumeroCivico()).append(", Descrizione: ")
                .append(struttura.getDescrizione()).append("\n"));
        sb.append("\n");

        textArea.setText(sb.toString());
    }
}