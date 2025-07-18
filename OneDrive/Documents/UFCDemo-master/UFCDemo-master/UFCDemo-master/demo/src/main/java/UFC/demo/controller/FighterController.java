package UFC.demo.controller;

import UFC.demo.entity.Fighter;
import UFC.demo.service.FighterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/fighters")
public class FighterController {

    private FighterService fighterService;

    public FighterController(FighterService fighterService){
        this.fighterService = fighterService;
    }

    @PostMapping("/test/post")
    public void postFighter(@RequestBody Fighter fighter){
        fighterService.postFighter(fighter);
    }

    @GetMapping("/test/get")
    public List<Fighter> getAllFighters(){
        return fighterService.getAllFighters();
    }

    @GetMapping("/test/getSingle")
    public Fighter getFighter(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String nickname){
        return fighterService.findByFirstNameAndLastNameAndNickName(firstName, lastName, nickname);
    }

}
