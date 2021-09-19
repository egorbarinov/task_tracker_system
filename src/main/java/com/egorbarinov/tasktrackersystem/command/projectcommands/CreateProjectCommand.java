package com.egorbarinov.tasktrackersystem.command.projectcommands;

import com.egorbarinov.tasktrackersystem.command.Command;
import com.egorbarinov.tasktrackersystem.entity.Project;
import com.egorbarinov.tasktrackersystem.repository.ProjectRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreateProjectCommand implements Command {
    private final ProjectRepository<Project> projectRepository;
    private final BufferedReader reader;
    private String name;

    public CreateProjectCommand(BufferedReader reader) {
        this.projectRepository = new ProjectRepository<>(Project.class);
        this.reader = reader;
    }

    @Override
    public void execute() {
        addProject();
    }

    private void addProject() {
        boolean lock = true;
        while (lock) {
            System.out.println("Введите название поекта, не менее 4 символов: ");
            try {
                name = reader.readLine();
                if (!name.isEmpty() && name.length() > 3) lock = false;

            }
            catch (NumberFormatException e) {
                System.out.println(" Вы ввели не числовое значение. Попробуйте снова:");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        Project project = new Project(name);
        projectRepository.save(project);
        System.out.println("Проект добавлен: " + name);
    }

}
