package unibo.sportcentermanager.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Lezioni", uniqueConstraints = {@UniqueConstraint(columnNames = {"IDcorso", "Data"})})
public class Lezione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDlezione")
    private int id;

    @ManyToOne
    @JoinColumn(name = "IDcorso", nullable = false, foreignKey = @ForeignKey(name = "FK_Lezione_Corso"))
    private Corso corso;

    @Column(name = "Data", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;

    @Column(name = "Orario", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date orario;

    @Column(name = "Descrizione")
    private String descrizione;

    @Column(name = "MaxPersone", nullable = false)
    private int maxPersone;

    // Costruttori, getter e setter
    public Lezione() {}

    public Lezione(Corso corso, Date data, Date orario, String descrizione, int maxPersone) {
        this.corso = corso;
        this.data = data;
        this.orario = orario;
        this.descrizione = descrizione;
        this.maxPersone = maxPersone;
    }

    // Getters and setters...
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Corso getCorso() {
        return corso;
    }

    public void setCorso(Corso corso) {
        this.corso = corso;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getOrario() {
        return orario;
    }

    public void setOrario(Date orario) {
        this.orario = orario;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getMaxPersone() {
        return maxPersone;
    }

    public void setMaxPersone(int maxPersone) {
        this.maxPersone = maxPersone;
    }
}