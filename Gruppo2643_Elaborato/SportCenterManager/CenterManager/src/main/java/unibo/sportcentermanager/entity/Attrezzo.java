package unibo.sportcentermanager.entity;

import javax.persistence.*;

@Entity
@Table(name = "Attrezzi")
public class Attrezzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDattrezzo")
    private int id;

    @Column(name = "Nome", nullable = false, length = 64)
    private String nome;

    @Column(name = "Descrizione")
    private String descrizione;

    @Column(name = "Quantita", nullable = false)
    private int quantita;

    @ManyToOne
    @JoinColumn(name = "IDcorso", nullable = false, foreignKey = @ForeignKey(name = "FK_Attrezzo_Corso"))
    private Corso corso;

    // Costruttori, getter e setter
    public Attrezzo() {}

    public Attrezzo(String nome, String descrizione, int quantita, Corso corso) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.quantita = quantita;
        this.corso = corso;
    }

    // Getters and setters...
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

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public Corso getCorso() {
        return corso;
    }

    public void setCorso(Corso corso) {
        this.corso = corso;
    }
}