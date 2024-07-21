package unibo.sportcentermanager.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Conduzioni")
@IdClass(ConduzioneId.class)
public class Conduzione implements Serializable {
    @Id
    @Column(name = "IDcorso")
    private int idCorso;

    @Id
    @Column(name = "IDmembro")
    private int idMembro;

    @Column(name = "IsCoordinatore")
    private boolean isCoordinatore;

    @ManyToOne
    @JoinColumn(name = "IDcorso", insertable = false, updatable = false)
    private Corso corso;

    @ManyToOne
    @JoinColumn(name = "IDmembro", insertable = false, updatable = false)
    private Membro membro;

    public Conduzione() {
    }

    public Conduzione(int idCorso, int idMembro, boolean isCoordinatore) {
        this.idCorso = idCorso;
        this.idMembro = idMembro;
        this.isCoordinatore = isCoordinatore;
    }

    public int getIdCorso() {
        return idCorso;
    }

    public void setIdCorso(int idCorso) {
        this.idCorso = idCorso;
    }

    public int getIdMembro() {
        return idMembro;
    }

    public void setIdMembro(int idMembro) {
        this.idMembro = idMembro;
    }

    public boolean isCoordinatore() {
        return isCoordinatore;
    }

    public void setCoordinatore(boolean isCoordinatore) {
        this.isCoordinatore = isCoordinatore;
    }

    public Corso getCorso() {
        return corso;
    }

    public void setCorso(Corso corso) {
        this.corso = corso;
    }

    public Membro getMembro() {
        return membro;
    }

    public void setMembro(Membro membro) {
        this.membro = membro;
    }
}