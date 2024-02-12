package edu.java.bot.commands;

import edu.java.bot.DB;
import java.net.URI;
import java.net.URISyntaxException;

public class TrackUriCommand implements Command {
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
            var uri = new URI(message);
            DB.add(id, uri);
            return "Resource added to track";
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
