package com.egorbarinov.tasktrackersystem.repository;


import java.util.List;

public class ProjectRepository<Project> extends RepositoryImpl<Project, Long> {

    public ProjectRepository(Class<Project> typeClass) {
        super(typeClass);
    }

    @Override
    public Project findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void save(Project entity) {
        super.save(entity);
    }

    @Override
    public void delete(Project entity) {
        super.delete(entity);
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }

    @Override
    public List<Project> findAll() {
        return super.findAll();
    }

}
