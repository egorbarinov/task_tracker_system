package com.egorbarinov.tasktrackersystem.command.usercommands;

import com.egorbarinov.tasktrackersystem.command.Command;
import com.egorbarinov.tasktrackersystem.entity.Task;
import com.egorbarinov.tasktrackersystem.entity.User;
import com.egorbarinov.tasktrackersystem.repository.TaskRepository;
import com.egorbarinov.tasktrackersystem.repository.UserRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeleteTaskFromUserCommand implements Command {
    private final TaskRepository<Task> taskRepository;
    private final UserRepository<User> userRepository;
    private User user;
    private final BufferedReader reader;
    private String enteredUserId;
    private Long userid;
    private Long taskId;
    private boolean lock = true;

    public DeleteTaskFromUserCommand() {
        this.taskRepository = new TaskRepository<>(Task.class);
        this.userRepository = new UserRepository<>(User.class);
        this.reader = new BufferedReader(new InputStreamReader(System.in));;;
    }

    @Override
    public void execute() {
        findUserById();
        deleteTaskFromUser();
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
        this.user = userRepository.findById(userid);
        lock = true;
    }

    private void deleteTaskFromUser() {
        while (lock) {
            System.out.println("Введите id удаляемого пользователя из проекта: ");
            try {
                String enteredTaskId = reader.readLine();
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
        Task task = taskRepository.findById(taskId);
        this.user.getTasks().remove(task);
        userRepository.update(user);
        System.out.println("Изменения сохранены.");

    }

}
