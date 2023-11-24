package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);


        User user1 = new User("User1", "Lastname1", "user1@mail.ru",
                new Car("Toyota", 70));
        User user2 = new User("User2", "Lastname2", "user2@mail.ru",
                new Car("BMW", 3));
        User user3 = new User("User3", "Lastname3", "user3@mail.ru",
                new Car("Volvo", 60));

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);

        User userByCar = userService.getUserByCar(new Car("BMW", 3));
        System.out.println(userByCar);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user);
        }

        context.close();
    }
}
