/*
 * Copyright © 2022 Shcherbina Daniil
 * License: http://opensource.org/licenses/MIT
 */

package ausf.software;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CommandHandler extends ListenerAdapter {



    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        //System.out.println(event.getMessage().getContentDisplay());
        if (!event.getAuthor().isBot()) {
            String message = event.getMessage().getContentDisplay();
            if (message.toCharArray()[0] == '*') {
                checkCommand(event, message);
            }
        }
    }

    private void checkCommand(@NotNull MessageReceivedEvent event, @NotNull String message){
        String[] arg = message.split(":");
        if (arg[0].equals("*расписание")) {
            event.getChannel().sendMessage(TimetableGenerator.getInstance().getTimetable()).submit();
        }
    }

}
