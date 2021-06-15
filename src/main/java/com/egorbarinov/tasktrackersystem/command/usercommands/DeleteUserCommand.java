package com.egorbarinov.tasktrackersystem.command.usercommands;

import com.egorbarinov.tasktrackersystem.command.Command;
import com.egorbarinov.tasktrackersystem.entity.User;
import com.egorbarinov.tasktrackersystem.repository.UserRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeleteUserCommand implements Command {
    private final UserRepository<User> userRepository;
    private final BufferedReader reader;
    private Long userId;

    public DeleteUserCommand() {
        this.userRepository = new UserRepository<>(User.class);
        this.reader = new BufferedReader(new InputStreamReader(System.in));    }

    @Override
    public void execute() {
        deleteById();
    }

    private void deleteById() {
        boolean lock = true;
        while (lock) {
            System.out.println("Введите id пользователя, которого следует удалить: ");
            try {
                String enteredUserId = reader.readLine();
                userId = Long.parseLong(enteredUserId);
                if (userId != 0) lock = false;
            }
            catch (NumberFormatException e) {
                System.out.println(" Вы ввели не числовое значение. Попробуйте снова:");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        userRepository.deleteById(userId);
        System.out.println("Пользователь удален");
    }

}
