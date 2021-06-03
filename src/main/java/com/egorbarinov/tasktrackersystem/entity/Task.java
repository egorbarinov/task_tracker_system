package com.egorbarinov.tasktrackersystem.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(schema = "task_tracker_system", name = "tasks")
public class Task extends AbstractTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinTable(schema = "task_tracker_system", name = "users_tasks",
            joinColumns = @JoinColumn(name = "tasks_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id"))
    private User user;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(schema = "task_tracker_system", name = "projects_tasks",
            joinColumns = @JoinColumn(name = "tasks_id"),
            inverseJoinColumns = @JoinColumn(name = "projects_id"))
    private List<Project> projects;

    @Override
    public void taskReport() {
        System.out.println("assigned tasks:");

    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id.equals(task.id) && name.equals(task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
