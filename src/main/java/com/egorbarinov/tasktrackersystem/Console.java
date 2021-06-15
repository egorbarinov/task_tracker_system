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

                if (prompt.isEmpty()) {
                    throw new IllegalArgumentException("Ввод остался пустым или введена недопустимая команда.");
                }

                if (!commandStorage.isCommandExist(prompt)) {
                    throw new IllegalArgumentException("Ввод остался пустым или введена недопустимая команда.");
                }
                commandStorage.executeCommand(prompt);
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
