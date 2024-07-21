package unibo.sportcentermanager.controller.api;

import java.util.List;

public interface BaseController<T> {
    List<T> getAll();
    T getById(int id);
    void create(T entity);
    void update(int id, T entity);
    void delete(int id);
}