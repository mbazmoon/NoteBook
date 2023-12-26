package com.azmoon.notebook;

import com.azmoon.notebook.exception.RoleNotFoundException;
import com.azmoon.notebook.exception.UserNotFoundException;
import com.azmoon.notebook.model.Role;
import com.azmoon.notebook.model.User;
import com.azmoon.notebook.service.RoleService;
import com.azmoon.notebook.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NotebookApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotebookApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService, RoleService roleService) {
        return args -> {
            roleService.save(Role.builder().name("ROLE_USER").build());
            roleService.save(Role.builder().name("ROLE_MANGER").build());
            roleService.save(Role.builder().name("ROLE_ADMIN").build());
            roleService.save(Role.builder().name("ROLE_SUPER_ADMIN").build());

            userService.save(User.builder().name("mohammad azmoon").username("mbazmoon").password("1234").build());
            userService.save(User.builder().name("Martin Fowler").username("mfowler").password("1234").build());
            userService.save(User.builder().name("Robert Cecil Martin").username("rcmartin").password("1234").build());
            userService.save(User.builder().name("linus torvalds").username("ltorvalds").password("1234").build());

            User user1 = userService.getByUsername("mbazmoon");
            roleService.getAll().forEach(role -> {
                try {
                    userService.addRoleToUser(user1.getUserId(), role.getName());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            User user2 = userService.getByUsername("mfowler");
            userService.addRoleToUser(user2.getUserId(), "ROLE_USER");
            userService.addRoleToUser(user2.getUserId(), "ROLE_MANGER");

            User user3 = userService.getByUsername("rcmartin");
            userService.addRoleToUser(user3.getUserId(), "ROLE_ADMIN");

            User user4 = userService.getByUsername("ltorvalds");
            userService.addRoleToUser(user4.getUserId(), "ROLE_ADMIN");
            userService.addRoleToUser(user4.getUserId(), "ROLE_SUPER_ADMIN");
        };
    }

}
