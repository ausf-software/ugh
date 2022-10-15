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

package ausf.software.bot.server;

import ausf.software.api.service.NotificationService;
import ausf.software.api.store.NotificationType;
import ausf.software.api.store.entity.NotificationEntity;
import ausf.software.bot.Config;
import ausf.software.util.DeleteMessageTimer;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Modal;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import org.jetbrains.annotations.NotNull;

import java.sql.Date;
import java.sql.Time;

/**
 * Слушатель для обработки уведомлений.
 *
 * @author Shcherbina Daniil
 * @author ***
 * @since 1.0
 * @version 1.0
 */
public class Notification extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("notification")) {
            notificationSlashCommand(event);
        }
    }

    @Override
    public void onSelectMenuInteraction(@NotNull SelectMenuInteractionEvent event) {
        if (event.getComponentId().equals("notification_type")) {
            notificationSelectType(event);
        }
    }

    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {
        if (event.getModalId().contains("notification.")) {
            notificationModalWindow(event);
        }
    }

    private void notificationSlashCommand(@NotNull SlashCommandInteractionEvent event) {
        SelectMenu type = SelectMenu.create("notification_type")
                .setPlaceholder("Ничего не выбрано")
                .setRequiredRange(1, 1)
                .addOption(NotificationType.PER_DAY.getTitle(), String.valueOf(NotificationType.PER_DAY.getValue()))
                .addOption(NotificationType.PER_HOUR.getTitle(), String.valueOf(NotificationType.PER_HOUR.getValue()))
                .addOption(NotificationType.PER_30.getTitle(), String.valueOf(NotificationType.PER_30.getValue()))
                .addOption(NotificationType.PER_15.getTitle(), String.valueOf(NotificationType.PER_15.getValue()))
                .addOption(NotificationType.ALL_TYPE.getTitle(), String.valueOf(NotificationType.ALL_TYPE.getValue()))
                .build();

        event.reply("Выберите тип уведомления")
                .setEphemeral(true)
                .addActionRow(type)
                .queue();
    }

    private void notificationSelectType(@NotNull SelectMenuInteractionEvent event) {
        TextInput data = TextInput.create("date", "Дата (ДД-ММ-ГГГГ)", TextInputStyle.SHORT)
                .setMinLength(10)
                .setMaxLength(10)
                .setRequired(true)
                .build();

        TextInput time = TextInput.create("time", "Время (ЧЧ:ММ:СС)", TextInputStyle.SHORT)
                .setMinLength(8)
                .setMaxLength(8)
                .setRequired(true)
                .build();

        TextInput text = TextInput.create("text", "Текст уведомления", TextInputStyle.PARAGRAPH)
                .setMinLength(5)
                .setRequired(true)
                .build();

        // кидаем и считываем далее тип уведомления в id модального окна,
        // чтобы не возиться с синхронизацией доступа к временному хранилищу
        Modal modal = Modal.create("notification." + event.getValues().get(0), "Создание уведомления")
                .addActionRows(ActionRow.of(data), ActionRow.of(time), ActionRow.of(text))
                .build();

        event.replyModal(modal).queue();
    }

    private void notificationModalWindow(@NotNull ModalInteractionEvent event) {
        String[] time = event.getValue("time").getAsString().split(":");
        String[] date = event.getValue("date").getAsString().split("-");
        String text = event.getValue("text").getAsString();
        byte type = Byte.parseByte(event.getModalId().replace("notification.", ""));

        event.deferReply().queue();

        if (time.length == 3 && date.length == 3) {
            NotificationService service = new NotificationService();

            service.add(new NotificationEntity(
                    type,
                    new Date(Integer.parseInt(date[2]) - 1900, Integer.parseInt(date[1]) - 1, Integer.parseInt(date[0])),
                    new Time(Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2])),
                    (byte) 0,
                    text
            ));

            DeleteMessageTimer.createTimer(event.getHook(), Config.NOTIFICATION_CREATE_SUCCESSFUL_TEXT);

        } else {
            event.reply("Ошибка! Были введены некорректные значения.").setEphemeral(true).queue();
        }
    }

}
