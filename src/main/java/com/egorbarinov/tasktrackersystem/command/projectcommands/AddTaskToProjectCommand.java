package com.egorbarinov.tasktrackersystem.command.projectcommands;

import com.egorbarinov.tasktrackersystem.command.Command;
import com.egorbarinov.tasktrackersystem.entity.Project;
import com.egorbarinov.tasktrackersystem.entity.Task;
import com.egorbarinov.tasktrackersystem.repository.ProjectRepository;
import com.egorbarinov.tasktrackersystem.repository.TaskRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddTaskToProjectCommand implements Command {
    private final TaskRepository<Task> taskRepository;
    private final ProjectRepository<Project> projectRepository;
    private final BufferedReader reader;
    private Project project;
    private Long taskId;
    private Long projectId;
    private boolean lock = true;

    public AddTaskToProjectCommand(BufferedReader reader) {
        this.projectRepository = new ProjectRepository<>(Project.class);
        this.taskRepository = new TaskRepository<>(Task.class);
        this.reader = reader;
    }

    @Override
    public void execute() {
        findProjectById();
        addTaskToProject();
    }

    private void findProjectById() {
        while (lock) {
            System.out.println("введите id проекта, в который требуется добавить задачу: ");
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
        this.project = projectRepository.findById(projectId);
        System.out.println(project.toString());
        lock = true;
    }

    private void addTaskToProject() {
        while (lock) {
            System.out.println("Введите id добавляемой в проект задачи: ");
            try {
                String enteredTaskId = reader.readLine();
                taskId = Long.parseLong(enteredTaskId);
                if (taskId != 0) lock = false;
            }
            catch (NumberFormatException e) {
                System.out.println(" Вы ввели не числовое значение. Попробуйте снова:");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        Task task = getTaskById(taskId);
        this.project.getTasks().add(task);
        projectRepository.save(project);
        System.out.println("Изменения сохранены.");

    }

    private Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId);
    }

}
