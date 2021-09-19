package com.egorbarinov.tasktrackersystem.command.taskcommands;

import com.egorbarinov.tasktrackersystem.command.Command;
import com.egorbarinov.tasktrackersystem.entity.Task;
import com.egorbarinov.tasktrackersystem.repository.TaskRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindTaskByIdCommand implements Command {
    private final TaskRepository<Task> taskRepository;
    private final BufferedReader reader;
    private Long taskId;
    public FindTaskByIdCommand(BufferedReader reader) {
        this.taskRepository = new TaskRepository<>(Task.class);
        this.reader = reader;
    }

    @Override
    public void execute() {
        System.out.println(findById());
    }

    private Task findById() {
        boolean lock = true;
        while (lock) {
            System.out.println("Введите id задачи: ");
            try {
                String enteredUTaskId = reader.readLine();
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
        return taskRepository.findById(taskId);

    }

}
