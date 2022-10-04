package ru.BouH.events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import ru.BouH.utils.EventCommand;

public class Commands implements CommandBase {
    @EventCommand(command = "table")
    public void getTimeTable(String[] args, MessageReceivedEvent event) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Дингльбоб");
        event.getChannel().sendMessage(stringBuilder).submit();
    }
}
