package edu.java.bot.commands;

public interface Command {
    String getName();

    String getDescription();

    String getMessage(long id, String message);

    boolean waitNextCommand();

    void cleanNextCommand();
}
