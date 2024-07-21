package unibo.sportcentermanager.view.detailframes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import unibo.sportcentermanager.controller.impl.VisualizationController;
import unibo.sportcentermanager.entity.Abbonamento;
import unibo.sportcentermanager.entity.Corso;
import unibo.sportcentermanager.entity.Membro;
import unibo.sportcentermanager.entity.Struttura;

@Component
public class PermessiFrame extends JFrame {
    
    @Autowired
    private VisualizationController visController;

    private Membro loggedInUser;
    private final JTextArea textArea;

    public PermessiFrame() {
        setTitle("Permessi");
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
        showPermessi();
    }

    private void onClose() {
        dispose();
    }

    private void showPermessi() {
        StringBuilder sb = new StringBuilder();
        sb.append("Permessi:\n");
        
        LocalDate today = LocalDate.now();
        List<Abbonamento> abbonamenti = visController.getAbbonamentiByMembro(loggedInUser.getId()).stream()
            .filter(abbonamento -> convertToLocalDateViaSqlDate(abbonamento.getDataFine()).isAfter(today))
            .collect(Collectors.toList());
        
        for (Abbonamento abbonamento : abbonamenti) {
            sb.append("Abbonamento: ")
              .append(abbonamento.getId())
              .append(", ")
              .append(abbonamento.getCategoria().getNome())
              .append(", ")
              .append(abbonamento.getDataFine())
              .append("\n");
            
            List<Struttura> strutture = visController.getStruttureByCategoria(abbonamento.getCategoria().getId());
            for (Struttura struttura : strutture) {
                sb.append("\tStruttura: ")
                  .append(struttura.getId())
                  .append(", ")
                  .append(struttura.getNome())
                  .append("\n");
                
                List<Corso> corsi = visController.getCorsiByStruttura(struttura.getId());
                for (Corso corso : corsi) {
                    sb.append("\t\tCorso: ")
                      .append(corso.getId())
                      .append(", ")
                      .append(corso.getTitolo())
                      .append("\n");
                }
            }
        }
    
        textArea.setText(sb.toString());
    }

    private LocalDate convertToLocalDateViaSqlDate(java.util.Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }
}