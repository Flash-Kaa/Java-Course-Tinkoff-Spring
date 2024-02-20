package edu.java.bot.commands;

import edu.java.bot.DB;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.extern.log4j.Log4j2;

@Log4j2
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
    public String execute(long id, String message) {
        try {
            if (DB.remove(id, new URI(message))) {
                return "Resource untracked";
            }

            return "Resource unexpected";
        } catch (URISyntaxException e) {
            log.error(
                String.format(
                    "Chat %d. Unexpected resource to untrack: '%s'. Exception: '%s'", id, message, e.getMessage()
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
