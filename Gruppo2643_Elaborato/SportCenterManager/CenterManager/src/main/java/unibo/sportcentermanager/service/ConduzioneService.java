package unibo.sportcentermanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unibo.sportcentermanager.entity.Conduzione;
import unibo.sportcentermanager.entity.ConduzioneId;
import unibo.sportcentermanager.repository.ConduzioneRepository;

@Service
public class ConduzioneService {
    @Autowired
    private ConduzioneRepository conduzioneRepository;

    public List<Conduzione> findAllConduzione() {
        return conduzioneRepository.findAll();
    }

    public void save(Conduzione conduzione) {
        conduzioneRepository.save(conduzione);
    }

    public Conduzione findById(ConduzioneId id) {
        return conduzioneRepository.findById(id).orElse(null);
    }

    public void update(Conduzione conduzione) {
        conduzioneRepository.save(conduzione);
    }

    public void deleteById(ConduzioneId id) {
        conduzioneRepository.deleteById(id);
    }
}
