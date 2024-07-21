package unibo.sportcentermanager.service;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unibo.sportcentermanager.entity.Abbonamento;
import unibo.sportcentermanager.repository.AbbonamentoRepository;

@Service
@Transactional
public class AbbonamentoService {

        @Autowired
    private AbbonamentoRepository abbonamentoRepository;

    public List<Abbonamento> findAllAbbonamento() {
        return abbonamentoRepository.findAll();
    }

    public void save(Abbonamento abbonamento) {
        abbonamentoRepository.save(abbonamento);
    }

    public Abbonamento findById(int id) {
        return abbonamentoRepository.findById(id).orElse(null);
    }

    public void update(Abbonamento abbonamento) {
        abbonamentoRepository.save(abbonamento);
    }

    public void deleteById(int id) {
        if (abbonamentoRepository.existsById(id)) {
            abbonamentoRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Corso not found with id: " + id);
        }
    }

    public List<Abbonamento> findByMembroId(int membroId) {
        var list = abbonamentoRepository.findByMembroId(membroId);
        return list.isEmpty() ? List.of() : list;
    }

    public BigDecimal calculateEntrateTotali() {
        return abbonamentoRepository.findEntrateTotali();
    }

    public BigDecimal calculateEntrateDaAbbonamentiAttivi() {
        return abbonamentoRepository.findEntrateDaAbbonamentiAttivi();
    }
}
