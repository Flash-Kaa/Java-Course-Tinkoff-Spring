package edu.java.bot;

import org.junit.Assert;
import org.junit.Test;

public class CommandsTest {
    private static final long ID = 1001;

    @Test
    public void testTrackAndListCommands() {
        CommandCenter commandCenter = new CommandCenter();

        String message = commandCenter.setCommand(ID, "/list");

        Assert.assertEquals(message, "Haven't resources to track");

        commandCenter.setCommand(ID, "/track");
        commandCenter.setCommand(ID, "/anyresource.uri");
        commandCenter.setCommand(ID, "/track");
        commandCenter.setCommand(ID, "/anyresource134.uri");

        String message2 = commandCenter.setCommand(ID, "/list");

        Assert.assertEquals(
            "/anyresource.uri\n/anyresource134.uri",
            message2
        );
    }

    @Test
    public void testUntrackCommand() {
        CommandCenter commandCenter = new CommandCenter();

        Assert.assertEquals(
            "Haven't resources to track",
            commandCenter.setCommand(ID, "/list")
        );

        commandCenter.setCommand(ID, "/track");
        commandCenter.setCommand(ID, "/anyresource.uri");
        commandCenter.setCommand(ID, "/track");
        commandCenter.setCommand(ID, "/anyresource134.uri");

        Assert.assertEquals(
            "/anyresource.uri\n/anyresource134.uri",
            commandCenter.setCommand(ID, "/list")
        );

        commandCenter.setCommand(ID, "/untrack");
        commandCenter.setCommand(ID, "/anyresource134.uri");

        Assert.assertEquals(
            "/anyresource.uri",
            commandCenter.setCommand(ID, "/list")
        );

        commandCenter.setCommand(ID, "/untrack");
        commandCenter.setCommand(ID, "/anyresource.uri");

        Assert.assertEquals(
            "Haven't resources to track",
            commandCenter.setCommand(ID, "/list")
        );
    }

    @Test
    public void testHelpCommand() {
        CommandCenter commandCenter = new CommandCenter();

        String message = commandCenter.setCommand(ID, "/help");

        commandCenter.getAllowedCommands().forEach(it -> {
            Assert.assertTrue(message.contains(it.getName()));
            Assert.assertTrue(message.contains(it.getDescription()));
        });
    }

    @Test
    public void testUnexpectedCommand() {
        CommandCenter commandCenter = new CommandCenter();

        Assert.assertEquals(
            "Unexpected command",
            commandCenter.setCommand(ID, "/listt")
        );
        Assert.assertEquals(
            "Unexpected command",
            commandCenter.setCommand(ID, "/")
        );
        Assert.assertEquals(
            "Unexpected command",
            commandCenter.setCommand(ID, "help")
        );
        Assert.assertEquals(
            "Unexpected command",
            commandCenter.setCommand(ID, "list")
        );
        Assert.assertEquals(
            "Unexpected command",
            commandCenter.setCommand(ID, "track")
        );
        Assert.assertEquals(
            "Unexpected command",
            commandCenter.setCommand(ID, "...")
        );
        Assert.assertEquals(
            "Unexpected command",
            commandCenter.setCommand(ID, " ")
        );
    }
}
