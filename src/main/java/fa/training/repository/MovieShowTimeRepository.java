package fa.training.repository;

import fa.training.entity.MovieShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieShowTimeRepository extends JpaRepository<MovieShowTime, Long> {

    @Query(value = "select mst.* " +
            "from hall th inner join movie_show_time mst " +
            "on th.id = mst.hall_id inner join movie m " +
            "on mst.movie_id = m.id " +
            "where mst.time_id = :timeId AND m.name = :movieName AND th.name = :hallName AND mst.date = :date", nativeQuery = true)
    MovieShowTime findForTicket(String date, String movieName, String hallName, long timeId);
    @Query(value = "select mst.* from movie_show_time mst where  mst.date = :date", nativeQuery = true)
    List<MovieShowTime> findByDate(String date);
}
