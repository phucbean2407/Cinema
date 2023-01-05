package fa.training.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table
@Data

public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    @NotNull(message = "Empty Movie Name")
    private String name;
    @Column()
    @NotNull(message = "Empty Description")
    private String description;
    @Column()
    @NotNull()
    @Positive(message = "Must > 0")
    private int lengthMinute;
    @Column()
    @NotNull()
    @Positive()
    private double rating;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @OneToMany(mappedBy ="movie", cascade = CascadeType.ALL)
    @Transient
    @JsonIgnore
    private Set<MovieShowTime> movieShowTimes;


}







































































































































































































































































































































































































































































































































































































































































































































































































