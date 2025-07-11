package UFC.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "fighter")
@Data
public class Fighter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "nickname")
    private String nickName;

    @Column(name = "height")
    private String height;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "reach")
    private String reach;

    @Column(name = "stance")
    private String stance;

    @Column(name = "wins")
    private int wins;

    @Column(name = "losses")
    private int losses;

    @Column(name = "draws")
    private int draws;

    @Column(name = "belt")
    private boolean belt;

}
