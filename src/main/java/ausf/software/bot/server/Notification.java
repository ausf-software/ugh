/*
 * Copyright © 2022 Shcherbina Daniil
 * License: http://opensource.org/licenses/MIT
 */

package ausf.software.bot.server;

import ausf.software.api.service.NotificationService;
import ausf.software.api.store.NotificationType;
import ausf.software.api.store.entity.NotificationEntity;
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
import java.util.concurrent.TimeUnit;

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

        if (time.length == 3 && date.length == 3) {
            NotificationService service = new NotificationService();
            event.reply("Уведомление было успешно создано")
                    .setEphemeral(true)
                    .timeout(5, TimeUnit.SECONDS)
                    .queue();

            service.add(new NotificationEntity(
                    type,
                    new Date(Integer.parseInt(date[2]) - 1900, Integer.parseInt(date[1]) - 1, Integer.parseInt(date[0])),
                    new Time(Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2])),
                    (byte) 0,
                    text
            ));
        } else {
            event.reply("Ошибка! Были введены некорректные значения.").setEphemeral(true).queue();
        }
    }

}
