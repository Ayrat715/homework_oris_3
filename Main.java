import java.sql.*;
import java.util.Optional;
import java.util.Scanner;
import java.util.List;

public class Main {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "Gamburger";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/testdb_3";

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        UserRepository userRepository = new UsersRepositoryJdbcImpl(connection);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1) Добавить пользователей");
            System.out.println("2) Найти пользователей по возрасту");
            System.out.println("3) Найти пользователей по городу");
            System.out.println("4) Найти пользователей по опыту");
            System.out.println("5) Найти пользователя по ID");
            System.out.println("6) Обновить пользователя");
            System.out.println("7) Удалить пользователя по ID");
            System.out.println("8) Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("Сколько пользователей добавить?");
                int count = scanner.nextInt();
                scanner.nextLine();

                for (int i = 0; i < count; i++) {
                    System.out.println("Введите имя:");
                    String name = scanner.nextLine();
                    System.out.println("Введите фамилию:");
                    String surname = scanner.nextLine();
                    System.out.println("Введите возраст:");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Введите номер лицензии:");
                    String licenseNumber = scanner.nextLine();
                    System.out.println("Введите стаж:");
                    String experienceYears = scanner.nextLine();
                    System.out.println("Введите город:");
                    String city = scanner.nextLine();

                    userRepository.save(new User(null, name, surname, age, licenseNumber, experienceYears, city));
                }

            } else if (choice == 2) {
                System.out.println("Введите возраст:");
                int age = scanner.nextInt();
                List<User> users = userRepository.findAllByAge(age);
                users.forEach(user -> System.out.println(user.getFirstName() + " " + user.getLastName()));

            } else if (choice == 3) {
                System.out.println("Введите город:");
                String city = scanner.nextLine();
                List<User> users = userRepository.findAllByCity(city);
                users.forEach(user -> System.out.println(user.getFirstName() + " " + user.getLastName()));

            } else if (choice == 4) {
                System.out.println("Введите стаж:");
                String experience = scanner.nextLine();
                List<User> users = userRepository.findAllByExperience(experience);
                users.forEach(user -> System.out.println(user.getFirstName() + " " + user.getLastName()));

            } else if (choice == 5) {
                System.out.println("Введите ID пользователя:");
                Long id = scanner.nextLong();
                scanner.nextLine();
                Optional<User> user = userRepository.findById(id);
                user.ifPresentOrElse(
                        u -> System.out.println("Найден: " + u.getFirstName() + " " + u.getLastName()),
                        () -> System.out.println("Пользователь не найден")
                );

            } else if (choice == 6) {
                System.out.println("Введите ID пользователя для обновления:");
                Long id = scanner.nextLong();
                scanner.nextLine();

                Optional<User> user = userRepository.findById(id);
                if (user.isPresent()) {
                    System.out.println("Введите новое имя (оставьте пустым для сохранения текущего):");
                    String name = scanner.nextLine();
                    System.out.println("Введите новую фамилию:");
                    String surname = scanner.nextLine();
                    System.out.println("Введите новый возраст:");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Введите новый номер лицензии:");
                    String licenseNumber = scanner.nextLine();
                    System.out.println("Введите новый стаж:");
                    String experienceYears = scanner.nextLine();
                    System.out.println("Введите новый город:");
                    String city = scanner.nextLine();

                    User updatedUser = new User(
                            id,
                            name.isEmpty() ? user.get().getFirstName() : name,
                            surname.isEmpty() ? user.get().getLastName() : surname,
                            age,
                            licenseNumber,
                            experienceYears,
                            city
                    );
                    userRepository.update(updatedUser);
                    System.out.println("Пользователь успешно обновлен.");
                } else {
                    System.out.println("Пользователь с таким ID не найден.");
                }

            } else if (choice == 7) {
                System.out.println("Введите ID пользователя для удаления:");
                Long id = scanner.nextLong();
                scanner.nextLine();
                userRepository.removeById(id);
                System.out.println("Пользователь удален.");

            } else if (choice == 8) {
                System.out.println("Выход.");
                break;
            } else {
                System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

        connection.close();
    }
}
