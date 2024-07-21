package unibo.sportcentermanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import unibo.sportcentermanager.entity.Iscrizione;
import unibo.sportcentermanager.entity.Lezione;
import unibo.sportcentermanager.entity.Membro;

@Repository
public interface IscrizioneRepository extends JpaRepository<Iscrizione, Integer> {
    @Query("SELECT l FROM Lezione l JOIN Iscrizione i ON l.id = i.lezione.id WHERE i.membro.id = :membroId")
    List<Lezione> findLezioniPrenotate(int membroId);

    boolean existsByMembroAndLezione(Membro membro, Lezione lezione);

    void deleteByMembroAndLezione(Membro membro, Lezione lezione);

    int countByLezione(Lezione lezione);
}