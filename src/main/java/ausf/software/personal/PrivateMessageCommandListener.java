/*
 * Copyright © 2022 Shcherbina Daniil, ***
 * License: http://opensource.org/licenses/MIT
 */

package ausf.software.personal;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * Слушатель для обработки команд из личных сообщений бота.
 *
 * @author Shcherbina Daniil
 * @author ***
 * @since 1.0
 * @version 1.0
 */
public class PrivateMessageCommandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().hasPrivateChannel()) {
            System.out.println("private message");
        }
    }

}
