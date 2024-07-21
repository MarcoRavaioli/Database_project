package unibo.sportcentermanager.view.detailframes;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

@Component
public class TopPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public TopPanel() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10)); // Aggiungi padding ai bordi

        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout());
        JLabel welcomeLabel1 = new JLabel("Bentornato");
        welcomeLabel1.setFont(new Font("Serif", Font.BOLD, 24));
        JLabel welcomeLabel2 = new JLabel("Admin");
        welcomeLabel2.setFont(new Font("Serif", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel1, BorderLayout.NORTH);
        welcomePanel.add(welcomeLabel2, BorderLayout.SOUTH);

        add(welcomePanel, BorderLayout.WEST);

        JButton statisticheButton = new JButton("Statistiche");
        JPanel statButtonPanel = new JPanel(new BorderLayout());
        statButtonPanel.setBorder(new EmptyBorder(0, 20, 0, 0)); // Aggiungi padding a sinistra del bottone
        statButtonPanel.add(statisticheButton, BorderLayout.EAST);

        add(statButtonPanel, BorderLayout.EAST);
    }
}