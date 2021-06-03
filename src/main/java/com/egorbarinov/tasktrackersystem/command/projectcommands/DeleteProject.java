package com.egorbarinov.tasktrackersystem.command.projectcommands;

import com.egorbarinov.tasktrackersystem.command.Executor;
import com.egorbarinov.tasktrackersystem.service.ProjectServiceImpl;
import com.egorbarinov.tasktrackersystem.service.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeleteProject implements Executor {
    private Service service;
    private BufferedReader reader;
    private String enteredProjectId;
    private Long projectId;

    public DeleteProject() {
        this.service = new ProjectServiceImpl();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private void deleteById() {
        boolean lock = true;
        while (lock) {
            System.out.println("Введите id поекта, который следует удалить: ");
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
        service.deleteById(projectId);
        System.out.println("Проект удален");
    }

    @Override
    public void execute() {
        deleteById();
    }
}
