package ch.noteshub.fhnw.data.repository;

import ch.noteshub.fhnw.data.domain.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import ch.noteshub.fhnw.data.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ch.noteshub.fhnw.data.domain.Module;


@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {
    List<Notes> findByNotesTitleContaining(String title);
    List<Notes> findAll(); 
    List<Notes> findByUser(User user);
    @Query("SELECT n FROM Notes n WHERE n.module = :module")
    List<Notes> findByModule(@Param("module") Module module);
}
    
