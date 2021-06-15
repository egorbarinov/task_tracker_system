package com.egorbarinov.tasktrackersystem.command.projectcommands;

import com.egorbarinov.tasktrackersystem.command.Command;
import com.egorbarinov.tasktrackersystem.entity.Project;
import com.egorbarinov.tasktrackersystem.entity.User;
import com.egorbarinov.tasktrackersystem.repository.ProjectRepository;
import com.egorbarinov.tasktrackersystem.repository.UserRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddUserToProject implements Command {
    private final ProjectRepository<Project> projectRepository;
    private final UserRepository<User> userRepository;
    private Project project;
    private final BufferedReader reader;
    private Long userId;
    private Long projectId;
    private boolean lock = true;

    public AddUserToProject() {
        this.projectRepository = new ProjectRepository<>(Project.class);
        this.userRepository = new UserRepository<>(User.class);
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private void findProjectById() {
        while (lock) {
            System.out.println("для добавления пользователя в проект введите id проекта: ");
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

    private void addUserToProject() {
        while (lock) {
            System.out.println("Введите id добавляемого в проект пользователя: ");
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
        User user = getUserById(userId);
        this.project.getUsers().add(user);
        projectRepository.save(project);
        System.out.println("Изменения сохранены.");

    }

    private User getUserById(Long userid) {
        return userRepository.findById(userid);
    }

    @Override
    public void execute() {
        findProjectById();
        addUserToProject();
    }
}
