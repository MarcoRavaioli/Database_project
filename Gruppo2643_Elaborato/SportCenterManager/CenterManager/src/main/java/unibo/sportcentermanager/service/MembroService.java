package unibo.sportcentermanager.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unibo.sportcentermanager.entity.Membro;
import unibo.sportcentermanager.repository.MembroRepository;

@Service
@Transactional
public class MembroService {

    @Autowired
    private MembroRepository membroRepository;

    public void createUser(final Membro membro, final String modality) {
        if ("Creazione".equals(modality)) {
            final Optional<Membro> existingUser = membroRepository.findByEmail(membro.getEmail());
            if (existingUser.isPresent()) {
                throw new UserAlreadyExistsException("Email già registrata");
            }
        }
        System.out.println("nuovo membro passato da membroService");
        membroRepository.save(membro);
    }

    public void save(Membro membro) {
        membroRepository.save(membro);
    }

    public Membro authenticateUser(final String email, final String password) {
        final Optional<Membro> optionalMembro = membroRepository.findByEmail(email);
        if (optionalMembro.isPresent()) {
            final Membro membro = optionalMembro.get();
            if (membro.getPassword().equals(password)) {
                return membro;
            } else {
                throw new WrongPasswordException("Password errata");
            }
        } else {
            throw new UserNotFoundException("Email non registrata");
        }
    }

    public void authenticateAdmin(final String password) {
        if (!password.equals("admin321")) {
            throw new WrongPasswordException("Password errata");
        }
    }

    public Membro findById(int id) {
        return membroRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Membro not found"));
    }

    public void update(Membro membro) {
        membroRepository.save(membro);
    }

    public void deleteById(int id) {
        if (membroRepository.existsById(id)) {
            membroRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Membro not found with id: " + id);
        }
    }

    public List<Membro> findAllMembro() {
        return membroRepository.findAll();
    }

    public class UserAlreadyExistsException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public UserAlreadyExistsException(final String message) {
            super(message);
        }
    }

    public class WrongPasswordException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public WrongPasswordException(final String message) {
            super(message);
        }
    }

    public class AdminNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public AdminNotFoundException(final String message) {
            super(message);
        }
    }

    public class UserNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public UserNotFoundException(final String message) {
            super(message);
        }
    }
}
