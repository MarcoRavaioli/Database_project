package unibo.sportcentermanager.entity;

import java.io.Serializable;
import java.util.Objects;

public class AccessoId implements Serializable {
    private int idCategoria;
    private int idStruttura;

    public AccessoId() {
    }

    public AccessoId(int idCategoria, int idStruttura) {
        this.idCategoria = idCategoria;
        this.idStruttura = idStruttura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessoId that = (AccessoId) o;
        return idCategoria == that.idCategoria && idStruttura == that.idStruttura;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategoria, idStruttura);
    }
}