package ru.BouH.init;

import ausf.software.Config;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main  extends ListenerAdapter {
    private final RegisterEvents registerEvents = new RegisterEvents();

    public Main(String token) {
        List<GatewayIntent> intents = new ArrayList<>();
        intents.add(GatewayIntent.DIRECT_MESSAGES);
        this.registerEvents.registerEventClass("ru.BouH.Commands");
        JDA jda = JDABuilder.createDefault(token, intents).build();
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
                    method.invoke(method.getDeclaringClass().newInstance(), Arrays.copyOfRange(command, 2, command.length), event);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
