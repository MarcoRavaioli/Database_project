package unibo.sportcentermanager.service;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unibo.sportcentermanager.entity.Categoria;
import unibo.sportcentermanager.repository.CategoriaRepository;

@Service
@Transactional
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAllCategoria() {
        return categoriaRepository.findAll();
    }

    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    public Categoria findById(int id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    public void update(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    public void deleteById(int id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Categoria not found with id: " + id);
        }
    }

    public Set<Integer> findStruttureAccessibili(Set<Integer> categoriaIds) {
        return categoriaRepository.findStruttureAccessibili(categoriaIds);
    }

    public List<Categoria> findAbbonamentiEconomici() {
        return categoriaRepository.findTop10ByOrderByPrezzoAsc();
    }
}
