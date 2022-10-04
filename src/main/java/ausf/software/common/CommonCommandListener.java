/*
 * Copyright © 2022 Shcherbina Daniil, ***
 * License: http://opensource.org/licenses/MIT
 */

package ausf.software.common;

import ausf.software.Config;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * Слушатель для обработки команд из любого места.
 *
 * @author Shcherbina Daniil
 * @author ***
 * @since 1.0
 * @version 1.0
 */
public class CommonCommandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            String message = event.getMessage().getContentDisplay();
            if (message.toCharArray()[0] == Config.commandTrigger) {
                checkCommand(event, message);
            }
        }
    }

    private void checkCommand(@NotNull MessageReceivedEvent event, @NotNull String message){
        String[] arg = message.split(":");
        if (arg[0].equals(Config.commandTrigger + "расписание")) {
            event.getChannel().sendMessage("ну допустим").submit();
        }
    }

}
