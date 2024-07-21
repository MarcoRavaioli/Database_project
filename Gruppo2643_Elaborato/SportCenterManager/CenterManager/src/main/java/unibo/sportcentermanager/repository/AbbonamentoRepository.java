package unibo.sportcentermanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

import java.util.List;

import unibo.sportcentermanager.entity.Abbonamento;

public interface AbbonamentoRepository extends JpaRepository<Abbonamento, Integer> {
    
    @Query("SELECT a FROM Abbonamento a WHERE a.dataFine >= CURRENT_DATE ORDER BY a.dataFine ASC")
    List<Abbonamento> findAbbonamentiProssimiAllaScadenza();

    @Query("SELECT SUM(c.prezzo) FROM Abbonamento a JOIN a.categoria c")
    BigDecimal findEntrateTotali();

    @Query("SELECT SUM(c.prezzo) FROM Abbonamento a JOIN a.categoria c WHERE a.dataFine >= CURRENT_DATE")
    BigDecimal findEntrateDaAbbonamentiAttivi();

    List<Abbonamento> findByMembroId(int membroId);
}
