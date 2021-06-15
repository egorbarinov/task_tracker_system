package com.egorbarinov.tasktrackersystem.command.projectcommands;

import com.egorbarinov.tasktrackersystem.command.Command;
import com.egorbarinov.tasktrackersystem.entity.Project;
import com.egorbarinov.tasktrackersystem.service.ProjectServiceImpl;
import com.egorbarinov.tasktrackersystem.service.Service;

import java.util.List;

public class ShowProjects implements Command {
    private Service service;

    public ShowProjects() {
        this.service = new ProjectServiceImpl();;
    }

    private List<Project> showProjects() {
        return service.findAll();

    }

    @Override
    public void execute() {
        showProjects().forEach(System.out::println);
    }
}
