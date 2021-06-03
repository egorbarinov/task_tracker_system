package com.egorbarinov.tasktrackersystem.command.usercommands;

import com.egorbarinov.tasktrackersystem.command.Executor;
import com.egorbarinov.tasktrackersystem.entity.User;
import com.egorbarinov.tasktrackersystem.service.Service;
import com.egorbarinov.tasktrackersystem.service.UserServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindUserById implements Executor {
    private UserServiceImpl service;
    private BufferedReader reader;
    private String enteredUserId;
    private Long userId;


    public FindUserById() {
        this.service = new UserServiceImpl();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private User findById() {
        boolean lock = true;
        while (lock) {
            System.out.println("Введите id пользователя: ");
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
        return service.findById(userId);

    }

    @Override
    public void execute() {
        System.out.println(findById());
    }
}
