package edu.java.bot.commands;

import java.util.List;
import java.util.function.Supplier;

public class HelpCommand implements Command {
    private final Supplier<List<Command>> allowedCommands;

    public HelpCommand(CommandCenter commandCenter) {
        allowedCommands = commandCenter::getAllowedCommands;
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
    public String execute(long id, String message) {
        StringBuilder sb = new StringBuilder();

        for (Command it : allowedCommands.get()) {
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
