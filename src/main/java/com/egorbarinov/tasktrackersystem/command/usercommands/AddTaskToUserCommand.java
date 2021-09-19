package com.egorbarinov.tasktrackersystem.command.usercommands;

import com.egorbarinov.tasktrackersystem.command.Command;
import com.egorbarinov.tasktrackersystem.entity.Task;
import com.egorbarinov.tasktrackersystem.entity.User;
import com.egorbarinov.tasktrackersystem.repository.TaskRepository;
import com.egorbarinov.tasktrackersystem.repository.UserRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddTaskToUserCommand implements Command {
    private final TaskRepository<Task> taskRepository;
    private final UserRepository<User> userRepository;
    private final BufferedReader reader;
    private User user;
    private Long userId;
    private Long taskId;
    boolean lock = true;
    public AddTaskToUserCommand(BufferedReader reader) {
        this.taskRepository = new TaskRepository<>(Task.class);
        this.userRepository = new UserRepository<>(User.class);
        this.reader = reader;
    }

    @Override
    public void execute() {
        findUserById();
        addTaskToUser();
    }

    private User findUserById() {
        while (lock) {
            System.out.println("для добавления задачи в проект введите id пользователя: ");
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
        user = userRepository.findById(userId);
        System.out.println(user.toString());
        lock = true;
        return user;
    }

    private void addTaskToUser() {
        while (lock) {
            System.out.println("Введите id назначенной пользователю задачи: ");
            try {
                String enteredTaskId = reader.readLine();
                taskId = Long.parseLong(enteredTaskId);
                if (taskId != 0) lock = false;
            }
            catch (NumberFormatException e) {
                System.out.println(" Вы ввели не числовое значение. Попробуйте снова:");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        Task task = getTaskById(taskId);
        this.user.getTasks().add(task);
        userRepository.save(user);
        System.out.println("Задача добавлена пользователю: " + user.getName());
    }

    private Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId);
    }

}
