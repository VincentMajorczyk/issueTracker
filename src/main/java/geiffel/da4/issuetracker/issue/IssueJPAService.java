package geiffel.da4.issuetracker.issue;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import geiffel.da4.issuetracker.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("jpa")
public class IssueJPAService implements IssueService{
    String errorname = "Issue";

    @Autowired
    private IssueRepository issueRepository;

    @Override
    public List<Issue> getAll() {
        return issueRepository.findAll();
    }

    @Override
    public Issue getByCode(Long code) {
        Optional<Issue> issue = issueRepository.findById(code);
        if (issue.isPresent()) {
            return issue.get();
        } else {
            throw new ResourceNotFoundException(errorname, code);
        }
    }

    @Override
    public Issue create(Issue newIssue) throws ResourceAlreadyExistsException {
        if(issueRepository.existsById(newIssue.getCode())){
            throw new ResourceAlreadyExistsException(errorname, newIssue.getCode());
        }
        else{
            return issueRepository.save(newIssue);
        }
    }

    @Override
    public void update(Long code, Issue updatedIssue) throws ResourceNotFoundException {
        if(issueRepository.existsById(code)){
            issueRepository.save(updatedIssue);
        }
        else {
            throw new ResourceNotFoundException(errorname, code);
        }
    }

    @Override
    public void delete(Long code) {
        issueRepository.deleteById(code);
    }
}
