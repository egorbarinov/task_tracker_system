package com.egorbarinov.tasktrackersystem.command;

import com.egorbarinov.tasktrackersystem.command.projectcommands.*;
import com.egorbarinov.tasktrackersystem.command.taskcommands.*;
import com.egorbarinov.tasktrackersystem.command.usercommands.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import java.util.Map;
import java.util.Set;

public class CommandStorage {
    private static final Map<String, Command> COMMANDS = new HashMap<>();
    private final BufferedReader reader;

    public CommandStorage() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        setCommands();
    }

    private void setCommands() {
        COMMANDS.put("show projects", new ShowProjectsCommand());
        COMMANDS.put("add project", new CreateProjectCommand(reader));
        COMMANDS.put("add user to project", new AddUserToProjectCommand(reader));
        COMMANDS.put("find project by id", new FindProjectByIdCommand(reader));
        COMMANDS.put("delete project by id", new DeleteProjectCommand(reader));
        COMMANDS.put("delete user from project", new DeleteUserFromProjectCommand(reader));
        COMMANDS.put("show users", new ShowUsersCommand());
        COMMANDS.put("add user", new CreateUserCommand(reader));
        COMMANDS.put("find user by id", new FindUserByIdCommand(reader));
        COMMANDS.put("delete user by id", new DeleteUserCommand(reader));
        COMMANDS.put("add task", new CreateTaskCommand(reader));
        COMMANDS.put("show tasks", new ShowTasksCommand());
        COMMANDS.put("add task to user", new AddTaskToUserCommand(reader));
        COMMANDS.put("delete task by id", new DeleteTaskCommand(reader));
        COMMANDS.put("delete task from user", new DeleteTaskFromUserCommand(reader));
        COMMANDS.put("find task by id", new FindTaskByIdCommand(reader));
        COMMANDS.put("showing all tasks by user", new ShowingAllTaskByUserCommand(reader));
        COMMANDS.put("add task to project", new AddTaskToProjectCommand(reader));
    }

    public Set<String> showCommands() {
        return COMMANDS.keySet();
    }

    public void executeCommand(String command) throws IOException {
        COMMANDS.get(command).execute();
    }

    public boolean isCommandExist(String command) {
        return COMMANDS.containsKey(command);
    }

}
