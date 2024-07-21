package unibo.sportcentermanager.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unibo.sportcentermanager.entity.Struttura;
import unibo.sportcentermanager.repository.StrutturaRepository;

@Service
@Transactional
public class StrutturaService {
    
    @Autowired
    private StrutturaRepository strutturaRepository;

    public List<Struttura> findAllStruttura() {
        return strutturaRepository.findAll();
    }

    public void save(Struttura struttura) {
        strutturaRepository.save(struttura);
    }

    public Struttura findById(int id) {
        return strutturaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Struttura not found"));
    }

    public void update(Struttura struttura) {
        strutturaRepository.save(struttura);
    }

    public void deleteById(int id) {
        if (strutturaRepository.existsById(id)) {
            strutturaRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Struttura not found with id: " + id);
        }
    }

    public List<Struttura> findStruttureByCategoria(int categoriaId) {
        return strutturaRepository.findStruttureByCategoria(categoriaId);
    }
}