package unibo.sportcentermanager.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import unibo.sportcentermanager.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    List<Categoria> findTop10ByOrderByPrezzoAsc();

    @Query("SELECT a.struttura.id FROM Accesso a WHERE a.categoria.id IN :categoriaIds")
    Set<Integer> findStruttureAccessibili(Set<Integer> categoriaIds);
}