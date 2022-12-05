package fa.training.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table
@Data

public class TheaterHall implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @NotNull
    private String name;

    @Column(columnDefinition = "boolean DEFAULT FALSE")
    @NotNull
    private boolean isFull;


    @OneToMany(mappedBy ="theaterHall", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Seat> seat;

    @OneToMany(mappedBy ="theaterHall", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<MovieShowTime> movieShowTimes;



}
