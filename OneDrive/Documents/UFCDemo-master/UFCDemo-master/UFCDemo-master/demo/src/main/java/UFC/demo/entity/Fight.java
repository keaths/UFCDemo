package UFC.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "fight")
@Data
public class Fight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fight_winner_id", referencedColumnName = "id")
    private Fighter fightWinner;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fight_loser_id", referencedColumnName = "id")
    private Fighter fightLoser;

    @Column(name = "method")
    private String method;

    @Column(name = "details")
    private String details;

    @Column(name = "round")
    private int round;

    @Column(name = "time")
    private String time;

    @Column(name = "ref")
    private String ref;
}
