package com.egorbarinov.tasktrackersystem.command.projectcommands;

import com.egorbarinov.tasktrackersystem.command.Command;
import com.egorbarinov.tasktrackersystem.entity.Project;
import com.egorbarinov.tasktrackersystem.repository.ProjectRepository;

import java.util.List;

public class ShowProjects implements Command {
    private final ProjectRepository<Project> projectRepository;

    public ShowProjects() {
        this.projectRepository = new ProjectRepository<>(Project.class);;
    }

    @Override
    public void execute() {
        showProjects().forEach(System.out::println);
    }

    private List<Project> showProjects() {
        return projectRepository.findAll();
    }
}
