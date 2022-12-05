package fa.training.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table
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
    @JoinColumn(name = "theatherHall_id")
    private TheaterHall theaterHall;


}
