package edu.java.bot.commands;

import edu.java.bot.CommandCenter;
import java.util.List;

public class HelpCommand implements Command {
    private final CommandCenter commandCenter;
    private List<Command> allowedCommands;

    public HelpCommand(CommandCenter commandCenter) {
        this.commandCenter = commandCenter;
    }

    @Override
    public String getName() {
        return "/help";
    }

    @Override
    public String getDescription() {
        return "get a list of commands";
    }

    @Override
    public String getMessage(long id, String message) {
        if (allowedCommands == null) {
            allowedCommands = commandCenter.getAllowedCommands();
        }

        StringBuilder sb = new StringBuilder();

        for (Command it : allowedCommands) {
            sb.append(it.getName())
                .append(" - ")
                .append(it.getDescription())
                .append("\n");
        }

        return sb.toString();
    }

    @Override
    public boolean waitNextCommand() {
        return false;
    }

    @Override
    public void cleanNextCommand() {
    }
}
