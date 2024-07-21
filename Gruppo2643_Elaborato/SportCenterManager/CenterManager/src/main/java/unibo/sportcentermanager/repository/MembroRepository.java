package unibo.sportcentermanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import unibo.sportcentermanager.entity.Membro;

public interface MembroRepository extends JpaRepository<Membro, Integer> {
    Optional<Membro> findByEmail(String email);
}

