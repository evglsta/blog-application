package test.blogapplication.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import test.blogapplication.user.User;
import test.blogapplication.user.UserRepository;

import java.util.List;

@Component
public class Seeder {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUserTable();
    }

    private void seedUserTable() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            User newUser = new User();
            newUser.setUsername("admin");
            newUser.setPassword(passwordEncoder.encode("12345"));
            newUser.setRole("ADMIN");
            userRepository.save(newUser);
        }
    }
}
