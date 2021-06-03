package com.egorbarinov.tasktrackersystem.command.projectcommands;

import com.egorbarinov.tasktrackersystem.command.Executor;
import com.egorbarinov.tasktrackersystem.entity.Project;
import com.egorbarinov.tasktrackersystem.entity.User;
import com.egorbarinov.tasktrackersystem.service.ProjectServiceImpl;
import com.egorbarinov.tasktrackersystem.service.UserServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddUserToProject implements Executor {
    private ProjectServiceImpl projectService;
    private UserServiceImpl userService;
    private Project project;
    private User user;
    private BufferedReader reader;
    private String enteredUserId;
    private Long userId;
    private String enteredProjectId;
    private Long projectId;
    private boolean lock = true;

    public AddUserToProject() {
        this.projectService = new ProjectServiceImpl();
        this.userService = new UserServiceImpl();
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private Project findProjectById() {
        while (lock) {
            System.out.println("для добавления пользователя в проект введите id проекта: ");
            try {
                enteredProjectId = reader.readLine();
                projectId = Long.parseLong(enteredProjectId);
                if (projectId != 0) lock = false;
            }
            catch (NumberFormatException e) {
                System.out.println(" Вы ввели не числовое значение. Попробуйте снова:");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        project = projectService.findById(projectId);
        System.out.println(project.toString());
        lock = true;
        return project;
    }

    private void addUserToProject() {
        while (lock) {
            System.out.println("Введите id добавляемого в проект пользователя: ");
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
        this.user = getUserById(userId);
        this.project.getUsers().add(user);
        projectService.save(project);
        System.out.println("Изменения сохранены.");

    }

    private User getUserById(Long userid) {
        return userService.findById(userid);
    }

    @Override
    public void execute() {
        findProjectById();
        addUserToProject();
    }
}
