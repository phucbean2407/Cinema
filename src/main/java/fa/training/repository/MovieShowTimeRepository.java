package fa.training.repository;

import fa.training.entity.Movie;
import fa.training.entity.MovieShowTime;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MovieShowTimeRepository extends JpaRepository<MovieShowTime, Long> {

    @Query(value = "select mst.* " +
            "from theatre_hall th INNER JOIN movie_show_time mst" +
            "ON th.id = mst.theatre_hall_id INNER JOIN movie m" +
            "ON m.id = mst.movie_id INNER JOIN time t" +
            "ON t.id = mst.time_id" +
            "where  mst.date = :date AND m.name = :movieName AND th.name = :hallName AND t.time = :timeName", nativeQuery = true)
    MovieShowTime findForTicket(Date date, String movieName, String hallName, String timeName);
    @Query(value = "select mst.* from movie_show_time mst where  mst.date = :date", nativeQuery = true)
    List<MovieShowTime> findByDate(String date);
}
