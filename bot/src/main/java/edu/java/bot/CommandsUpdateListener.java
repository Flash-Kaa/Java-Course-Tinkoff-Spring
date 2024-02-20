package edu.java.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.commands.CommandCenter;
import java.util.List;

public class CommandsUpdateListener implements UpdatesListener {
    private final TelegramBot bot;
    private final CommandCenter commandCenter;

    public CommandsUpdateListener(TelegramBot bot, CommandCenter commandCenter) {
        this.bot = bot;
        this.commandCenter = commandCenter;
    }

    @Override
    public int process(List<Update> list) {
        for (var upd : list) {
            bot.execute(
                new SendMessage(
                    upd.message().chat().id(),
                    commandCenter.setCommand(upd.message().chat().id(), upd.message().text())
                )
            );
        }

        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
