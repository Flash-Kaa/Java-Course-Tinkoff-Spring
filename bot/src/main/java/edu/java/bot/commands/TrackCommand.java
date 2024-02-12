package edu.java.bot.commands;

import java.util.List;

public class TrackCommand implements Command {
    private final List<Command> nextCommandsAllowed = List.of(
        new TrackUriCommand()
    );

    private Command nextCommand;

    @Override
    public String getName() {
        return "/track";
    }

    @Override
    public String getDescription() {
        return "add resource to track list";
    }

    @Override
    public String getMessage(long id, String message) {
        if (nextCommand != null) {
            String botMessage = nextCommand.getMessage(id, message);

            if (!nextCommand.waitNextCommand()) {
                nextCommand = null;
            }

            return botMessage;
        }

        nextCommand = nextCommandsAllowed.stream()
            .filter(it -> it.getName().equals("uri"))
            .findFirst()
            .get();

        return "Print uri to track";
    }

    @Override
    public boolean waitNextCommand() {
        return nextCommand != null;
    }

    @Override
    public void cleanNextCommand() {
        nextCommand = null;
    }
}
