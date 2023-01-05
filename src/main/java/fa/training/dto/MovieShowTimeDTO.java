package fa.training.dto;


import fa.training.entity.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieShowTimeDTO {

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date;
    private Time time;
    @NotNull
    private MovieDTO movieDTO;
    @NotNull
    private HallDTO hallDTO;
    private Set<SeatDTO> seatDTOS;

//    @ConstructorProperties({"date","time", "movieDTO", "hallDTO"})
//    public MovieShowTimeDTO(Date date, Time time, MovieDTO movieDTO, HallDTO hallDTO) {
//        this.date = date;
//        this.time = time;
//        this.movieDTO = movieDTO;
//        this.hallDTO = hallDTO;
//    }
//
//    public static MovieShowTimeDTO.MovieShowTimeDTOBuilder builder() {
//        return new MovieShowTimeDTO.MovieShowTimeDTOBuilder();
//    }
//
//    public static class MovieShowTimeDTOBuilder {
//        private Date date;
//        private Time time;
//        private MovieDTO movieDTO;
//        private HallDTO hallDTO;
//
//        public MovieShowTimeDTOBuilder() {
//            // TODO document why this constructor is empty
//        }
//        public MovieShowTimeDTO.MovieShowTimeDTOBuilder date(Date date) {
//            this.date = date;
//            return this;
//        }
//        public MovieShowTimeDTO.MovieShowTimeDTOBuilder  time(Time time){
//            this.time = time;
//            return this;
//        }
//        public MovieShowTimeDTO.MovieShowTimeDTOBuilder  movieDTO(MovieDTO movieDTO){
//            this.movieDTO = movieDTO;
//            return this;
//        }
//        public MovieShowTimeDTO.MovieShowTimeDTOBuilder hallDTO(HallDTO hallDTO) {
//            this.hallDTO = hallDTO;
//            return this;
//        }
//
//
//        public MovieShowTimeDTO build() {
//            return new MovieShowTimeDTO(this.date,this.time,this.movieDTO,this.hallDTO);
//        }
//    }

}
