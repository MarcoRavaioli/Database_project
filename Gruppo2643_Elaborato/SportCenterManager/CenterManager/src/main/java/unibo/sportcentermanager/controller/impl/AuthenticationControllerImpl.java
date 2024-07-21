package unibo.sportcentermanager.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import unibo.sportcentermanager.controller.api.AuthenticationController;
import unibo.sportcentermanager.entity.Membro;
import unibo.sportcentermanager.service.MembroService;

@Controller
public class AuthenticationControllerImpl implements AuthenticationController {

    @Autowired
    private final MembroService membroService;

    public AuthenticationControllerImpl(final MembroService membroService) {
        this.membroService = membroService;
    }

    @Override
    public Membro authenticateUser(final String email, final String password) {
        return membroService.authenticateUser(email, password);
    }

    @Override
    public void registerNewUser(final Membro membro) {
        membroService.createUser(membro, "Creazione");
    }

    @Override
    public void authenticateAdmin(final String password) {
        membroService.authenticateAdmin(password);
    }
}
