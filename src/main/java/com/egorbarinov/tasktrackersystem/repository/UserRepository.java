package com.egorbarinov.tasktrackersystem.repository;

import java.util.List;

public class UserRepository<User> extends RepositoryImpl<User, Long> {

    public UserRepository(Class<User> typeClass) {
        super(typeClass);
    }

    @Override
    public User findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }

    @Override
    public void save(User entity) {
        super.save(entity);
    }

    @Override
    public void delete(User entity) {
        super.delete(entity);
    }

    @Override
    public List<User> findAll() {
        return super.findAll();
    }
}
