package com.egorbarinov.tasktrackersystem.command.taskcommands;

import com.egorbarinov.tasktrackersystem.command.Executor;
import com.egorbarinov.tasktrackersystem.entity.Task;
import com.egorbarinov.tasktrackersystem.service.Service;
import com.egorbarinov.tasktrackersystem.service.TaskServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreateTask implements Executor {
    private Service service;
    private Task task;
    private BufferedReader reader;
    private String name;

    public CreateTask() {
        this.service = new TaskServiceImpl();
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private void addProject() {
        boolean lock = true;
        while (lock) {
            System.out.println("Введите название задачи, не менее 4 символов: ");
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
        this.task = new Task(name);
        service.save(task);
        System.out.println("Задача создана: " + name);
    }

    @Override
    public void execute() {
        addProject();
    }

}
