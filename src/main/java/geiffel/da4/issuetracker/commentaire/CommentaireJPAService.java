package geiffel.da4.issuetracker.commentaire;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Qualifier("jpa")
public class CommentaireJPAService implements CommentaireService {

    @Autowired
    private CommentaireRepository commentaireRepository;

    @Override
    public List<Commentaire> getAll() {
        return commentaireRepository.findAll();
    }

    @Override
    public Commentaire getById(Long id) {
        Optional<Commentaire> commentaire = commentaireRepository.findById(id);
        if (commentaire.isPresent()) {
            return commentaire.get();
        } else {
            throw new ResourceNotFoundException("Issue", id);
        }
    }

    @Override
    public List<Commentaire> getAllByAuthorId(Long id) {
        List<Commentaire> fL = new ArrayList<>();
        for (Commentaire c: this.getAll()) {
            if(Objects.equals(c.getAuthor().getId(), id)){
                fL.add(c);
            }
        }
        return fL;
    }

    @Override
    public List<Commentaire> getAllByIssueCode(Long code) {
        List<Commentaire> fL = new ArrayList<>();
        for (Commentaire c: this.getAll()) {
            if(Objects.equals(c.getIssue().getCode(), code)){
                fL.add(c);
            }
        }
        return fL;
    }

    @Override
    public Commentaire create(Commentaire commentaire) {
        if(commentaireRepository.existsById(commentaire.getId())){
            throw new ResourceAlreadyExistsException("Commentaire", commentaire.getId());
        }
        else{
            return commentaireRepository.save(commentaire);
        }
    }

    @Override
    public void update(Long id, Commentaire toUpdate1) {
        if(commentaireRepository.existsById(id)){
            commentaireRepository.save(toUpdate1);
        }
        else {
            throw new ResourceNotFoundException("Commentaire", id);
        }
    }

    @Override
    public void delete(Long id) {
        commentaireRepository.deleteById(id);
    }
}
