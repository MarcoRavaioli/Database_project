package unibo.sportcentermanager.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Accessi")
@IdClass(AccessoId.class)
public class Accesso implements Serializable {
    @Id
    @Column(name = "IDcategoria")
    private int idCategoria;

    @Id
    @Column(name = "IDstruttura")
    private int idStruttura;

    @ManyToOne
    @JoinColumn(name = "IDcategoria", insertable = false, updatable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "IDstruttura", insertable = false, updatable = false)
    private Struttura struttura;

    public Accesso() {
    }

    public Accesso(int idCategoria, int idStruttura) {
        this.idCategoria = idCategoria;
        this.idStruttura = idStruttura;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdStruttura() {
        return idStruttura;
    }

    public void setIdStruttura(int idStruttura) {
        this.idStruttura = idStruttura;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Struttura getStruttura() {
        return struttura;
    }

    public void setStruttura(Struttura struttura) {
        this.struttura = struttura;
    }
}