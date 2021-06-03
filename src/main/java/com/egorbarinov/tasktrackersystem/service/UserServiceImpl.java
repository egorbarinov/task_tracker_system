package com.egorbarinov.tasktrackersystem.service;

import com.egorbarinov.tasktrackersystem.entity.User;
import com.egorbarinov.tasktrackersystem.repository.UserRepository;

import java.util.List;

public class UserServiceImpl implements Service<User>{

    private UserRepository repository;

    public UserServiceImpl() {
        this.repository = new UserRepository(User.class);
    }

    @Override
    public User findById(Long id) {
        return (User) repository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);

    }

    @Override
    public void save(User user) {
        repository.save(user);
    }

    @Override
    public void update(User user) { repository.update(user);
    }


}
