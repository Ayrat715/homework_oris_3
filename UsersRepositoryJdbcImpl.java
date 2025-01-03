import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class UsersRepositoryJdbcImpl implements UserRepository {

    private Connection connection;

    public UsersRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAll() throws SQLException {
        String query = "SELECT * FROM driver";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<User> users = new ArrayList<>();

        while (resultSet.next()) {
            users.add(new User(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getInt("age"),
                    resultSet.getString("license_number"),
                    resultSet.getString("experience_years"),
                    resultSet.getString("city")
            ));
        }

        return users;
    }

    @Override
    public Optional<User> findById(Long id) throws SQLException {
        String query = "SELECT * FROM driver WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return Optional.of(new User(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getInt("age"),
                    resultSet.getString("license_number"),
                    resultSet.getString("experience_years"),
                    resultSet.getString("city")
            ));
        }

        return Optional.empty();
    }

    @Override
    public void save(User user) throws SQLException {
        String query = "INSERT INTO driver (name, surname, age, license_number, experience_years, city) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setInt(3, user.getAge());
        preparedStatement.setString(4, user.getLicenseNumber());
        preparedStatement.setString(5, user.getExperienceYears());
        preparedStatement.setString(6, user.getCity());
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(User user) throws SQLException {
        String query = "UPDATE driver SET name = ?, surname = ?, age = ?, license_number = ?, experience_years = ?, city = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setInt(3, user.getAge());
        preparedStatement.setString(4, user.getLicenseNumber());
        preparedStatement.setString(5, user.getExperienceYears());
        preparedStatement.setString(6, user.getCity());
        preparedStatement.setLong(7, user.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void remove(User user) throws SQLException {
        removeById(user.getId());
    }

    @Override
    public void removeById(Long id) throws SQLException {
        String query = "DELETE FROM driver WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public List<User> findAllByAge(Integer age) throws SQLException {
        String query = "SELECT * FROM driver WHERE age = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, age);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<User> users = new ArrayList<>();

        while (resultSet.next()) {
            users.add(new User(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getInt("age"),
                    resultSet.getString("license_number"),
                    resultSet.getString("experience_years"),
                    resultSet.getString("city")
            ));
        }

        return users;
    }

    @Override
    public List<User> findAllByCity(String city) throws SQLException {
        String query = "SELECT * FROM driver WHERE city = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, city);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<User> users = new ArrayList<>();

        while (resultSet.next()) {
            users.add(new User(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getInt("age"),
                    resultSet.getString("license_number"),
                    resultSet.getString("experience_years"),
                    resultSet.getString("city")
            ));
        }

        return users;
    }

    @Override
    public List<User> findAllByExperience(String experienceYears) throws SQLException {
        String query = "SELECT * FROM driver WHERE experience_years = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, experienceYears);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<User> users = new ArrayList<>();

        while (resultSet.next()) {
            users.add(new User(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getInt("age"),
                    resultSet.getString("license_number"),
                    resultSet.getString("experience_years"),
                    resultSet.getString("city")
            ));
        }

        return users;
    }
}
