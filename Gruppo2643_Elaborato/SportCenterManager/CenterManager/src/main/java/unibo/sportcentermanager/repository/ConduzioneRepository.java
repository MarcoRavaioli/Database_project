package unibo.sportcentermanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import unibo.sportcentermanager.entity.Conduzione;
import unibo.sportcentermanager.entity.ConduzioneId;

public interface ConduzioneRepository extends JpaRepository<Conduzione, ConduzioneId> {
    
}