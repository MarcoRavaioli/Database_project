package unibo.sportcentermanager.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unibo.sportcentermanager.entity.Iscrizione;
import unibo.sportcentermanager.entity.Lezione;
import unibo.sportcentermanager.entity.Membro;
import unibo.sportcentermanager.repository.IscrizioneRepository;

@Service
@Transactional
public class IscrizioneService {
    @Autowired
    private IscrizioneRepository iscrizioneRepository;

    public List<Iscrizione> findAllIscrizione() {
        return iscrizioneRepository.findAll();
    }

    public Iscrizione findById(int id) {
        return iscrizioneRepository.findById(id).orElse(null);
    }

    public Iscrizione save(Iscrizione iscrizione) {
        return iscrizioneRepository.save(iscrizione);
    }

    public Iscrizione update(Iscrizione iscrizione) {
        return iscrizioneRepository.save(iscrizione);
    }

    public void deleteById(int id) {
        iscrizioneRepository.deleteById(id);
    }

    public boolean isIscritto(Membro membro, Lezione lezione) {
        return iscrizioneRepository.existsByMembroAndLezione(membro, lezione);
    }

    public void delete(Membro membro, Lezione lezione) {
        iscrizioneRepository.deleteByMembroAndLezione(membro, lezione);
    }

    public int countByLezione(Lezione lezione) {
        return iscrizioneRepository.countByLezione(lezione);
    }
}