package unibo.sportcentermanager.entity;

import java.io.Serializable;
import java.util.Objects;

public class ConduzioneId implements Serializable {
    private int idCorso;
    private int idMembro;

    public ConduzioneId() {
    }

    public ConduzioneId(int idCorso, int idMembro) {
        this.idCorso = idCorso;
        this.idMembro = idMembro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConduzioneId that = (ConduzioneId) o;
        return idCorso == that.idCorso && idMembro == that.idMembro;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCorso, idMembro);
    }
}