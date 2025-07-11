package UFC.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "fight_winner")
@Data
public class FightWinner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "knockdowns")
    private int knockdowns;

    @OneToOne
    @JoinColumn(name = "fighter_id", referencedColumnName = "id")
    private Fighter fighter;

    @Column(name = "significant_strikes_landed")
    private int significantStrikesLanded;

    @Column(name = "significant_strikes_attempted")
    private int significantStrikeAttempt;

    @Column(name = "total_strikes_landed")
    private int totalStrikesLanded;

    @Column(name = "total_strikes_attempted")
    private int totalStrikesAttempt;

    @Column(name = "total_takedown_landed")
    private int totalTakedownLanded;

    @Column(name = "total_takedown_attempted")
    private int totalTakedownAttempt;

    @Column(name = "sub_attempt")
    private int subAttempt;

    @Column(name = "control_time")
    private String controlTime;

    @Column(name = "head_strike_landed")
    private int headStrikeLanded;

    @Column(name = "head_strike_attempt")
    private int headStrikeAttempt;

    @Column(name = "body_strike_landed")
    private int bodyStrikeLanded;

    @Column(name = "body_strike_attempt")
    private int bodyStrikeAttempt;

    @Column(name = "leg_strike_land")
    private int legStrikeLanded;

    @Column(name = "leg_strike_attempt")
    private int legStrikeAttempt;

    @Column(name = "distance_strike_land")
    private int distanceStrikeLanded;

    @Column(name = "distance_strike_attempt")
    private int distanceStrikeAttempt;

    @Column(name = "clinch_strike_land")
    private int clinchStrikeLanded;

    @Column(name = "clinch_strike_attempt")
    private int clinchStrikeAttempt;
}
