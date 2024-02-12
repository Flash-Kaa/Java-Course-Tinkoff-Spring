package edu.java.bot.commands;

import edu.java.bot.DB;

public class StartCommand implements Command {
    @Override
    public String getName() {
        return "/start";
    }

    @Override
    public String getDescription() {
        return "register user";
    }

    @Override
    public String getMessage(long id, String message) {
        DB.addUser(id);
        return "User added";
    }

    @Override
    public boolean waitNextCommand() {
        return false;
    }

    @Override
    public void cleanNextCommand() {
    }
}
