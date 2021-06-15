package com.egorbarinov.tasktrackersystem.command.usercommands;

import com.egorbarinov.tasktrackersystem.command.Command;
import com.egorbarinov.tasktrackersystem.entity.User;
import com.egorbarinov.tasktrackersystem.repository.UserRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreateUser implements Command {
    private final UserRepository<User> userRepository;
    private final BufferedReader reader;
    private String name;

    public CreateUser() {
        this.userRepository = new UserRepository<>(User.class);
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void execute() {
        addUser();
    }

    private void addUser() {
        boolean lock = true;
        while (lock) {
            System.out.println("Введите имя пользователя, не менее 4 символов: ");
            try {
                name = reader.readLine();
                if (!name.isEmpty() && name.length() > 3) lock = false;
            }
            catch (NumberFormatException e) {
                System.out.println(" Вы ввели не числовое значение. Попробуйте снова:");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        User user = new User(name);
        userRepository.save(user);
        System.out.println("Пользователь добавлен: " + name);
    }

}
