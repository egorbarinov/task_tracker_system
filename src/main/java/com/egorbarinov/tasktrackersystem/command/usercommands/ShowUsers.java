package com.egorbarinov.tasktrackersystem.command.usercommands;

import com.egorbarinov.tasktrackersystem.command.Command;
import com.egorbarinov.tasktrackersystem.entity.User;
import com.egorbarinov.tasktrackersystem.repository.UserRepository;

import java.util.List;

public class ShowUsers implements Command {
    private final UserRepository<User> userRepository;

    public ShowUsers() {
        this.userRepository = new UserRepository<>(User.class);
    }

    @Override
    public void execute() {
        showUsers().forEach(System.out::println);
    }

    private List<User> showUsers() {
        return userRepository.findAll();
    }

}
