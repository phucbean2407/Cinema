package fa.training.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table
@Data

public class Time implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    @NotNull
    @Size(max = 5, min = 5, message = "Time Type: hh:mm")
    private String time;

    @OneToMany(mappedBy = "time", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<MovieShowTime> movieShowTimes;
}