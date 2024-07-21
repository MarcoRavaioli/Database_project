package unibo.sportcentermanager.view.detailframes;

import javax.swing.JFrame;

public class FrameFactory {
    public static JFrame createInputFrame(String entityName) {
        return switch (entityName) {
            case "Membri" -> new MembroInputFrame();
            case "Indirizzi" -> new IndirizzoInputFrame();
            case "Strutture" -> new StrutturaInputFrame();
            case "Abbonamenti" -> new AbbonamentoInputFrame();
            case "Categorie" -> new CategoriaInputFrame();
            case "Corsi" -> new CorsoInputFrame();
            case "Lezioni" -> new LezioneInputFrame();
            case "Attrezzi" -> new AttrezzoInputFrame();
            case "Accessi" -> new AccessoInputFrame();
            case "Conduzioni" -> new ConduzioneInputFrame();
            default -> throw new IllegalArgumentException("Unknown entity: " + entityName);
        };
    }

    public static JFrame createEditFrame(String entityName, String[] currentValues) {
        return switch (entityName) {
            case "Membri" -> new MembroEditFrame(currentValues[0], currentValues[1], currentValues[2], currentValues[3], currentValues[4], currentValues[5]);
            case "Indirizzi" -> new IndirizzoEditFrame(currentValues[0], currentValues[1], currentValues[2], currentValues[3], currentValues[4]);
            case "Strutture" -> new StrutturaEditFrame(currentValues);
            case "Abbonamenti" -> new AbbonamentoEditFrame(currentValues);
            case "Categorie" -> new CategoriaEditFrame(currentValues);
            case "Corsi" -> new CorsoEditFrame(currentValues);
            case "Lezioni" -> new LezioneEditFrame(currentValues);
            case "Attrezzi" -> new AttrezzoEditFrame(currentValues);
            case "Accessi" -> new AccessoEditFrame(currentValues);
            case "Conduzioni" -> new ConduzioneEditFrame(currentValues);
            default -> throw new IllegalArgumentException("Unknown entity: " + entityName);
        };
    }
}