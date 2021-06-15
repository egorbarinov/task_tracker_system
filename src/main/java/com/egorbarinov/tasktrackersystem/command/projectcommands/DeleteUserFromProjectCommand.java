package com.egorbarinov.tasktrackersystem.command.projectcommands;

import com.egorbarinov.tasktrackersystem.command.Command;
import com.egorbarinov.tasktrackersystem.entity.Project;
import com.egorbarinov.tasktrackersystem.entity.User;
import com.egorbarinov.tasktrackersystem.repository.ProjectRepository;
import com.egorbarinov.tasktrackersystem.repository.UserRepository;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeleteUserFromProjectCommand implements Command {
    private final ProjectRepository<Project> projectRepository;
    private final UserRepository<User> userRepository;
    private Project project;
    private final BufferedReader reader;
    private Long projectId;
    private Long userid;
    private boolean lock = true;


    public DeleteUserFromProjectCommand() {
        this.projectRepository = new ProjectRepository<>(Project.class);
        this.userRepository = new UserRepository<>(User.class);
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void execute() {
        findProjectById();
        deleteUserFromProject();
    }

    private void findProjectById() {
        while (lock) {
            System.out.println("укажите id проекта, из которого следует удалить польователя: ");
            try {
                String enteredProjectId = reader.readLine();
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
        project = projectRepository.findById(projectId);
        System.out.println(project.toString());
        lock = true;
    }

    private void deleteUserFromProject() {
        while (lock) {
            System.out.println("Введите id удаляемого пользователя из проекта: ");
            try {
                String enteredUserId = reader.readLine();
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
        User user = userRepository.findById(userid);
        this.project.getUsers().remove(user);
        projectRepository.update(project);
        System.out.println("Изменения сохранены.");

    }

}

