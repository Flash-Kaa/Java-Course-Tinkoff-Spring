package edu.java.bot.commands;

import edu.java.bot.DB;
import java.net.URI;
import java.net.URISyntaxException;

public class UntrackUriCommand implements Command {
    @Override
    public String getName() {
        return "uri";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String getMessage(long id, String message) {
        try {
            if (DB.remove(id, new URI(message))) {
                return "Resource untracked";
            }

            return "Resource unexpected";
        } catch (URISyntaxException e) {
            return "Illegal resource";
        }
    }

    @Override
    public boolean waitNextCommand() {
        return false;
    }

    @Override
    public void cleanNextCommand() {
    }
}
