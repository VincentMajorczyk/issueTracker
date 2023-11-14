package geiffel.da4.issuetracker.projet;

import java.util.Objects;

public class Projet {
    private Long id;
    private String nom;

    public Projet(Long unId, String unNom){
        this.id = unId;
        this.nom = unNom;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Projet projet = (Projet) o;
        return Objects.equals(id, projet.id) && Objects.equals(nom, projet.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom);
    }
}
