/*
 * Copyright © 2022 Shcherbina Daniil
 * License: http://opensource.org/licenses/MIT
 */

package ausf.software.bot;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.interactions.InteractionHook;

import java.util.concurrent.TimeUnit;

public class DeleteMessageTimer {

    public static void createTimer(InteractionHook hook, String messageText) {
        hook.sendMessage(messageText+ " " + Config.DELETE_MESSAGE_TEXT + "5 секунд...")
                .setEphemeral(true)
                .delay(1, TimeUnit.SECONDS)
                .flatMap(message -> message.editMessage(messageText + " " + Config.DELETE_MESSAGE_TEXT + "4 секунды..."))
                .delay(1, TimeUnit.SECONDS)
                .flatMap(message -> message.editMessage(messageText + " " + Config.DELETE_MESSAGE_TEXT + "3 секунды..."))
                .delay(1, TimeUnit.SECONDS)
                .flatMap(message -> message.editMessage(messageText + " " + Config.DELETE_MESSAGE_TEXT + "2 секунды..."))
                .delay(1, TimeUnit.SECONDS)
                .flatMap(message -> message.editMessage(messageText + " " + Config.DELETE_MESSAGE_TEXT + "1 секунду..."))
                .delay(1, TimeUnit.SECONDS)
                .flatMap(Message::delete)
                .timeout(10, TimeUnit.SECONDS)
                .queue();
    }

}
