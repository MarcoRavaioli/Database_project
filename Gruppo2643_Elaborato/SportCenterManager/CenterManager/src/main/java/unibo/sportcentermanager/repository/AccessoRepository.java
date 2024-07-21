package unibo.sportcentermanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import unibo.sportcentermanager.entity.Accesso;
import unibo.sportcentermanager.entity.AccessoId;

public interface AccessoRepository extends JpaRepository<Accesso, AccessoId> {
    
}