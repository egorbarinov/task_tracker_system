package com.egorbarinov.tasktrackersystem.command.taskcommands;

import com.egorbarinov.tasktrackersystem.command.Command;
import com.egorbarinov.tasktrackersystem.entity.Task;
import com.egorbarinov.tasktrackersystem.repository.TaskRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreateTaskCommand implements Command {
    private final TaskRepository<Task> taskRepository;
    private final BufferedReader reader;
    private String name;

    public CreateTaskCommand(BufferedReader reader) {
        this.taskRepository = new TaskRepository<>(Task.class);
        this.reader = reader;
    }

    @Override
    public void execute() {
        addProject();
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
        Task task = new Task(name);
        taskRepository.save(task);
        System.out.println("Задача создана: " + name);
    }

}
