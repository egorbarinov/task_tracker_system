package com.egorbarinov.tasktrackersystem.command.taskcommands;

import com.egorbarinov.tasktrackersystem.command.Command;
import com.egorbarinov.tasktrackersystem.entity.Task;
import com.egorbarinov.tasktrackersystem.entity.User;
import com.egorbarinov.tasktrackersystem.repository.TaskRepository;
import com.egorbarinov.tasktrackersystem.repository.UserRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class ShowingAllTaskByUserCommand implements Command {
    private final UserRepository<User> userRepository;
    private final TaskRepository<Task> taskRepository;
    private final BufferedReader reader;
    private Long userId;

    public ShowingAllTaskByUserCommand() {
        this.userRepository = new UserRepository<>(User.class);
        this.taskRepository = new TaskRepository<>(Task.class);
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void execute() {
        getTasksByUserId().forEach(System.out::println);
    }

    private List<Task> getTasksByUserId() {
        findById();
        return taskRepository.findAll().stream().filter(t -> t.getUser().getId().equals(userId)).collect(Collectors.toList());
    }

    private void findById() {
        boolean lock = true;
        while (lock) {
            System.out.println("Введите id пользователя: ");
            try {
                String enteredUserId = reader.readLine();
                userId = Long.parseLong(enteredUserId);
                if (userId != 0) lock = false;
            } catch (NumberFormatException e) {
                System.out.println(" Вы ввели не числовое значение. Попробуйте снова:");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        userRepository.findById(userId);
    }
}
