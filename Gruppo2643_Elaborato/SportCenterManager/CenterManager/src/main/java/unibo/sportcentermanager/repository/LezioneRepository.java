package unibo.sportcentermanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import unibo.sportcentermanager.entity.Lezione;

public interface LezioneRepository extends JpaRepository<Lezione, Integer> {

    @Query("SELECT l FROM Lezione l JOIN Iscrizione i ON l.id = i.lezione.id WHERE i.membro.id = :membroId")
    List<Lezione> findLezioniPrenotate(@Param("membroId") int membroId);

    List<Lezione> findByCorsoId(int corsoId);
}