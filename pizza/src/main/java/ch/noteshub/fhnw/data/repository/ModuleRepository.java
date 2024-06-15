package ch.noteshub.fhnw.data.repository;

import ch.noteshub.fhnw.data.domain.Degree;
import ch.noteshub.fhnw.data.domain.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    @Query("SELECT m FROM Module m WHERE m.degree.degreeId = :degreeId")
    List<Module> findByDegreeId(@Param("degreeId") Long degreeId);
}
