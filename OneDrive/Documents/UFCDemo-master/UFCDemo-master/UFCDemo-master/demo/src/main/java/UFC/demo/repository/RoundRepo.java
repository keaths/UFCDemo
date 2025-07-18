package UFC.demo.repository;

import UFC.demo.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepo extends JpaRepository<Round, Long> {

}
