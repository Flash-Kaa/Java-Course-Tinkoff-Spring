package edu.java.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.request.SetMyCommands;
import edu.java.bot.commands.Command;
import edu.java.bot.commands.CommandsUpdateListener;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TelegramApi {
    private final TelegramBot bot;
    private CommandCenter commandCenter;

    public TelegramApi() {
        bot = new TelegramBot(System.getenv("APP_TELEGRAM_TOKEN"));
        commandCenter = new CommandCenter();

        addMenu();
        bot.setUpdatesListener(new CommandsUpdateListener(bot, commandCenter));
    }

    private void addMenu() {
        BotCommand[] botCommands = new BotCommand[commandCenter.getAllowedCommands().size()];

        for (int i = 0; i < botCommands.length; i++) {
            Command command = commandCenter.getAllowedCommands().get(i);
            botCommands[i] = new BotCommand(command.getName(), command.getDescription());
        }

        bot.execute(new SetMyCommands(botCommands));
    }
}
