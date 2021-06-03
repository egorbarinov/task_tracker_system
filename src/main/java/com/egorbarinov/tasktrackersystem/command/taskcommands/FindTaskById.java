package com.egorbarinov.tasktrackersystem.command.taskcommands;

import com.egorbarinov.tasktrackersystem.command.Executor;
import com.egorbarinov.tasktrackersystem.entity.Task;
import com.egorbarinov.tasktrackersystem.entity.User;
import com.egorbarinov.tasktrackersystem.service.Service;
import com.egorbarinov.tasktrackersystem.service.TaskServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindTaskById implements Executor {
    private Service service;
    private BufferedReader reader;
    private String enteredUTaskId;
    private Long taskId;

    public FindTaskById() {
        this.service = new TaskServiceImpl();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private Task findById() {
        boolean lock = true;
        while (lock) {
            System.out.println("Введите id задачи: ");
            try {
                enteredUTaskId = reader.readLine();
                taskId = Long.parseLong(enteredUTaskId);
                if (taskId != 0) lock = false;
            }
            catch (NumberFormatException e) {
                System.out.println(" Вы ввели не числовое значение. Попробуйте снова:");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return (Task) service.findById(taskId);

    }

    @Override
    public void execute() {
        System.out.println(findById());
    }
}
