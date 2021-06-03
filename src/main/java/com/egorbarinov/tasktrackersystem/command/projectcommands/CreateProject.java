package com.egorbarinov.tasktrackersystem.command.projectcommands;

import com.egorbarinov.tasktrackersystem.command.Executor;
import com.egorbarinov.tasktrackersystem.entity.Project;
import com.egorbarinov.tasktrackersystem.service.ProjectServiceImpl;
import com.egorbarinov.tasktrackersystem.service.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreateProject implements Executor {
    private Service service;
    private Project project;
    private BufferedReader reader;
    private String name;

    public CreateProject() {
        this.service = new ProjectServiceImpl();
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private void addProject() {
        boolean lock = true;
        while (lock) {
            System.out.println("Введите название поекта, не менее 4 символов: ");
            try {
                name = reader.readLine();
                if (!name.isEmpty() && name.length() > 3) lock = false;

            }
            catch (NumberFormatException e) {
                System.out.println(" Вы ввели не числовое значение. Попробуйте снова:");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.project = new Project(name);
        service.save(project);
        System.out.println("Проект добавлен: " + name);
    }

    @Override
    public void execute() {
        addProject();
    }
}
