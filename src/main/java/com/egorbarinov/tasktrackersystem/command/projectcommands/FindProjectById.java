package com.egorbarinov.tasktrackersystem.command.projectcommands;

import com.egorbarinov.tasktrackersystem.command.Command;
import com.egorbarinov.tasktrackersystem.entity.Project;
import com.egorbarinov.tasktrackersystem.repository.ProjectRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindProjectById implements Command {
    private final ProjectRepository<Project> projectRepository;
    private final BufferedReader reader;
    private Long projectId;

    public FindProjectById() {
        this.projectRepository = new ProjectRepository<>(Project.class);
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void execute() {
        System.out.println(findById());
    }

    private Project findById() {
        boolean lock = true;
        while (lock) {
            System.out.println("Введите id поекта: ");
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
        return projectRepository.findById(projectId);

    }

}
