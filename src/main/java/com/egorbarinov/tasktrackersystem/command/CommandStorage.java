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
        COMMANDS.put("show projects", new ShowProjectsCommand());
        COMMANDS.put("add project", new CreateProjectCommand());
        COMMANDS.put("add user to project", new AddUserToProjectCommand());
        COMMANDS.put("find project by id", new FindProjectByIdCommand());
        COMMANDS.put("delete project by id", new DeleteProjectCommand());
        COMMANDS.put("delete user from project", new DeleteUserFromProjectCommand());
        COMMANDS.put("show users", new ShowUsersCommand());
        COMMANDS.put("add user", new CreateUserCommand());
        COMMANDS.put("find user by id", new FindUserByIdCommand());
        COMMANDS.put("delete user by id", new DeleteUserCommand());
        COMMANDS.put("add task", new CreateTaskCommand());
        COMMANDS.put("show tasks", new ShowTasksCommand());
        COMMANDS.put("add task to user", new AddTaskToUserCommand());
        COMMANDS.put("delete task by id", new DeleteTaskCommand());
        COMMANDS.put("delete task from user", new DeleteTaskFromUserCommand());
        COMMANDS.put("find task by id", new FindTaskByIdCommand());
        COMMANDS.put("showing all tasks by user", new ShowingAllTaskByUserCommand());
        COMMANDS.put("add task to project", new AddTaskToProjectCommand());
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
