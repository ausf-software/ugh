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

package ausf.software.bot.channel;

import ausf.software.bot.Config;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * Слушатель для обработки команд служащих для настройки
 * бота. Обработка комманд происходит из специально отведенной
 * категории и текстового канала.
 *
 * @author Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
public class SettingsCommandListener extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getGuild().getMemberById(event.getUser().getId()).hasPermission(Permission.ADMINISTRATOR)) {
            checkCommands(event);
        }
    }

    public void checkCommands(SlashCommandInteractionEvent event) {
        if(event.getName().equals("set-notification-addresses")) {
            Config.NOTIFICATION_ADDRESSES = event.getOption("role").getAsRole();
            event.reply(Config.NOTIFICATION_ADDRESSES_CREATE_SUCCESSFUL_TEXT).setEphemeral(true).queue();
        }
    }

}
