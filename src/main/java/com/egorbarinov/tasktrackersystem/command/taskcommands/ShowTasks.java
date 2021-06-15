package com.egorbarinov.tasktrackersystem.command.taskcommands;

import com.egorbarinov.tasktrackersystem.command.Command;
import com.egorbarinov.tasktrackersystem.entity.Task;
import com.egorbarinov.tasktrackersystem.repository.TaskRepository;

import java.util.List;

public class ShowTasks implements Command {
    private final TaskRepository<Task> taskRepository;

    public ShowTasks() {
        this.taskRepository = new TaskRepository<>(Task.class);
    }

    @Override
    public void execute() {
        showTasks().forEach(System.out::println);
    }

    private List<Task> showTasks() {
        return taskRepository.findAll();
    }

}
