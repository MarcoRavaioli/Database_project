package unibo.sportcentermanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Indirizzi", uniqueConstraints = {@UniqueConstraint(columnNames = {"CAP", "Via", "NumeroCivico"})})
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDindirizzo")
    private int id;

    @Column(name = "CAP", nullable = false, length = 10)
    private String cap;

    @Column(name = "Via", nullable = false, length = 64)
    private String via;

    @Column(name = "NumeroCivico", nullable = false, length = 10)
    private String numeroCivico;

    @Column(name = "Citta", nullable = false, length = 64)
    private String citta;

    @Column(name = "Stato", nullable = false, length = 64)
    private String stato;

    // Costruttori, getter e setter
    public Indirizzo() {}

    public Indirizzo(String stato, String citta, String via, String numeroCivico, String cap) {
        this.cap = cap;
        this.via = via;
        this.numeroCivico = numeroCivico;
        this.citta = citta;
        this.stato = stato;
    }

    // Getters and setters...
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getNumeroCivico() {
        return numeroCivico;
    }

    public void setNumeroCivico(String numeroCivico) {
        this.numeroCivico = numeroCivico;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }
}