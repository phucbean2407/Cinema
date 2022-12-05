package fa.training.repository;

import fa.training.entity.TheaterHall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterHallRepository extends JpaRepository<TheaterHall, Long> {

    TheaterHall findByName(String name);
}
