package unibo.sportcentermanager.controller.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
import unibo.sportcentermanager.service.AbbonamentoService;
import unibo.sportcentermanager.service.AccessoService;
import unibo.sportcentermanager.service.AttrezzoService;
import unibo.sportcentermanager.service.CategoriaService;
import unibo.sportcentermanager.service.ConduzioneService;
import unibo.sportcentermanager.service.CorsoService;
import unibo.sportcentermanager.service.IndirizzoService;
import unibo.sportcentermanager.service.LezioneService;
import unibo.sportcentermanager.service.MembroService;
import unibo.sportcentermanager.service.StrutturaService;

@Controller
public class OperationsControllerImpl implements OperationsController {

    @Autowired
    private CorsoService corsoService;

    @Autowired
    private IndirizzoService indirizzoService;

    @Autowired
    private MembroService membroService;

    @Autowired
    private StrutturaService strutturaService;

    @Autowired
    private AbbonamentoService abbonamentoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private LezioneService lezioneService;

    @Autowired
    private AttrezzoService attrezzoService;

    @Autowired
    private AccessoService accessoService;

    @Autowired
    private ConduzioneService conduzioneService;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public List<?> getAllItems(String entityName) {
        switch (entityName) {
            case "Corsi" -> {
                List<Corso> corsi = corsoService.findAllCorso();
                corsi.forEach(corso -> {
                    if (corso.getStruttura() != null) {
                        corso.getStruttura().getNome(); // Forza l'inizializzazione della struttura
                    }
                });
                return corsi;
            }
            case "Indirizzi" -> {
                return indirizzoService.findAllIndirizzo();
            }
            case "Membri" -> {
                return membroService.findAllMembro();
            }
            case "Strutture" -> {
                return strutturaService.findAllStruttura();
            }
            case "Abbonamenti" -> {
                return abbonamentoService.findAllAbbonamento();
            }
            case "Categorie" -> {
                return categoriaService.findAllCategoria();
            }
            case "Lezioni" -> {
                return lezioneService.findAllLezione();
            }
            case "Attrezzi" -> {
                return attrezzoService.findAllAttrezzo();
            }
            case "Accessi" -> {
                return accessoService.findAllAccesso();
            }
            case "Conduzioni" -> {
                return conduzioneService.findAllConduzione();
            }
            default -> throw new IllegalArgumentException("Unknown entity: " + entityName);
        }
    }

    @Override
    public void addItem(String entityName, String[] values) {
        switch (entityName) {
            case "Corsi" -> {
                Struttura struttura = strutturaService.findById(Integer.parseInt(values[3]));
                if (struttura != null) {
                    Corso newCorso = new Corso(values[0], values[1], values[2], struttura);
                    corsoService.save(newCorso);
                } else {
                    JOptionPane.showMessageDialog(null, "ID Struttura non valido: " + values[3], "Errore",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Indirizzi" -> {
                Indirizzo newIndirizzo = new Indirizzo(values[0], values[1], values[2], values[3], values[4]);
                indirizzoService.save(newIndirizzo);
            }
            case "Membri" -> {
                LocalDate dataNascita = LocalDate.parse(values[2], DATE_FORMATTER);
                String documento = values[4].isEmpty() ? null : values[4];
                Membro newMembro = new Membro(values[0], values[1], java.sql.Date.valueOf(dataNascita), values[3],
                        documento, values[5]);
                membroService.createUser(newMembro, "Creazione");
            }
            case "Strutture" -> {
                Indirizzo indirizzo = indirizzoService.findById(Integer.parseInt(values[2]));
                if (indirizzo != null) {
                    Struttura newStruttura = new Struttura(values[0], values[1], indirizzo);
                    strutturaService.save(newStruttura);
                } else {
                    JOptionPane.showMessageDialog(null, "ID Indirizzo non valido: " + values[2], "Errore",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Abbonamenti" -> {
                Membro membro = membroService.findById(Integer.parseInt(values[0]));
                Categoria categoria = categoriaService.findById(Integer.parseInt(values[2]));
                LocalDate dataFine = LocalDate.parse(values[1], DATE_FORMATTER);
                if (membro != null && categoria != null) {
                    Abbonamento newAbbonamento = new Abbonamento(membro, java.sql.Date.valueOf(dataFine), categoria);
                    abbonamentoService.save(newAbbonamento);
                } else {
                    JOptionPane.showMessageDialog(null, "ID Membro o Categoria non valido.", "Errore",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Categorie" -> {
                Categoria newCategoria = new Categoria(values[0], values[1], Double.parseDouble(values[2]));
                categoriaService.save(newCategoria);
            }
            case "Lezioni" -> {
                Corso corso = corsoService.findById(Integer.parseInt(values[0]));
                LocalDate data = LocalDate.parse(values[1], DATE_FORMATTER);
                LocalTime orario = LocalTime.parse(values[2]);

                if (corso != null) {
                    Lezione newLezione = new Lezione(corso, java.sql.Date.valueOf(data), java.sql.Time.valueOf(orario),
                            values[3], Integer.parseInt(values[4]));
                    lezioneService.save(newLezione);
                } else {
                    JOptionPane.showMessageDialog(null, "ID Corso non valido: ", "Errore",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Attrezzi" -> {
                Corso corso = corsoService.findById(Integer.parseInt(values[3]));
                if (corso != null) {
                    Attrezzo newAttrezzo = new Attrezzo(values[0], values[1], Integer.parseInt(values[2]), corso);
                    attrezzoService.save(newAttrezzo);
                } else {
                    JOptionPane.showMessageDialog(null, "ID Corso non valido: ", "Errore",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Accessi" -> {
                int idCategoria = Integer.parseInt(values[0]);
                int idStruttura = Integer.parseInt(values[1]);
                Accesso newAccesso = new Accesso(idCategoria, idStruttura);
                accessoService.save(newAccesso);
            }
            case "Conduzioni" -> {
                Conduzione conduzione = new Conduzione(
                    Integer.parseInt(values[0]),
                    Integer.parseInt(values[1]),
                    Boolean.parseBoolean(values[2]));
                conduzioneService.save(conduzione);
            }
            default -> throw new IllegalArgumentException("Unknown entity: " + entityName);
        }
    }

    @Override
    public void updateItem(String entityName, Object id, String[] values) {
        switch (entityName) {
            case "Corsi" -> {
                Corso corso = corsoService.findById((int) id);
                Struttura struttura = strutturaService.findById(Integer.parseInt(values[3]));
                if (struttura != null) {
                    corso.setTitolo(values[0]);
                    corso.setDescrizione(values[1]);
                    corso.setFasciaOraria(values[2]);
                    corso.setStruttura(struttura);
                    corsoService.update(corso);
                } else {
                    JOptionPane.showMessageDialog(null, "ID Struttura non valido: " + values[3], "Errore",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Indirizzi" -> {
                Indirizzo indirizzo = indirizzoService.findById((int) id);
                indirizzo.setStato(values[0]);
                indirizzo.setCitta(values[1]);
                indirizzo.setVia(values[2]);
                indirizzo.setNumeroCivico(values[3]);
                indirizzo.setCap(values[4]);
                indirizzoService.update(indirizzo);
            }
            case "Membri" -> {
                LocalDate dataNascita = LocalDate.parse(values[2], DATE_FORMATTER);
                Membro membro = membroService.findById((int) id);
                membro.setNome(values[0]);
                membro.setCognome(values[1]);
                membro.setDataNascita(java.sql.Date.valueOf(dataNascita));
                membro.setEmail(values[3]);
                membro.setDocumento(values[4]);
                membro.setPassword(values[5]);
                membroService.update(membro);
            }
            case "Strutture" -> {
                Struttura struttura = strutturaService.findById((int) id);
                Indirizzo indirizzo = indirizzoService.findById(Integer.parseInt(values[2]));
                if (indirizzo != null) {
                    struttura.setNome(values[0]);
                    struttura.setDescrizione(values[1]);
                    struttura.setIndirizzo(indirizzo);
                    strutturaService.update(struttura);
                } else {
                    JOptionPane.showMessageDialog(null, "ID Indirizzo non valido: " + values[2], "Errore",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Abbonamenti" -> {
                Abbonamento abbonamento = abbonamentoService.findById((int) id);
                Membro membro = membroService.findById(Integer.parseInt(values[0]));
                Categoria categoria = categoriaService.findById(Integer.parseInt(values[2]));
                LocalDate dataFine = LocalDate.parse(values[1], DATE_FORMATTER);
                if (membro != null && categoria != null) {
                    abbonamento.setMembro(membro);
                    abbonamento.setDataFine(java.sql.Date.valueOf(dataFine));
                    abbonamento.setCategoria(categoria);
                    abbonamentoService.update(abbonamento);
                } else {
                    JOptionPane.showMessageDialog(null, "ID Membro o Categoria non valido.", "Errore",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Categorie" -> {
                Categoria categoria = categoriaService.findById((int) id);
                categoria.setNome(values[0]);
                categoria.setDescrizione(values[1]);
                categoria.setPrezzo(Double.parseDouble(values[2]));
                categoriaService.update(categoria);
            }
            case "Lezioni" -> {
                Lezione lezione = lezioneService.findById((int) id);
                Corso corso = corsoService.findById(Integer.parseInt(values[0]));
                LocalDate data = LocalDate.parse(values[1], DATE_FORMATTER);
                LocalTime orario = LocalTime.parse(values[2]);
                if (corso != null) {
                    lezione.setCorso(corso);
                    lezione.setData(java.sql.Date.valueOf(data));
                    lezione.setOrario(java.sql.Time.valueOf(orario));
                    lezione.setDescrizione(values[3]);
                    lezione.setMaxPersone(Integer.parseInt(values[4]));
                    lezioneService.update(lezione);
                } else {
                    JOptionPane.showMessageDialog(null, "ID Corso non valido: " + values[0], "Errore",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Attrezzi" -> {
                Attrezzo attrezzo = attrezzoService.findById((int) id);
                Corso corso = corsoService.findById(Integer.parseInt(values[3]));
                if (corso != null) {
                attrezzo.setNome(values[0]);
                attrezzo.setDescrizione(values[1]);
                attrezzo.setQuantita(Integer.parseInt(values[2]));
                attrezzo.setCorso(corso);
                attrezzoService.update(attrezzo);
                } else {
                    JOptionPane.showMessageDialog(null, "ID Corso non valido.", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            } 
            case "Accessi" -> {
                AccessoId accessoId = (AccessoId) id;
                Accesso accesso = accessoService.findById(accessoId);
                if (accesso != null) {
                    accesso.setIdCategoria(Integer.parseInt(values[0]));
                    accesso.setIdStruttura(Integer.parseInt(values[1]));
                    accessoService.update(accesso);
                } else {
                    JOptionPane.showMessageDialog(null, "ID Accesso non valido.", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Conduzioni" -> {
                ConduzioneId conduzioneId = (ConduzioneId) id;
                Conduzione conduzione = conduzioneService.findById(conduzioneId);
                if (conduzione != null) {
                    conduzione.setIdCorso(Integer.parseInt(values[0]));
                    conduzione.setIdMembro(Integer.parseInt(values[1]));
                    conduzione.setCoordinatore(Boolean.parseBoolean(values[2]));
                    conduzioneService.update(conduzione);
                } else {
                    JOptionPane.showMessageDialog(null, "ID Conduzione non valido.", "Errore", JOptionPane.ERROR_MESSAGE);
                }
                
                
            }
            default -> throw new IllegalArgumentException("Unknown entity: " + entityName);
        }
    }

    @Override
    public void deleteItem(String entityName, Object id) {
        switch (entityName) {
            case "Corsi" -> corsoService.deleteById((int) id);
            case "Indirizzi" -> indirizzoService.deleteById((int) id);
            case "Membri" -> membroService.deleteById((int) id);
            case "Strutture" -> strutturaService.deleteById((int) id);
            case "Abbonamenti" -> abbonamentoService.deleteById((int) id);
            case "Categorie" -> categoriaService.deleteById((int) id);
            case "Lezioni" -> lezioneService.deleteById((int) id);
            case "Attrezzi" -> attrezzoService.deleteById((int) id);
            case "Accessi" -> accessoService.deleteById((AccessoId) id);
            case "Conduzioni" -> conduzioneService.deleteById((ConduzioneId) id);
            default -> throw new IllegalArgumentException("Unknown entity: " + entityName);
        }
    }
}