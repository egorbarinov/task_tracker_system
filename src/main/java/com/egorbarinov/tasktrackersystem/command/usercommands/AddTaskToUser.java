package com.egorbarinov.tasktrackersystem.command.usercommands;

import com.egorbarinov.tasktrackersystem.command.Executor;
import com.egorbarinov.tasktrackersystem.entity.Task;
import com.egorbarinov.tasktrackersystem.entity.User;
import com.egorbarinov.tasktrackersystem.service.Service;
import com.egorbarinov.tasktrackersystem.service.TaskServiceImpl;
import com.egorbarinov.tasktrackersystem.service.UserServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddTaskToUser implements Executor {
    private Service taskService;
    private Service userService;
    private BufferedReader reader;
    private User user;
    private String enteredUserId;
    private Long userId;
    private String enteredTaskId;
    private Long taskId;
    boolean lock = true;

    public AddTaskToUser() {
        this.taskService = new TaskServiceImpl();
        this.userService = new UserServiceImpl();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private User findUserById() {
        while (lock) {
            System.out.println("для добавления задачи в проект введите id пользователя: ");
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
        user = (User) userService.findById(userId);
        System.out.println(user.toString());
        lock = true;
        return user;
    }

    private void addTaskToUser() {
        while (lock) {
            System.out.println("Введите id назначенной пользователю задачи: ");
            try {
                enteredTaskId = reader.readLine();
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
        userService.save(user);
        System.out.println("Задача добавлена пользователю: " + user.getName());
    }

    private Task getTaskById(Long taskId) {
        return (Task) taskService.findById(taskId);
    }

    @Override
    public void execute() {
        findUserById();
        addTaskToUser();
    }
}
