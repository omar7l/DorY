package ch.noteshub.fhnw.data.repository;

import ch.noteshub.fhnw.data.domain.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import ch.noteshub.fhnw.data.domain.User;


@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {
    List<Notes> findByNotesTitleContaining(String title);
    List<Notes> findAll(); 
    List<Notes> findByUser(User user);
}
    
