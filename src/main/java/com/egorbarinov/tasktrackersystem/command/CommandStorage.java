package com.egorbarinov.tasktrackersystem.command;

import com.egorbarinov.tasktrackersystem.command.projectcommands.*;
import com.egorbarinov.tasktrackersystem.command.taskcommands.*;
import com.egorbarinov.tasktrackersystem.command.usercommands.*;

import java.util.HashMap;

import java.util.Map;
import java.util.Set;

public class CommandStorage {

    private static final Map<String, Executor> commands = new HashMap<>();

    public CommandStorage() {
        setCommands();
    }

    private void setCommands() {
        commands.put("show projects", new ShowProjects());
        commands.put("add project", new CreateProject());
        commands.put("add user to project", new AddUserToProject());
        commands.put("find project by id", new FindProjectById());
        commands.put("delete project by id", new DeleteProject());
        commands.put("delete user from project", new DeleteUserFromProject());
        commands.put("show users", new ShowUsers());
        commands.put("add user", new CreateUser());
        commands.put("find user by id", new FindUserById());
        commands.put("delete user by id", new DeleteUser());
        commands.put("add task", new CreateTask());
        commands.put("show tasks", new ShowTasks());
        commands.put("add task to user", new AddTaskToUser());
        commands.put("delete task by id", new DeleteTask());
        commands.put("delete task from user", new DeleteTaskFromUser());
        commands.put("find task by id", new FindTaskById());
        commands.put("showing all tasks by user", new ShowingAllTaskByUser());
        commands.put("add task to project", new AddTaskToProject());
    }

    public Set<String> showCommands() {
        return commands.keySet();
    }

    public void executeCommand(String command) {
        commands.get(command).execute();
    }

}
