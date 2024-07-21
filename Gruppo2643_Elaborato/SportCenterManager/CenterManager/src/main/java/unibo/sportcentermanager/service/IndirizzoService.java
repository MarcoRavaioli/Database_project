package unibo.sportcentermanager.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unibo.sportcentermanager.entity.Indirizzo;
import unibo.sportcentermanager.repository.IndirizzoRepository;

@Service
@Transactional
public class IndirizzoService {
    
    @Autowired
    private IndirizzoRepository indirizzoRepository;

    public List<Indirizzo> findAllIndirizzo() {
        return indirizzoRepository.findAll();
    }

    public void save(Indirizzo indirizzo) {
        indirizzoRepository.save(indirizzo);
    }

    public Indirizzo findById(int id) {
        return indirizzoRepository.findById(id).orElse(null);
    }

    public void createIndirizzo(String cap, String via, String numero, String citta, String stato) {
        Indirizzo indirizzo = new Indirizzo(cap, via, numero, citta, stato);
        indirizzoRepository.save(indirizzo);
    }

    public void update(Indirizzo indirizzo) {
        indirizzoRepository.save(indirizzo);
    }

    public void deleteById(int id) {
        if (indirizzoRepository.existsById(id)) {
            indirizzoRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Indirizzo not found with id: " + id);
        }
    }
}