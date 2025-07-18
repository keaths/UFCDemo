package UFC.demo.service;

import UFC.demo.entity.Round;
import UFC.demo.repository.RoundRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoundService {

    private RoundRepo roundRepo;

    @Autowired
    public RoundService(RoundRepo roundRepo){
        this.roundRepo = roundRepo;
    }

    @Transactional
    public void postRound(Round round){
        roundRepo.save(round);
    }

}
