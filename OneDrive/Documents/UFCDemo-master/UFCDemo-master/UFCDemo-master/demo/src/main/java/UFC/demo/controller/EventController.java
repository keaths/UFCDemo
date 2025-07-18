package UFC.demo.controller;

import UFC.demo.entity.Event;
import UFC.demo.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("api/events")
public class EventController {

    private EventService eventService;

    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @PostMapping("/postEvent")
    public void postEvent(@RequestBody Event event){
        eventService.postEvent(event);
    }

    @GetMapping("/getEvents")
    public List<Event> getEvents(){
        return eventService.getAllEvents();
    }

    @GetMapping("/getEventById")
    public Event getEventById(@RequestParam("event") Long Id){
        return eventService.getEventById(Id);
    }
}
