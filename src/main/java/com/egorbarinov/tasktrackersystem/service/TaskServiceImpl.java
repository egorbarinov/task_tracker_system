package com.egorbarinov.tasktrackersystem.service;

import com.egorbarinov.tasktrackersystem.entity.Task;
import com.egorbarinov.tasktrackersystem.repository.TaskRepository;

import java.util.List;

public class TaskServiceImpl implements Service<Task> {
    private TaskRepository repository;

    public TaskServiceImpl() {
        this.repository = new TaskRepository(Task.class);
    }

    @Override
    public Task findById(Long id) {
        return (Task) repository.findById(id);
    }

    public List<Task> findAll() {
        return repository.findAll();
    }
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void save(Task task) {
        repository.save(task);
    }

    @Override
    public void update(Task task) { repository.update(task);
    }


}
