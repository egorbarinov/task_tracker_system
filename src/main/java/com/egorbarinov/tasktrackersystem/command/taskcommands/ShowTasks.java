package com.egorbarinov.tasktrackersystem.command.taskcommands;

import com.egorbarinov.tasktrackersystem.command.Executor;
import com.egorbarinov.tasktrackersystem.entity.Task;
import com.egorbarinov.tasktrackersystem.service.Service;
import com.egorbarinov.tasktrackersystem.service.TaskServiceImpl;

import java.util.List;

public class ShowTasks implements Executor {
    private Service service;

    public ShowTasks() {
        this.service = new TaskServiceImpl();
    }

    private List<Task> showTasks() {
        return service.findAll();

    }

    @Override
    public void execute() {
        showTasks().forEach(System.out::println);
    }
}
