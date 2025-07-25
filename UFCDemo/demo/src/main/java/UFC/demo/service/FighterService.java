package UFC.demo.service;

import UFC.demo.entity.Fighter;
import UFC.demo.repository.FighterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FighterService {

    private FighterRepo fighterRepo;

    @Autowired
    public FighterService(FighterRepo fighterRepo){
        this.fighterRepo = fighterRepo;
    }

    public List<Fighter> getAllFighters(){
        return fighterRepo.findAll();
    }

    public Fighter findByFirstAndLastName(String firstName, String lastName){
        return fighterRepo.findByFirstNameAndLastName(firstName, lastName);
    }

    public void postFighter(Fighter fighter){

        Fighter fighter1 = new Fighter();

        fighter1.setFirstName(fighter.getFirstName());
        fighter1.setLastName(fighter.getLastName());
        fighter1.setNickName(fighter.getNickName());
        fighter1.setHeight(fighter.getHeight());
        fighter1.setWeight(fighter.getWeight());
        fighter1.setReach(fighter.getReach());
        fighter1.setStance(fighter.getStance());
        fighter1.setWins(fighter.getWins());
        fighter1.setLosses(fighter.getLosses());
        fighter1.setDraws(fighter.getDraws());
        fighter1.setBelt(fighter.isBelt());

        fighterRepo.save(fighter1);
    }

    public boolean isTableEmpty(){
        return fighterRepo.count() == 0;
    }

}
