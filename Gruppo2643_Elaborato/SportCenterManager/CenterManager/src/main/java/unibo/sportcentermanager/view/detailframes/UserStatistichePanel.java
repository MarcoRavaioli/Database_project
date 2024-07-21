package unibo.sportcentermanager.view.detailframes;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import unibo.sportcentermanager.controller.impl.VisualizationController;
import unibo.sportcentermanager.entity.Categoria;
import unibo.sportcentermanager.entity.Corso;
import unibo.sportcentermanager.entity.Lezione;
import unibo.sportcentermanager.entity.Membro;
import unibo.sportcentermanager.entity.Struttura;

@Component
public class UserStatistichePanel extends JFrame {

    @Autowired
    private VisualizationController visController;

    private Membro loggedInUser;
    private final JTextArea textArea;

    public UserStatistichePanel() {
        setTitle("Statistiche Utente");
        setSize(600, 400);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton closeButton = new JButton("Chiudi");
        closeButton.addActionListener(e -> onClose());
        add(closeButton, BorderLayout.SOUTH);
    }

    public void setMembro(Membro membro) {
        this.loggedInUser = membro;
        showStatistiche();
    }

    private void onClose() {
        dispose();
    }

    public void showStatistiche() {
        StringBuilder sb = new StringBuilder();

        // Visualizzazione degli abbonamenti più economici
        sb.append("Abbonamenti disponibili:\n");
        List<Categoria> abbonamentiEconomici = visController.getAbbonamentiEconomici();
        for (Categoria categoria : abbonamentiEconomici) {
            sb.append(categoria.getNome())
              .append(": ")
              .append(categoria.getPrezzo())
              .append("\n");
        }
        sb.append("\n");

        // Visualizzazione delle lezioni prenotate da un membro
        sb.append("Lezioni prenotate:\n");
        List<Lezione> lezioniPrenotate = visController.getLezioniPrenotate(loggedInUser.getId());
        for (Lezione lezione : lezioniPrenotate) {
            Corso corso = lezione.getCorso();
            sb.append(corso.getTitolo())
              .append(" - ")
              .append(lezione.getData())
              .append(", ")
              .append(lezione.getOrario())
              .append("\n");
        }
        sb.append("\n");

        // Visualizzazione delle strutture disponibili
        sb.append("Strutture disponibili:\n");
        List<Struttura> strutture = visController.getStruttureDisponibili();
        for (Struttura struttura : strutture) {
            sb.append(struttura.getNome())
              .append(" - ")
              .append(struttura.getIndirizzo().getCitta())
              .append(", ")
              .append(struttura.getIndirizzo().getVia())
              .append(" ")
              .append(struttura.getIndirizzo().getNumeroCivico())
              .append("\n");
        }

        textArea.setText(sb.toString());
    }
}