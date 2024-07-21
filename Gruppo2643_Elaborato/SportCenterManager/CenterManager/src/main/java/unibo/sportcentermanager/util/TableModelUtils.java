/*package unibo.sportcentermanager.util;

import java.lang.reflect.Field;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import unibo.sportcentermanager.controller.api.AdminOperationsController;

@Component
public class TableModelUtils {

    @Autowired
    private AdminOperationsController adminController;

    public DefaultTableModel getTableModel(String itemType) {
        try {
            // Recupera i dati dal controller
            List<?> items = (List<?>) adminController.getAllItems(itemType);

            if (items.isEmpty()) {
                return new DefaultTableModel(); // Ritorna un modello vuoto se non ci sono elementi
            }

            // Ottenere la classe del primo elemento nella lista
            Class<?> clazz = items.get(0).getClass();

            // Ottenere i nomi delle colonne dalla classe dell'oggetto
            Field[] fields = clazz.getDeclaredFields();
            String[] columnNames = new String[fields.length];
            for (int i = 0; i < fields.length; i++) {
                columnNames[i] = fields[i].getName();
            }

            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            // Aggiungi i dati al modello della tabella
            for (Object item : items) {
                Object[] row = new Object[fields.length];
                for (int i = 0; i < fields.length; i++) {
                    fields[i].setAccessible(true); // Rende accessibile il campo privato
                    try {
                        row[i] = fields[i].get(item); // Ottiene il valore del campo
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                model.addRow(row);
            }

            return model;
        } catch (IllegalArgumentException | SecurityException e) {
            e.printStackTrace();
            return new DefaultTableModel(); // Ritorna un modello vuoto in caso di errore
        }
    }

    public DefaultTableModel getTableModelByForeignKey(String itemType, String foreignKey, int foreignKeyValue) {
        try {
            // Recupera i dati dal controller
            List<?> items = (List<?>) adminController.getItemsByForeignKey(itemType, foreignKey, foreignKeyValue);
    
            if (items.isEmpty()) {
                return new DefaultTableModel(); // Ritorna un modello vuoto se non ci sono elementi
            }
    
            // Ottenere la classe del primo elemento nella lista
            Class<?> clazz = items.get(0).getClass();
    
            // Ottenere i nomi delle colonne dalla classe dell'oggetto
            Field[] fields = clazz.getDeclaredFields();
            String[] columnNames = new String[fields.length];
            for (int i = 0; i < fields.length; i++) {
                columnNames[i] = fields[i].getName();
            }
    
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    
            // Aggiungi i dati al modello della tabella
            for (Object item : items) {
                Object[] row = new Object[fields.length];
                for (int i = 0; i < fields.length; i++) {
                    fields[i].setAccessible(true); // Rende accessibile il campo privato
                    try {
                        row[i] = fields[i].get(item); // Ottiene il valore del campo
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                model.addRow(row);
            }
    
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            return new DefaultTableModel(); // Ritorna un modello vuoto in caso di errore
        }
    }
}*/