package ch.noteshub.fhnw.data.repository;

import ch.noteshub.fhnw.data.domain.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ch.noteshub.fhnw.data.domain.Location;
import java.util.List;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, Long> {
        @Query("SELECT d FROM Degree d WHERE d.location = :location")
        List<Degree> findByLocation(@Param("location") Location location);
}
