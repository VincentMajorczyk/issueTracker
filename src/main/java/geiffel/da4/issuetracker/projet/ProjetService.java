package geiffel.da4.issuetracker.projet;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProjetService{
    List<Projet> getAll();

    Projet getById(Long id);

    Projet create(Projet toCreate) throws ResourceAlreadyExistsException;

    void update(Long id, Projet newProjet) throws ResourceNotFoundException;

    void delete(Long id) throws ResourceNotFoundException;
}
