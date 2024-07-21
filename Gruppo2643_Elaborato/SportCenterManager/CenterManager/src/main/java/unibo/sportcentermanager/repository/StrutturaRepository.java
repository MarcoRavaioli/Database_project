package unibo.sportcentermanager.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import unibo.sportcentermanager.entity.Struttura;

@Repository
public interface StrutturaRepository extends JpaRepository<Struttura, Integer> {

    @Query("SELECT s FROM Struttura s JOIN Accesso a ON s.id = a.idStruttura WHERE a.idCategoria = :categoriaId")
    List<Struttura> findStruttureByCategoria(int categoriaId);
}