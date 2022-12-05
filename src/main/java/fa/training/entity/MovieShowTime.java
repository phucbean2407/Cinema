package fa.training.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.validation.constraints.*;


@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(name = "CheckInit", columnNames = { "date","movieId","theatreHallId","timeId"}))
public class MovieShowTime implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull
    private Date date;

    @ManyToOne
    @JoinColumn(name = "timeId")
    private Time time;

    @ManyToOne
    @JoinColumn(name = "movieId")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theatreHallId")
    private TheaterHall theaterHall;

    @OneToMany(mappedBy = "movieShowTime", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Ticket> ticket;


}
