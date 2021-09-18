package com.egorbarinov.tasktrackersystem;

import com.egorbarinov.tasktrackersystem.command.CommandStorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class Console {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void readInput() {

        var commandStorage = new CommandStorage();
        var prompt = "";

        while (!prompt.equals("exit")) {
            try {
                System.out.println("Для выхода наберите команду 'exit'");
                System.out.println("Доступные команды: ");
                commandStorage.showCommands().forEach(System.out::println);
                System.out.println("Что вы желаете сделать?");
                prompt = reader.readLine();

                if (prompt.equals("exit")) {
                    System.out.println("Работа приложения завершена.");
                } else if (prompt.isEmpty() || !commandStorage.isCommandExist(prompt)) {
                    System.out.println("Ввод остался пустым или введена недопустимая команда");
                } else {
                    commandStorage.executeCommand(prompt);
                    System.out.println("Press Enter key to continue...");
                    reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    public static void main(String[] args) {
        var console = new Console();
        console.readInput();

    }
}
