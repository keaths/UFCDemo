package UFC.demo.service;

import UFC.demo.entity.Fight;
import UFC.demo.repository.FightRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FightService {

    private FightRepo fightRepo;

    @Autowired
    public FightService(FightRepo fightRepo){
        this.fightRepo = fightRepo;
    }

    public List<Fight> getFights(){
        return fightRepo.findAll();
    }

    public Optional<Fight> getFightById(Long Id){
        return fightRepo.findById(Id);
    }
    public List<Fight> findByEvent(Long Id){
        return fightRepo.findByEventId(Id);
    }

    public Fight getLastFight(){
        return fightRepo.getLastFight();
    }

    public void deleteFightById(Long Id){
        fightRepo.deleteFightById(Id);
    }

    public void postFight(Fight fight){
        Fight fight1 = new Fight();
        fight1.setEvent(fight.getEvent());
        fight1.setFightWinner(fight.getFightWinner());
        fight1.setFightLoser(fight.getFightLoser());
        fight1.setMethod(fight.getMethod());
        fight1.setDetails(fight.getDetails());
        fight1.setRound(fight.getRound());
        fight1.setTime(fight.getTime());
        fight1.setRef(fight.getRef());

        fightRepo.save(fight1);
    }

    public int getCount(){
        return fightRepo.getCount();
    }

    public boolean isTableEmpty(){
        return fightRepo.count() == 0;
    }
}
