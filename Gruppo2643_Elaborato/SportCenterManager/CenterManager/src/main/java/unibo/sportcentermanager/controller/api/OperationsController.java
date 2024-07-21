package unibo.sportcentermanager.controller.api;

import java.util.List;

public interface OperationsController {

    List<?> getAllItems(String entityName);

    void addItem(String entityName, String[] values);

    void updateItem(String entityName, Object id, String[] values);

    void deleteItem(String entityName, Object id);
}
