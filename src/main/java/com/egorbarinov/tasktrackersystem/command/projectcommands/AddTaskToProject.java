package com.egorbarinov.tasktrackersystem.command.projectcommands;

import com.egorbarinov.tasktrackersystem.command.Executor;
import com.egorbarinov.tasktrackersystem.entity.Project;
import com.egorbarinov.tasktrackersystem.entity.Task;
import com.egorbarinov.tasktrackersystem.service.ProjectServiceImpl;
import com.egorbarinov.tasktrackersystem.service.TaskServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddTaskToProject implements Executor {
    private ProjectServiceImpl projectService;
    private TaskServiceImpl taskService;
    private BufferedReader reader;
    private Project project;
    private Task task;
    private String enteredTaskId;
    private Long taskId;
    private String enteredProjectId;
    private Long projectId;
    private boolean lock = true;

    public AddTaskToProject() {
        this.projectService = new ProjectServiceImpl();
        this.taskService = new TaskServiceImpl();
        this.reader = new BufferedReader(new InputStreamReader(System.in));;
    }

    private Project findProjectById() {
        while (lock) {
            System.out.println("введите id проекта, в который требуется добавить задачу: ");
            try {
                enteredProjectId = reader.readLine();
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
        this.project = projectService.findById(projectId);
        System.out.println(project.toString());
        lock = true;
        return project;
    }

    private void addTaskToProject() {
        while (lock) {
            System.out.println("Введите id добавляемой в проект задачи: ");
            try {
                enteredTaskId = reader.readLine();
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
        this.task = getTaskById(taskId);
        this.project.getTasks().add(task);
        projectService.save(project);
        System.out.println("Изменения сохранены.");

    }

    private Task getTaskById(Long taskId) {
        return taskService.findById(taskId);
    }

    @Override
    public void execute() {
        findProjectById();
        addTaskToProject();
    }
}
