package edu.java.bot.commands;

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
                return it.execute(id, message).trim();
            }
        }

        if (lastCommand != null && lastCommand.waitNextCommand()) {
            return lastCommand.execute(id, message).trim();
        }

        return "Unexpected command";
    }
}
