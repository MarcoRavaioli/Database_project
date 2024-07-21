package unibo.sportcentermanager.controller.api;

import unibo.sportcentermanager.entity.Membro;

public interface AuthenticationController {

    Membro authenticateUser(String email, String password);

    void registerNewUser(Membro utente);
    
    void authenticateAdmin(String password);
}
