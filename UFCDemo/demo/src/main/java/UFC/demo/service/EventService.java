package UFC.demo.service;

import UFC.demo.entity.Event;
import UFC.demo.repository.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventService {

    private EventRepo eventRepo;

    @Autowired
    public EventService(EventRepo eventRepo){
        this.eventRepo = eventRepo;
    }

    public void postEvent(Event event){
        Event event1 = new Event();
        event1.setEventName(event.getEventName());
        event1.setEventDate(event.getEventDate());
        event1.setEventLocation(event.getEventLocation());

        eventRepo.save(event1);
    }

    public List<Event> getAllEventes(){
        return eventRepo.findAll();
    }

    public boolean isTableEmpty(){
        return eventRepo.count() == 0;
    }
}
