package unibo.sportcentermanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unibo.sportcentermanager.entity.Accesso;
import unibo.sportcentermanager.entity.AccessoId;
import unibo.sportcentermanager.repository.AccessoRepository;

import java.util.List;

@Service
public class AccessoService {
    @Autowired
    private AccessoRepository accessoRepository;

    public List<Accesso> findAllAccesso() {
        return accessoRepository.findAll();
    }

    public Accesso findById(AccessoId id) {
        return accessoRepository.findById(id).orElse(null);
    }

    public void save(Accesso accesso) {
        accessoRepository.save(accesso);
    }

    public void update(Accesso accesso) {
        accessoRepository.save(accesso);
    }

    public void deleteById(AccessoId id) {
        accessoRepository.deleteById(id);
    }
}