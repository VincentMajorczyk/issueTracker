package geiffel.da4.issuetracker.commentaire;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import geiffel.da4.issuetracker.issue.Issue;
import geiffel.da4.issuetracker.issue.IssueEmbeddedJSONSerializer;
import geiffel.da4.issuetracker.user.User;
import geiffel.da4.issuetracker.user.UserEmbeddedJSONSerializer;
import jakarta.persistence.*;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonSerialize(using = UserEmbeddedJSONSerializer.class)
    private User author;

    private String contenu;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonSerialize(using = IssueEmbeddedJSONSerializer.class)
    private Issue issue;

    public Commentaire(Long id, User author, Issue issue, String contenu) {
        this.id = id;
        this.author = author;
        this.issue = issue;
        this.contenu = contenu;
        this.author.addCommentaire(this);
        this.issue.addCommentaire(this);

    }

    public Commentaire() {
    }

    public Long getAuthorId(){
        return this.author.getId();
    }
    public Long getIssueCode() {
        return this.issue.getCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }


}
