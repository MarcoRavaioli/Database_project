package unibo.sportcentermanager.service;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unibo.sportcentermanager.entity.Attrezzo;
import unibo.sportcentermanager.repository.AttrezzoRepository;

@Service
@Transactional
public class AttrezzoService {
    @Autowired
    private AttrezzoRepository attrezzoRepository;

    public List<Attrezzo> findAllAttrezzo() {
        return attrezzoRepository.findAll();
    }

    public void save(Attrezzo attrezzo) {
        attrezzoRepository.save(attrezzo);
    }

    public Attrezzo findById(int id) {
        return attrezzoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Attrezzo not found"));
    }

    public void update(Attrezzo attrezzo) {
        attrezzoRepository.save(attrezzo);
    }

    public void deleteById(int id) {
        if (attrezzoRepository.existsById(id)) {
            attrezzoRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Attrezzo not found with id: " + id);
        }
    }
}