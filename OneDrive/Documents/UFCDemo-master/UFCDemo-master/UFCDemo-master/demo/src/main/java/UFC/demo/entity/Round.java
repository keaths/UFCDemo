package UFC.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.tomcat.util.buf.C2BConverter;

@Entity
@Table(name = "round")
@Data
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fight_id", referencedColumnName = "id")
    private Fight fight;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fighter_id", referencedColumnName = "id")
    private Fighter fighter;

    @Column(name = "round_no")
    private int roundNo;

    @Column(name = "head_landed")
    private int headLanded;

    @Column(name = "head_attempt")
    private int headAttempt;

    @Column(name = "body_landed")
    private int bodyLanded;

    @Column(name = "body_attempt")
    private int bodyAttempt;

    @Column(name = "leg_landed")
    private int legLanded;

    @Column(name = "leg_attempt")
    private int legAttempt;

    @Column(name = "distance_land")
    private int distanceLanded;

    @Column(name = "distance_attempt")
    private int distanceAttempt;

    @Column(name = "clinch_land")
    private int clinchLand;

    @Column(name = "clinch_attempt")
    private int clinchAttempt;

    @Column(name = "ground_land")
    private int groundLand;

    @Column(name = "ground_attempt")
    private int ground_attempt;

    @Column(name = "td_land")
    private int td_land;

    @Column(name = "td_attempt")
    private int tdAttempt;

    @Column(name = "sub_attempt")
    private int subAttempt;

    @Column(name = "control_time")
    private String control_time;
}
