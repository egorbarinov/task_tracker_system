package com.egorbarinov.tasktrackersystem.command.taskcommands;

import com.egorbarinov.tasktrackersystem.command.Executor;
import com.egorbarinov.tasktrackersystem.service.Service;
import com.egorbarinov.tasktrackersystem.service.TaskServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeleteTask implements Executor {
    private Service taskService;
    private BufferedReader reader;
    private String enteredTaskId;
    private Long taskId;

    public DeleteTask() {
        this.taskService = new TaskServiceImpl();
        this.reader = new BufferedReader(new InputStreamReader(System.in));;;
    }

    private void deleteById() {
        boolean lock = true;
        while (lock) {
            System.out.println("Введите id задачи, которую следует удалить: ");
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
        taskService.deleteById(taskId);
        System.out.println("Задача удалена");
    }

    @Override
    public void execute() {
        deleteById();
    }
}
