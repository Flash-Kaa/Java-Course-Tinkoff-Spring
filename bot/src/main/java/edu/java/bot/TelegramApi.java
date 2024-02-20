package edu.java.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.request.SetMyCommands;
import edu.java.bot.commands.CommandCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TelegramApi {
    private final TelegramBot bot;
    private final CommandCenter commandCenter;

    @Autowired
    public TelegramApi(TelegramBot bot, CommandCenter commandCenter) {
        this.bot = bot;
        this.commandCenter = commandCenter;

        addMenu();
        bot.setUpdatesListener(new CommandsUpdateListener(bot, commandCenter));
    }

    private void addMenu() {
        BotCommand[] botCommands = commandCenter.getAllowedCommands()
            .stream()
            .map(command -> new BotCommand(command.getName(), command.getDescription()))
            .toArray(BotCommand[]::new);

        bot.execute(new SetMyCommands(botCommands));
    }
}
