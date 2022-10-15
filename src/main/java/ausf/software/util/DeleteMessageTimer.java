/*
 * Copyright © 2022 Shcherbina Daniil and BouH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ausf.software.util;

import ausf.software.bot.Config;
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
