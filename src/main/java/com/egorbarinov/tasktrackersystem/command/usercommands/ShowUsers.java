package com.egorbarinov.tasktrackersystem.command.usercommands;

import com.egorbarinov.tasktrackersystem.command.Executor;
import com.egorbarinov.tasktrackersystem.entity.User;
import com.egorbarinov.tasktrackersystem.service.Service;
import com.egorbarinov.tasktrackersystem.service.UserServiceImpl;

import java.util.List;

public class ShowUsers implements Executor {
    private Service service;

    public ShowUsers() {
        this.service = new UserServiceImpl();
    }

    private List<User> showUsers() {
        return service.findAll();
    }

    @Override
    public void execute() {
        showUsers().forEach(System.out::println);
    }
}
