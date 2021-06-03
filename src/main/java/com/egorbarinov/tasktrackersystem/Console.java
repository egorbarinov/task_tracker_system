package com.egorbarinov.tasktrackersystem;

import com.egorbarinov.tasktrackersystem.command.CommandStorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void readInput() {
        CommandStorage commandStorage = new CommandStorage();
        String prompt = "";
        while (!prompt.equals("exit")) {
            try {
                System.out.println("Для выхода наберите команду 'exit'");
                System.out.println("Доступные команды: ");
                commandStorage.showCommands().forEach(System.out::println);
                System.out.println("Что вы желаете сделать?");
                prompt = reader.readLine();

                if (prompt.isEmpty()) System.out.println("Ввод остался пустым или введена недопустимая команда.");
                else if ("show projects".equals(prompt)) commandStorage.executeCommand(prompt);
                else if ("add project".equals(prompt)) commandStorage.executeCommand(prompt);
                else if ("add user to project".equals(prompt)) commandStorage.executeCommand(prompt);
                else if ("find project by id".equals(prompt)) commandStorage.executeCommand(prompt);
                else if ("delete project by id".equals(prompt)) commandStorage.executeCommand(prompt);
                else if ("delete user from project".equals(prompt)) commandStorage.executeCommand(prompt);
                else if ("show users".equals(prompt)) commandStorage.executeCommand(prompt);
                else if ("add user".equals(prompt)) commandStorage.executeCommand(prompt);
                else if ("find user by id".equals(prompt)) commandStorage.executeCommand(prompt);
                else if ("delete user by id".equals(prompt)) commandStorage.executeCommand(prompt);
                else if ("add task".equals(prompt)) commandStorage.executeCommand(prompt);
                else if ("show tasks".equals(prompt)) commandStorage.executeCommand(prompt);
                else if ("add task to user".equals(prompt)) commandStorage.executeCommand(prompt);
                else if ("delete task by id".equals(prompt)) commandStorage.executeCommand(prompt);
                else if ("find task by id".equals(prompt)) commandStorage.executeCommand(prompt);
                else if ("delete task from user".equals(prompt)) commandStorage.executeCommand(prompt);
                else if ("showing all tasks by user".equals(prompt)) commandStorage.executeCommand(prompt);
                else if ("add task to project".equals(prompt)) commandStorage.executeCommand(prompt);
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Console console = new Console();
        console.readInput();


    }
}
