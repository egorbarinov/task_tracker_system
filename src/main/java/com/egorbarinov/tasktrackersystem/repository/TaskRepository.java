package com.egorbarinov.tasktrackersystem.repository;

import com.egorbarinov.tasktrackersystem.entity.Project;
import com.egorbarinov.tasktrackersystem.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository<Task> extends RepositoryImpl<Task, Long> {

    public TaskRepository(Class<Task> typeClass) {
        super(typeClass);
    }

    @Override
    public Task findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void save(Task entity) {
        super.save(entity);
    }

    @Override
    public void delete(Task entity) {
        super.delete(entity);
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }

    @Override
    public List<Task> findAll() {
        return super.findAll();
    }

    public List<com.egorbarinov.tasktrackersystem.entity.Task> getAllByProjectIsInAndUser(List<Project> projects, User user) {
        List<com.egorbarinov.tasktrackersystem.entity.Task> outputTasks = new ArrayList<>();
        SessionFactory factory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory();
        try (Session session = factory.getCurrentSession()) {
            session.getTransaction().begin();
            Query q = session.createQuery("SELECT task FROM Task task WHERE task.project IN :projects AND task.user.id = :user");
            q.setParameter("projects", projects);
            q.setParameter("user", user.getId());
            outputTasks = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputTasks;
    }
}
