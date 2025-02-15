package com.azmoon.notebook;

import com.azmoon.notebook.entity.Role;
import com.azmoon.notebook.entity.User;
import com.azmoon.notebook.service.RoleService;
import com.azmoon.notebook.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableJpaAuditing
public class NotebookApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotebookApplication.class, args);
        List<String> names = List.of("mohammad", "bahar", "arian", "bahar");
        Map<String, Long> result = names.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(result);
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

            User user3 = userService.getByUsername("rcmartin");
            userService.addRoleToUser(user3.getUserId(), "ROLE_ADMIN");

            User user4 = userService.getByUsername("ltorvalds");
            userService.addRoleToUser(user4.getUserId(), "ROLE_ADMIN");
            userService.addRoleToUser(user4.getUserId(), "ROLE_SUPER_ADMIN");
        };
    }

}
