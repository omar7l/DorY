package ch.noteshub.fhnw.data.repository;

import ch.noteshub.fhnw.data.domain.Favorite;
import ch.noteshub.fhnw.data.domain.Notes;
import ch.noteshub.fhnw.data.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUser(User user);
    List<Favorite> findByNotes(Notes notes);
    Favorite findByUserAndNotes(User user, Notes notes);
}
