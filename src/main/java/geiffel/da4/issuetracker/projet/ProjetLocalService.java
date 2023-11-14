package geiffel.da4.issuetracker.projet;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import geiffel.da4.issuetracker.user.User;
import geiffel.da4.issuetracker.utils.LocalService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjetLocalService  extends LocalService<Projet, Long> implements ProjetService {
    public ProjetLocalService(List<Projet> projets) {
        super(projets);
    }

    @Override
    protected String getIdentifier() {
        return "id";
    }

    @Override
    public List<Projet> getAll() {
        return super.getAll();
    }

    @Override
    public Projet getById(Long id) {
        return this.getByIdentifier(id);
    }

    @Override
    public Projet create(Projet toCreate) throws ResourceAlreadyExistsException {
        try{
            getByIdentifier(toCreate.getId());
            throw new ResourceAlreadyExistsException("Projet", toCreate.getId());
        }
        catch (ResourceNotFoundException e){
            super.allValues.add(toCreate);
            return toCreate;

        }
    }

    @Override
    public void update(Long id, Projet newProjet) throws ResourceNotFoundException {
        IndexAndValue<Projet> found = this.findByProperty(id);
        this.allValues.remove(found.index());
        this.allValues.add(found.index(), newProjet);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        IndexAndValue<Projet> found = this.findByProperty(id);
        this.allValues.remove(found.value());
    }
}
