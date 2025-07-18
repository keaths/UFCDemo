package UFC.demo.controller;

import UFC.demo.entity.Fight;
import UFC.demo.service.FightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/fights")
public class FightController {

    private FightService fightService;

    private FightController(FightService fightService){
        this.fightService = fightService;
    }

    @GetMapping("/getFights")
    public List<Fight> getFights(){
        return fightService.getFights();
    }

    @GetMapping("/getFightsByEvent")
    public List<Fight> getFightsByEvent(@RequestParam("event") Long Id){
        return fightService.findByEvent(Id);
    }

    @GetMapping("/getFightById")
    public Optional<Fight> getFightById(@RequestParam("fight") Long Id){
        return fightService.getFightById(Id);
    }

    @GetMapping("/getLastFight")
    public Fight getLastFight(){
        return fightService.getLastFight();
    }

    @DeleteMapping("/deleteFightById")
    public void deleteFightById(@RequestParam("fight") Long Id){
        fightService.deleteFightById(Id);
    }

    @GetMapping("/getCount")
    public int getCount(){
        return fightService.getCount();
    }

}
