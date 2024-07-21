package unibo.sportcentermanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Corsi")
public class Corso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDcorso")
    private int id;

    @Column(name = "Titolo", nullable = false, length = 64)
    private String titolo;

    @Column(name = "Descrizione", nullable = false)
    private String descrizione;

    @Column(name = "FasciaOraria", nullable = false, length = 16)
    private String fasciaOraria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDstruttura", nullable = false)
    private Struttura struttura;

    // Costruttori, getter e setter
    public Corso() {}

    public Corso(String titolo, String descrizione, String fasciaOraria, Struttura struttura) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.fasciaOraria = fasciaOraria;
        this.struttura = struttura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getFasciaOraria() {
        return fasciaOraria;
    }

    public void setFasciaOraria(String fasciaOraria) {
        this.fasciaOraria = fasciaOraria;
    }

    public Struttura getStruttura() {
        return struttura;
    }

    public void setStruttura(Struttura struttura) {
        this.struttura = struttura;
    }
}