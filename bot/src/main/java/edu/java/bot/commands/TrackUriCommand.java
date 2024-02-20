package edu.java.bot.commands;

import edu.java.bot.DB;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.extern.log4j.Log4j2;

@Log4j2
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
    public String execute(long id, String message) {
        try {
            var uri = new URI(message);
            DB.add(id, uri);
            return "Resource added to track";
        } catch (URISyntaxException e) {
            log.error(
                String.format(
                    "Chat %d. Unexpected resource to track: '%s'. Exception: '%s'", id, message, e.getMessage()
                )
            );
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
