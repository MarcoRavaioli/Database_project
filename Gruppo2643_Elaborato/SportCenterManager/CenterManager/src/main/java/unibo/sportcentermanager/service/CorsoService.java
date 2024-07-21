package unibo.sportcentermanager.service;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unibo.sportcentermanager.entity.Corso;
import unibo.sportcentermanager.repository.CorsoRepository;

@Service
@Transactional
public class CorsoService {
    
    @Autowired
    private CorsoRepository corsoRepository;

    public List<Corso> findAllCorso() {
        List<Corso> corsi = corsoRepository.findAll();
        // Carica completamente le strutture associate
        corsi.forEach(corso -> {
            if (corso.getStruttura() != null) {
                corso.getStruttura().getNome(); // Forza l'inizializzazione della struttura
            }
        });
        return corsi;
    }

    public void save(Corso corso) {
        corsoRepository.save(corso);
    }

    public Corso findById(int id) {
        return corsoRepository.findById(id).orElse(null);
    }

    public void update(Corso corso) {
        corsoRepository.save(corso);
    }

    public void deleteById(int id) {
        if (corsoRepository.existsById(id)) {
            corsoRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Corso not found with id: " + id);
        }
    }

    public List<Corso> findCorsiDisponibili(int categoriaId) {
        return corsoRepository.findCorsiDisponibili(categoriaId);
    }

    public List<Corso> findByStrutturaId(int strutturaId) {
        return corsoRepository.findByStrutturaId(strutturaId);
    }

    public List<Corso> findCorsiByStrutture(Set<Integer> strutturaIds) {
        return corsoRepository.findCorsiByStrutture(strutturaIds);
    }

    public List<Corso> findCorsiFrequentati() {
        return corsoRepository.findCorsiFrequentati();
    }
}