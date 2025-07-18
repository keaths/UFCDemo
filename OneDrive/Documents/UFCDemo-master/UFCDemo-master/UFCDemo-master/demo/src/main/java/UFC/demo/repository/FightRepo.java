package UFC.demo.repository;

import UFC.demo.entity.Fight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FightRepo extends JpaRepository<Fight, Long> {

    List<Fight> findByEventId(Long Id);

    Optional<Fight> findById(Long Id);

    @Query(
            value = "SELECT * FROM fight ORDER BY id DESC LIMIT 1;",
            nativeQuery = true)
    Fight getLastFight();

    void deleteFightById(Long Id);

    @Query(
            value = "select Count(event_id) from fight where event_id = (select event_id from fight order by id desc limit 1);",
            nativeQuery = true)
    Integer getCount();
}
