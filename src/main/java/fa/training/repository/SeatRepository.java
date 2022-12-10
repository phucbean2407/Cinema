package fa.training.repository;


import fa.training.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    Seat findByName(String name);

}


