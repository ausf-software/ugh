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

package ausf.software.bot.common;

import ausf.software.bot.Config;
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
            if (message.toCharArray()[0] == Config.COMMAND_TRIGGER) {
                checkCommand(event, message);
            }
        }
    }

    private void checkCommand(@NotNull MessageReceivedEvent event, @NotNull String message){
        String[] arg = message.split(String.valueOf(Config.COMMAND_ARG_SPLITTER));
        if (arg[0].equals(Config.COMMAND_TRIGGER + "расписание")) {
            event.getChannel().sendMessage("ну допустим").submit();
        }
    }

}
