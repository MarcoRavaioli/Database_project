package unibo.sportcentermanager.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Membri")
public class Membro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDmembro")
    private int id;

    @Column(name = "Nome", nullable = false, length = 64)
    private String nome;

    @Column(name = "Cognome", nullable = false, length = 64)
    private String cognome;

    @Column(name = "DataNascita", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataNascita;

    @Column(name = "Email", nullable = false, unique = true, length = 64)
    private String email;

    @Column(name = "Documento", length = 255)
    private String documento;

    @Column(name = "Password", nullable = false, length = 64)
    private String password;

    // Costruttori, getter e setter
    public Membro() {}

    public Membro(String nome, String cognome, Date dataNascita, String email, String documento, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.email = email;
        this.documento = documento;
        this.password = password;
    }

    public boolean isIstruttore() {
        return documento != null && !documento.isEmpty();
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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}