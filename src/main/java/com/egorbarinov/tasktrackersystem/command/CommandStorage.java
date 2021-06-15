package com.egorbarinov.tasktrackersystem.command;

import com.egorbarinov.tasktrackersystem.command.projectcommands.*;
import com.egorbarinov.tasktrackersystem.command.taskcommands.*;
import com.egorbarinov.tasktrackersystem.command.usercommands.*;

import java.util.HashMap;

import java.util.Map;
import java.util.Set;

public class CommandStorage {

    private static final Map<String, Command> COMMANDS = new HashMap<>();

    public CommandStorage() {
        setCommands();
    }

    private void setCommands() {
        COMMANDS.put("show projects", new ShowProjects());
        COMMANDS.put("add project", new CreateProject());
        COMMANDS.put("add user to project", new AddUserToProject());
        COMMANDS.put("find project by id", new FindProjectById());
        COMMANDS.put("delete project by id", new DeleteProject());
        COMMANDS.put("delete user from project", new DeleteUserFromProject());
        COMMANDS.put("show users", new ShowUsers());
        COMMANDS.put("add user", new CreateUser());
        COMMANDS.put("find user by id", new FindUserById());
        COMMANDS.put("delete user by id", new DeleteUser());
        COMMANDS.put("add task", new CreateTask());
        COMMANDS.put("show tasks", new ShowTasks());
        COMMANDS.put("add task to user", new AddTaskToUser());
        COMMANDS.put("delete task by id", new DeleteTask());
        COMMANDS.put("delete task from user", new DeleteTaskFromUser());
        COMMANDS.put("find task by id", new FindTaskById());
        COMMANDS.put("showing all tasks by user", new ShowingAllTaskByUser());
        COMMANDS.put("add task to project", new AddTaskToProject());
    }

    public Set<String> showCommands() {
        return COMMANDS.keySet();
    }

    public void executeCommand(String command) {
        COMMANDS.get(command).execute();
    }
    public boolean isCommandExist(String command) {
        return COMMANDS.containsKey(command);
    }

}
