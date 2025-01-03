import java.sql.SQLException;
import java.util.List;

interface UserRepository extends CrudRepository<User> {
    List<User> findAllByAge(Integer age) throws SQLException;
    List<User> findAllByCity(String city) throws SQLException;
    List<User> findAllByExperience(String experienceYears) throws SQLException;
}