package com.example.splitwiseapplication.commands;

import java.util.ArrayList;
import java.util.List;

public class CommandExecutor {

    private List<CommandInterface> commands = new ArrayList<>();

    public void addCommand(CommandInterface command) {
        commands.add(command);
    }

    public void removeCommand(CommandInterface command) {
        commands.remove(command);
    }

    public void executeCommand(String input) {
        for(CommandInterface command: commands) {
            if(command.matches(input)) {
                command.execute(input);
                break;
            }
        }
    }
}
