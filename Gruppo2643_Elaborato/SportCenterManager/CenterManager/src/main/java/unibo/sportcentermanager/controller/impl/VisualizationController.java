package unibo.sportcentermanager.controller.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import unibo.sportcentermanager.entity.Corso;
import unibo.sportcentermanager.entity.Iscrizione;
import unibo.sportcentermanager.entity.Categoria;
import unibo.sportcentermanager.entity.Lezione;
import unibo.sportcentermanager.entity.Membro;
import unibo.sportcentermanager.entity.Struttura;
import unibo.sportcentermanager.entity.Abbonamento;
import unibo.sportcentermanager.service.CorsoService;
import unibo.sportcentermanager.service.StrutturaService;
import unibo.sportcentermanager.service.AbbonamentoService;
import unibo.sportcentermanager.service.CategoriaService;
import unibo.sportcentermanager.service.LezioneService;
import unibo.sportcentermanager.service.IscrizioneService;
import unibo.sportcentermanager.service.MembroService;

@Controller
public class VisualizationController {
    @Autowired
    private CorsoService corsoService;
    @Autowired
    private StrutturaService strutturaService;
    @Autowired
    private AbbonamentoService abbonamentoService;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private LezioneService lezioneService;
    @Autowired
    private MembroService membroService;
    @Autowired
    private IscrizioneService iscrizioneService;

    public List<Corso> getCorsiDisponibili(int membroId) {
        // Ottieni il membro specificato
        Membro membro = membroService.findById(membroId);
        if (membro == null) {
            throw new IllegalArgumentException("ID Membro non valido: " + membroId);
        }

        // Ottieni tutti gli abbonamenti del membro
        List<Abbonamento> abbonamenti = abbonamentoService.findByMembroId(membroId);
        
        // Filtra gli abbonamenti ancora attivi
        List<Abbonamento> abbonamentiAttivi = abbonamenti.stream()
                .filter(abbonamento -> abbonamento.getDataFine().after(new java.util.Date()))
                .collect(Collectors.toList());

        // Ottieni gli ID delle categorie degli abbonamenti attivi
        Set<Integer> categoriaIds = abbonamentiAttivi.stream()
                .map(abbonamento -> abbonamento.getCategoria().getId())
                .collect(Collectors.toSet());

        // Trova le strutture accessibili tramite la tabella Accessi usando gli ID delle categorie
        Set<Integer> strutturaIds = categoriaService.findStruttureAccessibili(categoriaIds);

        // Trova i corsi disponibili nelle strutture accessibili
        return corsoService.findCorsiByStrutture(strutturaIds);
    }

    public List<Categoria> getAbbonamentiEconomici() {
        return categoriaService.findAbbonamentiEconomici();
    }

    public List<Lezione> getLezioniPrenotate(int membroId) {
        return lezioneService.findLezioniPrenotate(membroId);
    }

    public BigDecimal getEntrateAbbonamenti() {
        return abbonamentoService.calculateEntrateTotali();
    }

    public BigDecimal getEntrateDaAbbonamentiAttivi() {
        return abbonamentoService.calculateEntrateDaAbbonamentiAttivi();
    }

    public List<Struttura> getStruttureDisponibili() {
        return strutturaService.findAllStruttura();
    }

    public List<Corso> getCorsiFrequentati() {
        return corsoService.findCorsiFrequentati();
    }

    public List<Abbonamento> getAbbonamentiByMembro(int membroId) {
        return abbonamentoService.findByMembroId(membroId);
    }

    public List<Struttura> getStruttureByCategoria(int categoriaId) {
        return strutturaService.findStruttureByCategoria(categoriaId);
    }

    public List<Corso> getCorsiByStruttura(int strutturaId) {
        return corsoService.findByStrutturaId(strutturaId);
    }

    public List<Lezione> getLezioniByCorso(int corsoId) {
        return lezioneService.findByCorsoId(corsoId);
    }

    public boolean isIscritto(int membroId, int lezioneId) {
        Membro membro = new Membro();
        membro.setId(membroId);
        Lezione lezione = new Lezione();
        lezione.setId(lezioneId);
        return iscrizioneService.isIscritto(membro, lezione);
    }

    public void prenotaLezione(int membroId, int lezioneId) {
        Membro membro = new Membro();
        membro.setId(membroId);
        Lezione lezione = new Lezione();
        lezione.setId(lezioneId);
        Iscrizione iscrizione = new Iscrizione(membro, lezione, new java.sql.Date(System.currentTimeMillis()));
        iscrizioneService.save(iscrizione);
    }

    public void disdiciLezione(int membroId, int lezioneId) {
        Membro membro = new Membro();
        membro.setId(membroId);
        Lezione lezione = new Lezione();
        lezione.setId(lezioneId);
        iscrizioneService.delete(membro, lezione);
    }

    public List<Lezione> getLezioni() {
        return lezioneService.findAllLezione();
    }

    public int getNumeroPartecipanti(Lezione lezione) {
        return iscrizioneService.countByLezione(lezione);
    }

    public Map<Corso, List<Lezione>> getCorsiConLezioni() {
        return lezioneService.findAllLezione().stream().collect(Collectors.groupingBy(Lezione::getCorso));
    }

    public List<Map.Entry<Corso, Double>> getCorsiOrdinatiPerMediaPartecipazione() {
        Map<Corso, List<Lezione>> corsiConLezioni = getCorsiConLezioni();
        Map<Corso, Double> corsoMediaPartecipazione = new HashMap<>();

        corsiConLezioni.forEach((corso, lezioni) -> {
            double mediaPartecipazione = lezioni.stream()
                    .mapToDouble(lezione -> (double) getNumeroPartecipanti(lezione) / lezione.getMaxPersone())
                    .average()
                    .orElse(0.0);
            corsoMediaPartecipazione.put(corso, mediaPartecipazione);
        });

        return corsoMediaPartecipazione.entrySet().stream()
                .sorted(Map.Entry.<Corso, Double>comparingByValue().reversed())
                .collect(Collectors.toList());
    }

    public List<Abbonamento> getAbbonamentiInScadenza(int membroId, int giorni) {
        List<Abbonamento> abbonamenti = abbonamentoService.findByMembroId(membroId);
        LocalDate now = LocalDate.now();
        return abbonamenti.stream()
            .filter(a -> {
                LocalDate dataFine = convertToLocalDateViaSqlDate(a.getDataFine());
                long daysBetween = ChronoUnit.DAYS.between(now, dataFine);
                return daysBetween <= giorni && daysBetween >= 0;
            })
            .collect(Collectors.toList());
    }

    private LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }
}