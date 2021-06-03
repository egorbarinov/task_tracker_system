package com.egorbarinov.tasktrackersystem.command.usercommands;

import com.egorbarinov.tasktrackersystem.command.Executor;
import com.egorbarinov.tasktrackersystem.entity.Project;
import com.egorbarinov.tasktrackersystem.entity.Task;
import com.egorbarinov.tasktrackersystem.entity.User;
import com.egorbarinov.tasktrackersystem.service.Service;
import com.egorbarinov.tasktrackersystem.service.TaskServiceImpl;
import com.egorbarinov.tasktrackersystem.service.UserServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeleteTaskFromUser implements Executor {
    private TaskServiceImpl taskService;
    private UserServiceImpl userService;
    private User user;
    private BufferedReader reader;
    private String enteredUserId;
    private Long userid;
    private String enteredTaskId;
    private Long taskId;
    private boolean lock = true;

    public DeleteTaskFromUser() {
        this.taskService = new TaskServiceImpl();
        this.userService = new UserServiceImpl();
        this.reader = new BufferedReader(new InputStreamReader(System.in));;;
    }

    private void findUserById() {
        while (lock) {
            System.out.println("укажите id пользователя, у которого следует убрать задачу: ");
            try {
                enteredUserId = reader.readLine();
                userid = Long.parseLong(enteredUserId);
                if (userid != 0) lock = false;
            }
            catch (NumberFormatException e) {
                System.out.println(" Вы ввели не числовое значение. Попробуйте снова:");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.user = userService.findById(userid);
        lock = true;
    }

    private void deleteTaskFromUser() {
        while (lock) {
            System.out.println("Введите id удаляемого пользователя из проекта: ");
            try {
                enteredTaskId = reader.readLine();
                taskId = Long.parseLong(enteredUserId);
                if (taskId != 0) lock = false;
            }
            catch (NumberFormatException e) {
                System.out.println(" Вы ввели не числовое значение. Попробуйте снова:");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        Task task = taskService.findById(taskId);
        this.user.getTasks().remove(task);
        userService.update(user);
        System.out.println("Изменения сохранены.");

    }

    @Override
    public void execute() {
        findUserById();
        deleteTaskFromUser();
    }
}
