package unibo.sportcentermanager.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import unibo.sportcentermanager.entity.Corso;

public interface CorsoRepository extends JpaRepository<Corso, Integer> {

    @Query("SELECT c FROM Corso c JOIN Accesso a ON c.struttura.id = a.struttura.id WHERE a.categoria.id = :categoriaId")
    List<Corso> findCorsiDisponibili(int categoriaId);

    @Query("SELECT c, COUNT(i) AS numPartecipazioni FROM Corso c JOIN Lezione l ON c.id = l.corso.id JOIN Iscrizione i ON l.id = i.lezione.id GROUP BY c.id ORDER BY numPartecipazioni DESC")
    List<Corso> findCorsiFrequentati();

    @Query("SELECT c FROM Corso c JOIN c.struttura s WHERE s.id IN :strutturaIds")
    List<Corso> findCorsiByStrutture(Set<Integer> strutturaIds);

    @Query("SELECT c FROM Corso c WHERE c.struttura.id = :strutturaId")
    List<Corso> findByStrutturaId(int strutturaId);
}