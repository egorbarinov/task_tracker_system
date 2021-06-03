package com.egorbarinov.tasktrackersystem.repository;

import java.util.List;

public interface Repository<T, ID>{

    T findById(ID id);
    void save(T entity);
    void deleteById(ID id);
    void delete(T entity);
    List<T> findAll();
    void update(T entity);
}
