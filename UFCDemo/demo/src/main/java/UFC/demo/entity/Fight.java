package UFC.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.id.factory.spi.GenerationTypeStrategy;

//@Entity
//@Table(name = "fight")
//@Data
public class Fight {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "event_id", referencedColumnName = "id")
//    private Event event;
//
//    @OneToOne
//    @JoinColumn(name = "fight_winner_id", referencedColumnName = "id")
//    private FightWinner fightWinner;
//
//    @OneToOne
//    @JoinColumn(name = "fight_loser_id", referencedColumnName = "id")
//    private Long fightLoser;
//
//    @Column(name = "method")
//    private String method;
//
//    @Column(name = "details")
//    private String details;
//
//    @Column(name = "round")
//    private Long round;
//
//    @Column(name = "time")
//    private String time;
//
//    @Column(name = "ref")
//    private String ref;
}
