package ru.BouH.init;

import ausf.software.bot.Config;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ru.BouH.events.EventCommand;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class EventHandler extends ListenerAdapter {
    private final RegisterEvents registerEvents = new RegisterEvents();

    public EventHandler(JDA jda) {
        this.registerEvents.registerEventClass("ru.BouH.Commands");
        jda.addEventListener(this);
    }

    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().toString().toLowerCase();
        if (message.startsWith(String.valueOf(Config.commandTrigger))) {
            String formattedMsg = message.substring(1);
            String[] command = formattedMsg.split(" ");
            if (this.registerEvents.getMethodMap().containsKey(command[0])) {
                Method method = this.registerEvents.getMethodMap().get(command[0]);
                try {
                    if (!((boolean) method.invoke(method.getDeclaringClass().newInstance(), Arrays.copyOfRange(command, 1, command.length), event))) {
                        event.getChannel().sendMessage("/" + command[0] + " " + method.getAnnotation(EventCommand.class).usage()).submit();
                    }
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
