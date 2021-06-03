package com.egorbarinov.tasktrackersystem.command.projectcommands;

import com.egorbarinov.tasktrackersystem.command.Executor;
import com.egorbarinov.tasktrackersystem.entity.Project;
import com.egorbarinov.tasktrackersystem.entity.User;
import com.egorbarinov.tasktrackersystem.service.ProjectServiceImpl;
import com.egorbarinov.tasktrackersystem.service.UserServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeleteUserFromProject implements Executor {
    private ProjectServiceImpl projectService;
    private UserServiceImpl userService;
    private Project project;
    private User user;
    private BufferedReader reader;
    private String enteredProjectId;
    private Long projectId;
    private String enteredUserId;
    private Long userid;
    private boolean lock = true;


    public DeleteUserFromProject() {
        this.projectService = new ProjectServiceImpl();
        this.userService = new UserServiceImpl();
        this.reader = new BufferedReader(new InputStreamReader(System.in));;
    }

    private void findProjectById() {
        while (lock) {
            System.out.println("укажите id проекта, из которого следует удалить польователя: ");
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
    }

    private void deleteUserFromProject() {
        while (lock) {
            System.out.println("Введите id удаляемого пользователя из проекта: ");
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
        this.project.getUsers().remove(user);
        projectService.update(project);
        System.out.println("Изменения сохранены.");

    }

    @Override
    public void execute() {
        findProjectById();
        deleteUserFromProject();
    }
}

