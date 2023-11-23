package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        Car car1 = new Car("Toyota", 70);
        Car car2 = new Car("BMW", 3);
        Car car3 = new Car("Volvo", 60);

        user1.setCar(car1);
        user2.setCar(car2);
        user3.setCar(car3);
        car1.setUser(user1);
        car2.setUser(user2);
        car3.setUser(user3);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        User userByCar = userService.getUserByCar(new Car("BMW", 3));
        System.out.println(userByCar.getFirstName());
        System.out.println(userByCar.getLastName());
        System.out.println(userByCar.getEmail());

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            if (user.getCar() != null) {
                System.out.println("Car model = " + user.getCar().getModel());
                System.out.println("Car series = " + user.getCar().getSeries());
            }
            System.out.println();
        }

        context.close();
    }
}
