package edu.java.bot;

import edu.java.bot.commands.Command;
import edu.java.bot.commands.HelpCommand;
import edu.java.bot.commands.ListCommand;
import edu.java.bot.commands.StartCommand;
import edu.java.bot.commands.TrackCommand;
import edu.java.bot.commands.UntrackCommand;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CommandCenter {
    private final List<Command> nextCommandsAllowed = List.of(
        new HelpCommand(this),
        new StartCommand(),
        new TrackCommand(),
        new UntrackCommand(),
        new ListCommand()
    );

    private Command lastCommand;

    public List<Command> getAllowedCommands() {
        return nextCommandsAllowed;
    }

    public String setCommand(long id, String message) {
        for (Command it : nextCommandsAllowed) {
            if (it.getName().equals(message.trim().toLowerCase())) {
                if (lastCommand != null && lastCommand.waitNextCommand()
                    && !lastCommand.getName().equals(it.getName())) {
                    lastCommand.cleanNextCommand();
                }

                lastCommand = it;
                return it.getMessage(id, message).trim();
            }
        }

        if (lastCommand != null && lastCommand.waitNextCommand()) {
            return lastCommand.getMessage(id, message).trim();
        }

        return "Unexpected command";
    }
}
