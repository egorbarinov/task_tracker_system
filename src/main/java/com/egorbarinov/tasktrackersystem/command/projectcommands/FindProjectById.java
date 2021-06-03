package com.egorbarinov.tasktrackersystem.command.projectcommands;

import com.egorbarinov.tasktrackersystem.command.Executor;
import com.egorbarinov.tasktrackersystem.entity.Project;
import com.egorbarinov.tasktrackersystem.service.ProjectServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindProjectById implements Executor {
    private ProjectServiceImpl service;
    private BufferedReader reader;
    private String enteredProjectId;
    private Long projectId;


    public FindProjectById() {
        this.service = new ProjectServiceImpl();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private Project findById() {
        boolean lock = true;
        while (lock) {
            System.out.println("Введите id поекта: ");
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
        return service.findById(projectId);

    }

    @Override
    public void execute() {
        System.out.println(findById());
    }
}
