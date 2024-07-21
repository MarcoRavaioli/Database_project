package unibo.sportcentermanager.entity;

import javax.persistence.*;

@Entity
@Table(name = "Strutture")
public class Struttura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDstruttura")
    private int id;

    @Column(name = "Nome", nullable = false, length = 64)
    private String nome;

    @Column(name = "Descrizione", nullable = false)
    private String descrizione;

    @ManyToOne
    @JoinColumn(name = "IDindirizzo", nullable = false, foreignKey = @ForeignKey(name = "FK_Struttura_Indirizzo"))
    private Indirizzo indirizzo;

    // Costruttori, getter e setter
    public Struttura() {}

    public Struttura(String nome, String descrizione, Indirizzo indirizzo) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.indirizzo = indirizzo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }
}