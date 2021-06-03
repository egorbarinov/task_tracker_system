package com.egorbarinov.tasktrackersystem.service;

import com.egorbarinov.tasktrackersystem.entity.Project;
import com.egorbarinov.tasktrackersystem.repository.ProjectRepository;

import java.util.List;

public class ProjectServiceImpl implements Service<Project> {

    private ProjectRepository repository;

    public ProjectServiceImpl() {
        this.repository = new ProjectRepository(Project.class);
    }

    @Override
    public Project findById(Long id) {
        return (Project) repository.findById(id);
    }

    @Override
    public List<Project> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void save(Project project) {
        repository.save(project);
    }

    @Override
    public void update(Project project) {
        repository.update(project);
    }

}
