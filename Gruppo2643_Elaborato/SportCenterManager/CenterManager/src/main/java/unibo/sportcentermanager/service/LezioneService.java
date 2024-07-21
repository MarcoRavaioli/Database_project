package unibo.sportcentermanager.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unibo.sportcentermanager.entity.Lezione;
import unibo.sportcentermanager.repository.LezioneRepository;

@Service
@Transactional
public class LezioneService {

    @Autowired
    private LezioneRepository lezioneRepository;

    public List<Lezione> findAllLezione() {
        return lezioneRepository.findAll();
    }

    public void save(Lezione lezione) {
        lezioneRepository.save(lezione);
    }

    public Lezione findById(int id) {
        return lezioneRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Lezione not found"));
    }

    public void update(Lezione lezione) {
        lezioneRepository.save(lezione);
    }

    public void deleteById(int id) {
        if (lezioneRepository.existsById(id)) {
            lezioneRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Lezione not found with id: " + id);
        }
    }

    public List<Lezione> findLezioniPrenotate(int membroId) {
        return lezioneRepository.findLezioniPrenotate(membroId);
    }

    public List<Lezione> findByCorsoId(int corsoId) {
        return lezioneRepository.findByCorsoId(corsoId);
    }
}