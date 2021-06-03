package com.egorbarinov.tasktrackersystem.command.usercommands;

import com.egorbarinov.tasktrackersystem.command.Executor;
import com.egorbarinov.tasktrackersystem.entity.User;
import com.egorbarinov.tasktrackersystem.service.Service;
import com.egorbarinov.tasktrackersystem.service.UserServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreateUser implements Executor {
    private Service service;
    private User user;
    private BufferedReader reader;
    private String name;

    public CreateUser() {
        this.service = new UserServiceImpl();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private void addUser() {
        boolean lock = true;
        while (lock) {
            System.out.println("Введите имя пользователя, не менее 4 символов: ");
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
        this.user = new User(name);
        service.save(user);
        System.out.println("Пользователь добавлен: " + name);
    }


    @Override
    public void execute() {
        addUser();
    }
}
