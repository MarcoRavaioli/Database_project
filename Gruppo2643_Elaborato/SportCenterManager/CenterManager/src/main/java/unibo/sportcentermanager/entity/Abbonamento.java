package unibo.sportcentermanager.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Abbonamenti", uniqueConstraints = {@UniqueConstraint(columnNames = {"IDmembro", "IDcategoria"})})
public class Abbonamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDabbonamento")
    private int id;

    @ManyToOne
    @JoinColumn(name = "IDmembro", nullable = false, foreignKey = @ForeignKey(name = "FK_Abbonamento_Membro"))
    private Membro membro;

    @Column(name = "DataFine", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataFine;

    @ManyToOne
    @JoinColumn(name = "IDcategoria", nullable = false, foreignKey = @ForeignKey(name = "FK_Abbonamento_Categoria"))
    private Categoria categoria;

    // Costruttori, getter e setter
    public Abbonamento() {}

    public Abbonamento(Membro membro, Date dataFine, Categoria categoria) {
        this.membro = membro;
        this.dataFine = dataFine;
        this.categoria = categoria;
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

    public Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(Date dataFine) {
        this.dataFine = dataFine;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}