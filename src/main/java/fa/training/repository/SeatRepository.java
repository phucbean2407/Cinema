package fa.training.repository;


import fa.training.entity.Movie;
import fa.training.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findSeatsByIsActive(boolean b);
    Seat findByName(String name);


    @Query(value = "select s.* " +
            "from cinema.seat s inner join cinema.theater_hall th " +
            "on s.theather_hall_id = th.id " +
            "where  th.name = :name" , nativeQuery = true)
    public List<Seat> findSeatFromHall(String name);

    @Query(value = "select s.* " +
            "from cinema.seat s inner join cinema.theater_hall th " +
            "on s.theather_hall_id = th.id " +
            "where  th.name = :name and s.is_active = true", nativeQuery = true)
    public List<Seat> findFreeSeatFromHall(String name);
}
