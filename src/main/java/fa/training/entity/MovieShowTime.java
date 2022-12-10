package fa.training.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(name = "CheckInit", columnNames = { "date","movie_id","hall_id","time_id"}))
public class MovieShowTime implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToMany()
    @JoinTable(	name = "show_time_seat",
            joinColumns = @JoinColumn(name = "movie_show_time_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id"))
    private Set<Seat> seats = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "time_id")
    private Time time;
    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @OneToMany(mappedBy = "movieShowTime", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Ticket> ticket;


}
