package com.egorbarinov.tasktrackersystem.command.taskcommands;

import com.egorbarinov.tasktrackersystem.command.Executor;
import com.egorbarinov.tasktrackersystem.entity.Task;
import com.egorbarinov.tasktrackersystem.entity.User;
import com.egorbarinov.tasktrackersystem.service.TaskServiceImpl;
import com.egorbarinov.tasktrackersystem.service.UserServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class ShowingAllTaskByUser implements Executor {
    private UserServiceImpl userService;
    private TaskServiceImpl taskService;
    private BufferedReader reader;
    private String enteredUserId;
    private Long userId;

    public ShowingAllTaskByUser() {
        this.userService = new UserServiceImpl();
        this.taskService = new TaskServiceImpl();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private User findById() {
        boolean lock = true;
        while (lock) {
            System.out.println("Введите id пользователя: ");
            try {
                enteredUserId = reader.readLine();
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
        return userService.findById(userId);
    }

    private List<Task> getTasksByUserId() {
        findById();
        return taskService.findAll().stream().filter(t -> t.getUser().getId().equals(userId)).collect(Collectors.toList());    }


    @Override
    public void execute() {
        getTasksByUserId().forEach(System.out::println);
    }
}
