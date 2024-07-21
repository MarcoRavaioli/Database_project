package unibo.sportcentermanager.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Iscrizioni", uniqueConstraints = @UniqueConstraint(columnNames = {"IDmembro", "IDlezione"}))
public class Iscrizione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDiscrizione")
    private int id;

    @ManyToOne
    @JoinColumn(name = "IDmembro", nullable = false)
    private Membro membro;

    @ManyToOne
    @JoinColumn(name = "IDlezione", nullable = false)
    private Lezione lezione;

    @Column(name = "Data", nullable = false)
    private Date data;

    public Iscrizione() {
    }

    public Iscrizione(Membro membro, Lezione lezione, Date data) {
        this.membro = membro;
        this.lezione = lezione;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Membro getMembro() {
        return membro;
    }

    public void setMembro(Membro membro) {
        this.membro = membro;
    }

    public Lezione getLezione() {
        return lezione;
    }

    public void setLezione(Lezione lezione) {
        this.lezione = lezione;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}