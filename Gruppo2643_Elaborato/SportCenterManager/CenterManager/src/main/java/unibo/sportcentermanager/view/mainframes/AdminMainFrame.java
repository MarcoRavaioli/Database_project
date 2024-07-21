package unibo.sportcentermanager.view.mainframes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import unibo.sportcentermanager.controller.api.OperationsController;
import unibo.sportcentermanager.entity.Abbonamento;
import unibo.sportcentermanager.entity.Accesso;
import unibo.sportcentermanager.entity.AccessoId;
import unibo.sportcentermanager.entity.Attrezzo;
import unibo.sportcentermanager.entity.Categoria;
import unibo.sportcentermanager.entity.Conduzione;
import unibo.sportcentermanager.entity.ConduzioneId;
import unibo.sportcentermanager.entity.Corso;
import unibo.sportcentermanager.entity.Indirizzo;
import unibo.sportcentermanager.entity.Lezione;
import unibo.sportcentermanager.entity.Membro;
import unibo.sportcentermanager.entity.Struttura;
import unibo.sportcentermanager.view.detailframes.AbbonamentoEditFrame;
import unibo.sportcentermanager.view.detailframes.AbbonamentoInputFrame;
import unibo.sportcentermanager.view.detailframes.AccessoEditFrame;
import unibo.sportcentermanager.view.detailframes.AccessoInputFrame;
import unibo.sportcentermanager.view.detailframes.AttrezzoEditFrame;
import unibo.sportcentermanager.view.detailframes.AttrezzoInputFrame;
import unibo.sportcentermanager.view.detailframes.CategoriaEditFrame;
import unibo.sportcentermanager.view.detailframes.CategoriaInputFrame;
import unibo.sportcentermanager.view.detailframes.ConduzioneEditFrame;
import unibo.sportcentermanager.view.detailframes.ConduzioneInputFrame;
import unibo.sportcentermanager.view.detailframes.CorsoInputFrame;
import unibo.sportcentermanager.view.detailframes.FrameFactory;
import unibo.sportcentermanager.view.detailframes.IndirizzoEditFrame;
import unibo.sportcentermanager.view.detailframes.IndirizzoInputFrame;
import unibo.sportcentermanager.view.detailframes.LezioneInputFrame;
import unibo.sportcentermanager.view.detailframes.MembroEditFrame;
import unibo.sportcentermanager.view.detailframes.MembroInputFrame;
import unibo.sportcentermanager.view.detailframes.StatistichePanel;
import unibo.sportcentermanager.view.detailframes.StrutturaEditFrame;
import unibo.sportcentermanager.view.detailframes.StrutturaInputFrame;

@Component
@Lazy
public class AdminMainFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private final JTabbedPane tabbedPane;
    private final String[] tableNames = { "Indirizzi", "Strutture", "Corsi", "Attrezzi", "Lezioni", "Membri",
            "Abbonamenti",
            "Categorie", "Accessi", "Conduzioni" };

    @Autowired
    private OperationsController adminOpContr;

    @Autowired
    private StatistichePanel statistichePanel;

    public AdminMainFrame() {
        setTitle("Admin Dashboard");
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        // Pannello principale
        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(topPanel(), BorderLayout.NORTH);

        setContentPane(mainPanel);
    }

    @PostConstruct
    void createPane() {
        for (final String name : tableNames) {
            tabbedPane.addTab(name, createPanePanel(name));
        }
        add(tabbedPane, BorderLayout.CENTER);
        setVisible(true);
    }

    public String[] getColumnsName(final String tableName) {
        return switch (tableName) {
            case "Corsi" -> new String[] { "ID", "Titolo", "Descrizione", "FasciaOraria", "Struttura" };
            case "Indirizzi" -> new String[] { "ID", "Stato", "Citta", "Via", "Numero", "CAP" };
            case "Membri" -> new String[] { "ID", "Nome", "Cognome", "Data", "Email", "Documento" };
            case "Strutture" -> new String[] { "ID", "Nome", "Descrizione", "Indirizzo" };
            case "Abbonamenti" -> new String[] { "ID", "ID Membro", "Data Fine", "ID Categoria" };
            case "Categorie" -> new String[] { "ID", "Nome", "Descrizione", "Prezzo" };
            case "Lezioni" -> new String[] { "ID", "ID Corso", "Data", "Orario", "Descrizione", "Max Persone" };
            case "Attrezzi" -> new String[] { "ID", "Nome", "Descrizione", "Quantità", "ID Corso" };
            case "Accessi" -> new String[] { "Categoria", "Struttura" };
            case "Conduzioni" -> new String[] { "ID Corso", "ID Membro", "Is Coordinatore" };
            default -> new String[] { "errore" };
        };
    }

    private JPanel createPanePanel(final String name) {
        final JPanel panel = new JPanel(new BorderLayout());
        final JButton addButton = new JButton("Nuovo");
        final JButton modifyButton = new JButton("Modifica");
        final JButton deleteButton = new JButton("Elimina");

        final JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(addButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(deleteButton);

        final String[] columnsName = getColumnsName(name);
        final DefaultTableModel model = new DefaultTableModel(columnsName, 0) {
            @Override
            public boolean isCellEditable(final int row, final int column) {
                return false;
            }
        };

        final JTable table = new JTable(model);

        addButton.addActionListener(e -> addRow(name, model));
        modifyButton.addActionListener(e -> modifyRow(name, model, table));
        deleteButton.addActionListener(e -> deleteRow(name, model, table));

        final JScrollPane scrollPane = new JScrollPane(table);

        populateModel(name, model);

        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private void addRow(String name, DefaultTableModel model) {
        JFrame inputFrame = FrameFactory.createInputFrame(name);
        inputFrame.setVisible(true);
        if (inputFrame instanceof MembroInputFrame membroInputFrame) {
            MembroInputFrame membroFrame = membroInputFrame;
            membroFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    if (membroFrame.isConfirmed()) {
                        String[] values = membroFrame.getValues();
                        adminOpContr.addItem(name, values);
                        populateModel(name, model); // Aggiorna la tabella dopo l'inserimento
                    }
                }
            });
        } else if (inputFrame instanceof CorsoInputFrame corsoFrame) {
            corsoFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    if (corsoFrame.isConfirmed()) {
                        String[] values = corsoFrame.getValues();
                        adminOpContr.addItem(name, values);
                        populateModel(name, model); // Aggiorna la tabella dopo l’inserimento
                    }
                }
            });
        } else if (inputFrame instanceof IndirizzoInputFrame indirizzoFrame) {
            indirizzoFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    if (indirizzoFrame.isConfirmed()) {
                        String[] values = indirizzoFrame.getValues();
                        adminOpContr.addItem(name, values);
                        populateModel(name, model); // Aggiorna la tabella dopo l’inserimento
                    }
                }
            });
        } else if (inputFrame instanceof StrutturaInputFrame strutturaFrame) {
            strutturaFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    if (strutturaFrame.isConfirmed()) {
                        String[] values = strutturaFrame.getValues();
                        adminOpContr.addItem(name, values);
                        populateModel(name, model); // Aggiorna la tabella dopo l’inserimento
                    }
                }
            });
        } else if (inputFrame instanceof AbbonamentoInputFrame abbonamentoInputFrame) {
            abbonamentoInputFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    if (abbonamentoInputFrame.isConfirmed()) {
                        String[] values = abbonamentoInputFrame.getValues();
                        adminOpContr.addItem(name, values);
                        populateModel(name, model); // Aggiorna la tabella dopo l’inserimento
                    }
                }
            });
        } else if (inputFrame instanceof CategoriaInputFrame categoriaFrame) {
            categoriaFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    if (categoriaFrame.isConfirmed()) {
                        String[] values = categoriaFrame.getValues();
                        adminOpContr.addItem(name, values);
                        populateModel(name, model); // Aggiorna la tabella dopo l’inserimento
                    }
                }
            });
        } else if (inputFrame instanceof LezioneInputFrame lezioneFrame) {
            System.out.println("è entrato nel windowListener");
            lezioneFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    if (lezioneFrame.isConfirmed()) {
                        System.out.println("chiusura confermata");
                        String[] values = lezioneFrame.getValues();
                        adminOpContr.addItem(name, values);
                        populateModel(name, model); // Aggiorna la tabella dopo l’inserimento
                    }
                }
            });
        } else if (inputFrame instanceof AttrezzoInputFrame attrezzoFrame) {
            System.out.println("è entrato nel windowListener");
            attrezzoFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    if (attrezzoFrame.isConfirmed()) {
                        System.out.println("chiusura confermata");
                        String[] values = attrezzoFrame.getValues();
                        adminOpContr.addItem(name, values);
                        populateModel(name, model); // Aggiorna la tabella dopo l’inserimento
                    }
                }
            });
        } else if (inputFrame instanceof AccessoInputFrame accessoFrame) {
            System.out.println("è entrato nel windowListener");
            accessoFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    if (accessoFrame.isConfirmed()) {
                        System.out.println("chiusura confermata");
                        String[] values = accessoFrame.getValues();
                        adminOpContr.addItem(name, values);
                        populateModel(name, model); // Aggiorna la tabella dopo l’inserimento
                    }
                }
            });
        } else if (inputFrame instanceof ConduzioneInputFrame conduzioneFrame) {
            System.out.println("è entrato nel windowListener");
            conduzioneFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    if (conduzioneFrame.isConfirmed()) {
                        String[] values = conduzioneFrame.getValues();
                        adminOpContr.addItem(name, values);
                        populateModel(name, model); // Aggiorna la tabella dopo l’inserimento
                    }
                }
            });
        }
    }

    private void modifyRow(String name, DefaultTableModel model, JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleziona una riga da modificare.", "Errore",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] currentValues = new String[getColumnsName(name).length];
        for (int i = 1; i < getColumnsName(name).length; i++) { // Ignora la colonna ID (i = 0)
            currentValues[i - 1] = table.getValueAt(selectedRow, i).toString();
        }

        JFrame editFrame = FrameFactory.createEditFrame(name, currentValues);
        editFrame.setVisible(true);

        if (editFrame instanceof MembroEditFrame membroEditFrame) {
            membroEditFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    if (membroEditFrame.isConfirmed()) {
                        String[] values = membroEditFrame.getValues();
                        int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
                        adminOpContr.updateItem(name, id, values);
                        populateModel(name, model); // Aggiorna la tabella dopo la modifica
                    }
                }
            });
        } else if (editFrame instanceof IndirizzoEditFrame indirizzoEditFrame) {
            indirizzoEditFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    if (indirizzoEditFrame.isConfirmed()) {
                        String[] values = indirizzoEditFrame.getValues();
                        System.out.println("valori dentro la modifyRow");
                        for (String string : values) {
                            System.out.println(string);
                        }
                        int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
                        System.out.println("id: " + id);
                        adminOpContr.updateItem(name, id, values);
                        populateModel(name, model); // Aggiorna la tabella dopo la modifica
                    }
                }
            });
        } else if (editFrame instanceof StrutturaEditFrame strutturaEditFrame) {
            strutturaEditFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    if (strutturaEditFrame.isConfirmed()) {
                        String[] values = strutturaEditFrame.getValues();
                        int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
                        adminOpContr.updateItem(name, id, values);
                        populateModel(name, model); // Aggiorna la tabella dopo la modifica
                    }
                }
            });
        } else if (editFrame instanceof AbbonamentoEditFrame abbonamentoEditFrame) {
            abbonamentoEditFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    if (abbonamentoEditFrame.isConfirmed()) {
                        String[] values = abbonamentoEditFrame.getValues();
                        int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
                        adminOpContr.updateItem(name, id, values);
                        populateModel(name, model); // Aggiorna la tabella dopo la modifica
                    }
                }
            });
        } else if (editFrame instanceof CategoriaEditFrame categoriaFrame) {
            categoriaFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    if (categoriaFrame.isConfirmed()) {
                        String[] values = categoriaFrame.getValues();
                        int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
                        adminOpContr.updateItem(name, id, values);
                        populateModel(name, model); // Aggiorna la tabella dopo la modifica
                    }
                }
            });
        } else if (editFrame instanceof StrutturaEditFrame strutturaFrame) {
            strutturaFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    if (strutturaFrame.isConfirmed()) {
                        String[] values = strutturaFrame.getValues();
                        int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
                        adminOpContr.updateItem(name, id, values);
                        populateModel(name, model); // Aggiorna la tabella dopo la modifica
                    }
                }
            });
        } else if (editFrame instanceof AttrezzoEditFrame attrezzoFrame) {
            attrezzoFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    if (attrezzoFrame.isConfirmed()) {
                        String[] values = attrezzoFrame.getValues();
                        int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
                        adminOpContr.updateItem(name, id, values);
                        populateModel(name, model); // Aggiorna la tabella dopo la modifica
                    }
                }
            });
        } else if (editFrame instanceof AccessoEditFrame accessoFrame) {
            accessoFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    if (accessoFrame.isConfirmed()) {
                        String[] values = accessoFrame.getValues();
                        AccessoId id = new AccessoId(
                                Integer.parseInt(currentValues[0]),
                                Integer.parseInt(currentValues[1]));
                        adminOpContr.updateItem(name, id, values);
                        populateModel(name, model); // Aggiorna la tabella dopo la modifica
                    }
                }
            });
        } else if (editFrame instanceof ConduzioneEditFrame conduzioneEditFrame) {
        conduzioneEditFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                if (conduzioneEditFrame.isConfirmed()) {
                    String[] values = conduzioneEditFrame.getValues();
                    ConduzioneId id = new ConduzioneId(
                        Integer.parseInt(currentValues[0]),
                        Integer.parseInt(currentValues[1]));
                    adminOpContr.updateItem(name, id, values);
                    populateModel(name, model); // Aggiorna la tabella dopo la modifica
                }
            }
        });
    }
    }

    private void deleteRow(String name, DefaultTableModel model, JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleziona una riga da eliminare.", "Errore",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Sei sicuro di voler eliminare questa riga?",
                "Conferma Eliminazione", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            switch (name) {
                case "Accessi" -> {
                    AccessoId accessoId = new AccessoId(
                            Integer.parseInt(table.getValueAt(selectedRow, 0).toString().substring(0, 1)),
                            Integer.parseInt(table.getValueAt(selectedRow, 1).toString().substring(0, 1)));
                    adminOpContr.deleteItem(name, accessoId);
                }
                case "Conduzioni" -> {
                    ConduzioneId conduzioneId = new ConduzioneId(
                            Integer.parseInt(table.getValueAt(selectedRow, 0).toString().substring(0, 1)),
                            Integer.parseInt(table.getValueAt(selectedRow, 1).toString().substring(0, 1)));
                    adminOpContr.deleteItem(name, conduzioneId);
                }
                default -> {
                    int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
                    adminOpContr.deleteItem(name, id);
                }
            }
            populateModel(name, model); // Aggiorna la tabella dopo l'eliminazione
        }
    }

    private void populateModel(final String name, final DefaultTableModel model) {
        model.setRowCount(0); // Svuota il modello
        final List<?> items = adminOpContr.getAllItems(name);
        for (Object item : items) {
            switch (name) {
                case "Corsi" -> {
                    Corso corso = (Corso) item;
                    Struttura struttura = corso.getStruttura(); // Assicurati che la struttura sia inizializzata
                    model.addRow(new Object[] {
                            corso.getId(),
                            corso.getTitolo(),
                            corso.getDescrizione(),
                            corso.getFasciaOraria(),
                            struttura.getId() + ", " + struttura.getNome()
                    });
                }
                case "Indirizzi" -> {
                    Indirizzo indirizzo = (Indirizzo) item;
                    model.addRow(new Object[] {
                            indirizzo.getId(),
                            indirizzo.getStato(),
                            indirizzo.getCitta(),
                            indirizzo.getVia(),
                            indirizzo.getNumeroCivico(),
                            indirizzo.getCap()
                    });
                }
                case "Membri" -> {
                    Membro membro = (Membro) item;
                    model.addRow(new Object[] {
                            membro.getId(),
                            membro.getNome(),
                            membro.getCognome(),
                            membro.getDataNascita().toString(),
                            membro.getEmail(),
                            membro.isIstruttore() ? membro.getDocumento() : "no",
                    });
                }
                case "Strutture" -> {
                    Struttura struttura = (Struttura) item;
                    model.addRow(new Object[] {
                            struttura.getId(),
                            struttura.getNome(),
                            struttura.getDescrizione(),
                            struttura.getIndirizzo().getId() + ", " + struttura.getIndirizzo().getCitta() + ", " + struttura.getIndirizzo().getVia()
                    });
                }
                case "Abbonamenti" -> {
                    Abbonamento abbonamento = (Abbonamento) item;
                    model.addRow(new Object[] {
                            abbonamento.getId(),
                            abbonamento.getMembro().getId() + ": " + abbonamento.getMembro().getNome(),
                            abbonamento.getDataFine().toString(),
                            abbonamento.getCategoria().getId() + ": " + abbonamento.getCategoria().getNome()
                    });
                }
                case "Categorie" -> {
                    Categoria categoria = (Categoria) item;
                    model.addRow(new Object[] {
                            categoria.getId(),
                            categoria.getNome(),
                            categoria.getDescrizione(),
                            categoria.getPrezzo()
                    });
                }
                case "Lezioni" -> {
                    Lezione lezione = (Lezione) item;
                    Corso corso = lezione.getCorso();
                    model.addRow(new Object[] {
                            lezione.getId(),
                            corso.getId() + ", " + corso.getTitolo(),
                            lezione.getData(),
                            lezione.getOrario(),
                            lezione.getDescrizione(),
                            lezione.getMaxPersone()
                    });
                }
                case "Attrezzi" -> {
                    Attrezzo attrezzo = (Attrezzo) item;
                    Corso corso = attrezzo.getCorso();
                    model.addRow(new Object[] {
                            attrezzo.getId(),
                            attrezzo.getNome(),
                            attrezzo.getDescrizione(),
                            attrezzo.getQuantita(),
                            corso.getId() + ", " + corso.getTitolo()
                    });
                }
                case "Accessi" -> {
                    Accesso accesso = (Accesso) item;
                    model.addRow(new Object[] {
                            accesso.getCategoria().getId() + ", " + accesso.getCategoria().getNome(),
                            accesso.getStruttura().getId() + ", " + accesso.getStruttura().getNome()
                    });
                }
                case "Conduzioni" -> {
                Conduzione conduzione = (Conduzione) item;
                model.addRow(new Object[] {
                        conduzione.getCorso().getId() + ", " + conduzione.getCorso().getTitolo(),
                        conduzione.getMembro().getId() + ", " + conduzione.getMembro().getNome(),
                        conduzione.isCoordinatore() ? "Sì" : "No"
                });
            }
                default -> throw new IllegalArgumentException("Unknown entity: " + name);
            }
        }
    }

    private void onStatisticheButtonClick() {
        JFrame statsFrame = new JFrame("Statistiche");
        statsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        statsFrame.setSize(600, 400);
        statsFrame.setLayout(new BorderLayout());
        statsFrame.add(statistichePanel, BorderLayout.CENTER);
        statistichePanel.showStatistiche();
        statsFrame.setVisible(true);
    }

    private JPanel topPanel() {
        final JPanel topPanel = new JPanel();

        setLayout(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Aggiungi padding ai bordi

        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout());
        JLabel welcomeLabel1 = new JLabel("Bentornato");
        welcomeLabel1.setFont(new Font("Serif", Font.BOLD, 24));
        JLabel welcomeLabel2 = new JLabel("Admin");
        welcomeLabel2.setFont(new Font("Serif", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel1, BorderLayout.NORTH);
        welcomePanel.add(welcomeLabel2, BorderLayout.SOUTH);

        topPanel.add(welcomePanel, BorderLayout.WEST);

        JButton statisticheButton = new JButton("Statistiche");
        statisticheButton.addActionListener(e -> onStatisticheButtonClick());
        JPanel statButtonPanel = new JPanel(new BorderLayout());
        statButtonPanel.setBorder(new EmptyBorder(0, 20, 0, 0)); // Aggiungi padding a sinistra del bottone
        statButtonPanel.add(statisticheButton, BorderLayout.EAST);

        topPanel.add(statButtonPanel, BorderLayout.EAST);

        return topPanel;
    }
}