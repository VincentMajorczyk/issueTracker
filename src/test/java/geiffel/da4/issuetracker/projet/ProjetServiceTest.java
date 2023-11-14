package geiffel.da4.issuetracker.projet;
import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import geiffel.da4.issuetracker.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ProjetServiceTest {
    private  ProjetService projetService;

    private List<Projet> projets;

    @BeforeEach
    void setUp(){
        projets = new ArrayList<>(){{
            add(new Projet(1L, "Projet 1"));
            add(new Projet(2L, "Projet 2"));
            add(new Projet(3L, "Projet 2"));
        }};
        projetService = new ProjetLocalService(projets);
    }

    @Test
    void whenGettingAll_shouldReturn3(){
        assertEquals(3, projetService.getAll().size());
    }


    @Test
    void whenGettingById_shouldReturnIfExists_andBeTheSame(){
        assertAll(
                () -> assertEquals(projets.get(0), projetService.getById(1L)),
                () -> assertEquals(projets.get(1), projetService.getById(2L)),
                () -> assertEquals(projets.get(2), projetService.getById(3L)),
                () -> assertThrows(ResourceNotFoundException.class, () -> projetService.getById(24L)),
                () -> assertThrows(ResourceNotFoundException.class, () -> projetService.getById(6L))

        );
    }

    @Test
    void whenCreating_shouldReturnSame(){
        Projet toCreate = new Projet(5L, "Projet 3");
        assertEquals(toCreate, projetService.create(toCreate));
    }

    @Test
    void whenCreatingWithSameId_shouldReturnEmpty(){
        Projet same_projet = projets.get(0);
        assertThrows(ResourceAlreadyExistsException.class, () -> projetService.create(same_projet));

    }

    @Test
    void whenUpdating_shouldModifyUser(){
        Projet initial_projet = projets.get(2);
        Projet new_user = new Projet(initial_projet.getId(), "Updaté");

        projetService.update(new_user.getId(), new_user);
        Projet updated_user = projetService.getById(initial_projet.getId());
        assertEquals(new_user, updated_user);
        assertTrue(projetService.getAll().contains(new_user));
    }

    @Test
    void whenUpdatingNonExisting_shouldThrowException() {
        Projet projet = projets.get(2);

        assertThrows(ResourceNotFoundException.class, ()->projetService.update(75L, projet));
    }

    @Test
    void whenDeletingExistingUser_shouldNotBeInUsersAnymore() {
        Projet projet = projets.get(1);
        Long id = projet.getId();

        projetService.delete(id);
        assertFalse(projetService.getAll().contains(projet));
    }

    @Test
    void whenDeletingNonExisting_shouldThrowException() {
        Long id = 68L;

        assertThrows(ResourceNotFoundException.class, ()->projetService.delete(id));
    }











}
