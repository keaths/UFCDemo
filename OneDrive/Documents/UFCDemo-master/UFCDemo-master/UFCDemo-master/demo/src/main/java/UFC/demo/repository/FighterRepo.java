package UFC.demo.repository;
import UFC.demo.entity.Fighter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FighterRepo extends JpaRepository<Fighter, Long> {

    Fighter findByFirstNameAndLastNameAndNickName(String firstName, String lastName, String nickname);

    Fighter findByFirstName(String firstName);

    Fighter findByFirstNameAndLastName(String firstName, String lastName);
}
