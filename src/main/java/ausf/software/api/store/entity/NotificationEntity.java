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

package ausf.software.api.store.entity;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.sql.Date;
import java.sql.Time;

/**
 * Описывает элемент из таблицы, содержащей информацию о уведомлениях.
 *
 * @see ausf.software.api.store.NotificationType
 * @see ausf.software.api.store.NotificationStatus
 * @author Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
@Entity
@Table(name = "notification")
public class NotificationEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @Column (name = "notification_type")
    @NotNull
    private byte notificationType;

    @Column (name = "date")
    @NotNull
    private Date date;

    @Column (name = "time")
    @NotNull
    private Time time;

    @Column (name = "notification_status")
    @NotNull
    private byte notificationStatus;

    @Column (name = "text")
    @NotNull
    private String text;

    public NotificationEntity(@NotNull byte notificationType, @NotNull Date date, @NotNull Time time,
                              @NotNull byte notificationStatus, @NotNull String text) {
        this.notificationType = notificationType;
        this.date = date;
        this.notificationStatus = notificationStatus;
        this.text = text;
        this.time = time;
    }

    public NotificationEntity() {
    }

    public int getId() {
        return id;
    }

    public byte getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(@NotNull byte notificationType) {
        this.notificationType = notificationType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(@NotNull Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(@NotNull String text) {
        this.text = text;
    }

    public byte getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(@NotNull byte notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(@NotNull Time time) {
        this.time = time;
    }
}
