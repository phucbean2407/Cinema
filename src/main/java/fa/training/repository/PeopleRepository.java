package fa.training.repository;

import fa.training.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {
    @Query(value = "select p.* " +
            "from cinema.people p INNER JOIN cinema.users s " +
            "on p.user_id = s.id " +
            "where s.email = :email ", nativeQuery = true)
    People findByEmail(String email);
    @Query(value = "select p.* " +
            "from cinema.people p INNER JOIN cinema.users s " +
            "on p.user_id = s.id INNER JOIN cinema.user_role r " +
            "on s.id = r.user_id "+
            "where r.role_id= :roleId", nativeQuery = true)
    List<People> findByRole(long roleId);
    @Query(value = "select p.* "+
            "from cinema.people p INNER JOIN cinema.users " +
            "on p.user_id = s.id INNER JOIN cinema.user_role r " +
            "on s.id = r.user_id " +
            "where r.role_id= :roleId and p.birthday = :birthday " , nativeQuery = true)
    List<People> findByRoleAndBirthday(long roleId, String birthday);



}
