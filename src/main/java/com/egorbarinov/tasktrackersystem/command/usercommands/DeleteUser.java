package com.egorbarinov.tasktrackersystem.command.usercommands;

import com.egorbarinov.tasktrackersystem.command.Executor;
import com.egorbarinov.tasktrackersystem.service.Service;
import com.egorbarinov.tasktrackersystem.service.UserServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeleteUser implements Executor {
    private Service service;
    private BufferedReader reader;
    private String enteredUserId;
    private Long userId;

    public DeleteUser() {
        this.service = new UserServiceImpl();
        this.reader = new BufferedReader(new InputStreamReader(System.in));    }

    private void deleteById() {
        boolean lock = true;
        while (lock) {
            System.out.println("Введите id пользователя, которого следует удалить: ");
            try {
                enteredUserId = reader.readLine();
                userId = Long.parseLong(enteredUserId);
                if (userId != 0) lock = false;
            }
            catch (NumberFormatException e) {
                System.out.println(" Вы ввели не числовое значение. Попробуйте снова:");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        service.deleteById(userId);
        System.out.println("Пользователь удален");
    }

    @Override
    public void execute() {
        deleteById();
    }
}
