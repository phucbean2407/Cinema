package fa.training.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "CheckInit", columnNames = { "name","theather_hall_id","time_id"}))
@Data
public class Seat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @NotNull
    private String name;

    @Column(columnDefinition = "boolean DEFAULT FALSE")
    @NotNull
    private boolean isActive;
    @ManyToOne
    @JoinColumn(name = "theather_hall_id")
    private TheaterHall theaterHall;

    @ManyToOne
    @JoinColumn(name = "time_id")
    private Time time;

}
