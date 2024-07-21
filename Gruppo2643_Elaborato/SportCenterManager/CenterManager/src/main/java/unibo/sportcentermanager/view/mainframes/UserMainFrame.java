package unibo.sportcentermanager.view.mainframes;

import javax.annotation.PostConstruct;
import javax.swing.*;

import java.awt.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import unibo.sportcentermanager.controller.api.OperationsController;
import unibo.sportcentermanager.controller.impl.VisualizationController;
import unibo.sportcentermanager.entity.Abbonamento;
import unibo.sportcentermanager.entity.Membro;
import unibo.sportcentermanager.view.authentication.RoleSelectionFrame;
import unibo.sportcentermanager.view.detailframes.MembroEditFrame;
import unibo.sportcentermanager.view.detailframes.PermessiFrame;
import unibo.sportcentermanager.view.detailframes.PrenotaLezioneFrame;
import unibo.sportcentermanager.view.detailframes.UserStatistichePanel;

@Component
@Lazy
public class UserMainFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    @Autowired
    private PermessiFrame permessiFrame;

    @Autowired
    private PrenotaLezioneFrame prenotaLezioneFrame;

    @Autowired
    private UserStatistichePanel userStatistichePanel;

    @Autowired
    private OperationsController operationsController;

    @Autowired
    private VisualizationController visController;

    private Membro loggedInUser;
    private JLabel welcomeLabel;

    public UserMainFrame() {
        setTitle("User Dashboard");
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel(), BorderLayout.NORTH);
        mainPanel.add(centerPanel(), BorderLayout.CENTER);

        setContentPane(mainPanel);
    }

    @PostConstruct
    void init() {
        setVisible(true);
    }

    public void setMembro(Membro membro) {
        this.loggedInUser = membro;
        updateWelcomeLabel();
        checkAbbonamentiInScadenza();
    }

    private JPanel topPanel() {
        final JPanel topPanel = new JPanel();
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Aggiungi padding ai bordi

        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout());
        welcomeLabel = new JLabel("Bentornato " + (loggedInUser != null ? loggedInUser.getNome() : ""));
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel, BorderLayout.WEST);

        JButton statisticheButton = new JButton("Statistiche");
        statisticheButton.addActionListener(e -> onStatisticheButtonClick());

        JPanel statButtonPanel = new JPanel(new BorderLayout());
        statButtonPanel.setBorder(new EmptyBorder(0, 20, 0, 0)); // Aggiungi padding a sinistra del bottone
        statButtonPanel.add(statisticheButton, BorderLayout.EAST);

        topPanel.add(welcomePanel, BorderLayout.WEST);
        topPanel.add(statButtonPanel, BorderLayout.EAST);

        return topPanel;
    }

    private JPanel centerPanel() {
        final JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout());

        JButton permessiButton = new JButton("Permessi");
        permessiButton.addActionListener(e -> onPermessiButtonClick());

        JButton infoPersonaliButton = new JButton("Info Personali");
        infoPersonaliButton.addActionListener(e -> onInfoPersonaliButtonClick());

        JButton prenotaLezioneButton = new JButton("Prenota Lezione");
        prenotaLezioneButton.addActionListener(e -> onPrenotaLezioneButtonClick());

        JButton eliminaProfiloButton = new JButton("Elimina Profilo");
        eliminaProfiloButton.addActionListener(e -> onEliminaProfiloButtonClick());

        centerPanel.add(permessiButton);
        centerPanel.add(infoPersonaliButton);
        centerPanel.add(prenotaLezioneButton);
        centerPanel.add(eliminaProfiloButton);

        return centerPanel;
    }

    private void onPermessiButtonClick() {
        permessiFrame.setMembro(loggedInUser);
        permessiFrame.setVisible(true);
    }

    private void onInfoPersonaliButtonClick() {
        MembroEditFrame membroEditFrame = new MembroEditFrame(
            loggedInUser.getNome(),
            loggedInUser.getCognome(),
            loggedInUser.getDataNascita().toString(),
            loggedInUser.getEmail(),
            loggedInUser.getDocumento(),
            loggedInUser.getPassword()
        );

        membroEditFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                if (membroEditFrame.isConfirmed()) {
                    String[] values = membroEditFrame.getValues();
                    int id = loggedInUser.getId();
                    operationsController.updateItem("Membri", id, values);

                    // Aggiorna l'oggetto loggedInUser con i nuovi valori
                    loggedInUser.setNome(values[0]);
                    loggedInUser.setCognome(values[1]);
                    loggedInUser.setDataNascita(java.sql.Date.valueOf(values[2]));
                    loggedInUser.setEmail(values[3]);
                    loggedInUser.setDocumento(values[4]);
                    loggedInUser.setPassword(values[5]);

                    // Aggiorna l'etichetta di benvenuto
                    updateWelcomeLabel();
                }
            }
        });

        membroEditFrame.setVisible(true);  // Ensure the frame is made visible
    }

    private void onPrenotaLezioneButtonClick() {
        prenotaLezioneFrame.setMembro(loggedInUser);
        prenotaLezioneFrame.setVisible(true);
    }

    private void onStatisticheButtonClick() {
        userStatistichePanel.setMembro(loggedInUser);
        userStatistichePanel.setVisible(true);
    }

    private void onEliminaProfiloButtonClick() {
        int response = JOptionPane.showConfirmDialog(this, "Sei sicuro di voler eliminare il tuo profilo?", "Conferma Eliminazione", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            operationsController.deleteItem("Membri", loggedInUser.getId());
            navigateToRoleSelection();
        }
    }

    private void navigateToRoleSelection() {
        // Assumi che ci sia una classe RoleSelectionFrame per la selezione del ruolo
        RoleSelectionFrame roleSelectionFrame = new RoleSelectionFrame();
        roleSelectionFrame.setVisible(true);
        dispose();
    }

    private void updateWelcomeLabel() {
        welcomeLabel.setText("Bentornato " + (loggedInUser != null ? loggedInUser.getNome() : ""));
    }

    private void checkAbbonamentiInScadenza() {
        List<Abbonamento> abbonamentiInScadenza = visController.getAbbonamentiInScadenza(loggedInUser.getId(), 20);
        if (!abbonamentiInScadenza.isEmpty()) {
            Abbonamento abbonamento = abbonamentiInScadenza.get(0);
            LocalDate dataFine = convertToLocalDateViaSqlDate(abbonamento.getDataFine());
            long giorniAllaScadenza = ChronoUnit.DAYS.between(LocalDate.now(), dataFine);
            JOptionPane.showMessageDialog(this,
                "Attenzione! Il tuo abbonamento scade tra " + giorniAllaScadenza + " giorni.",
                "Abbonamento in Scadenza",
                JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }
}