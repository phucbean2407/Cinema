package fa.training.repository;

import fa.training.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query(value = "select t.* " +
            "from cinema.ticket t inner join cinema.people p " +
            "on t.people_id = p.id " +
            "where  p.email = :email AND p.role_id = 3", nativeQuery = true)
    Ticket findTicketByEmailCustomer(String email);

    @Query(value = "select t.*" +
            "        from cinema.ticket t inner join cinema.movie_show_time mst" +
            "        on t.movie_show_time_id = mst.id INNER JOIN cinema.theater_hall th" +
            "        on mst.theatre_hall_id = th.id" +
            "        where  th.name = :name", nativeQuery = true)
    List<Ticket> findTicketByHallName(String name);

    @Query(value = "select t.* from cinema.ticket t ", nativeQuery = true)
    List<Ticket> findAll();
}
