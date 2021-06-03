package com.egorbarinov.tasktrackersystem.service;

import java.util.List;

public interface Service<T> {
    T findById(Long id);
    List<T> findAll();
    void deleteById(Long id);
    void save(T entity);
    void update(T entity);
}
