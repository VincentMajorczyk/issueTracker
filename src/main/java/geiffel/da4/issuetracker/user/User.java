package geiffel.da4.issuetracker.user;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import geiffel.da4.issuetracker.commentaire.Commentaire;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    private Long id;
    private String nom;
    private Fonction fonction;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Commentaire> commentaireEcrits;


    public User() {
    }

    public User(Long id, String nom, Fonction fonction) {
        this.id = id;
        this.nom = nom;
        this.fonction = fonction;
        this.commentaireEcrits= new ArrayList<>();

    }

    public void addCommentaire(Commentaire commentaire){
        this.commentaireEcrits.add(commentaire);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Fonction getFonction() {
        return fonction;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }

    public List<Commentaire> getCommentaireEcrits() {
        return commentaireEcrits;
    }

    public void setCommentaireEcrits(List<Commentaire> commentaireEcrits) {
        this.commentaireEcrits = commentaireEcrits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(nom, user.nom) && fonction == user.fonction && Objects.equals(commentaireEcrits, user.commentaireEcrits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, fonction, commentaireEcrits);
    }
}
