package edu.java.bot.commands;

import edu.java.bot.DB;
import java.net.URI;
import java.util.List;

public class ListCommand implements Command {
    @Override
    public String getName() {
        return "/list";
    }

    @Override
    public String getDescription() {
        return "get a list of all track resources";
    }

    @Override
    public String getMessage(long id, String message) {
        StringBuilder sb = new StringBuilder();
        List<URI> resources = DB.getAllUserResources(id);

        if (resources.isEmpty()) {
            return "Haven't resources to track";
        }

        resources.forEach(it ->
            sb.append(it.toString())
                .append("\n")
        );

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
