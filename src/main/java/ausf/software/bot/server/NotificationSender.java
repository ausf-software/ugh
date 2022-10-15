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
import ausf.software.api.store.NotificationStatus;
import ausf.software.api.store.NotificationType;
import ausf.software.api.store.entity.NotificationEntity;
import ausf.software.bot.Config;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class NotificationSender extends Thread {

    private Logger logger;
    private int sleepTime = 600000;
    private NotificationService service;
    private List<NotificationEntity> entities;
    private Date currentDate;
    private boolean isRun = false;

    @Override
    public void run()
    {
        isRun = true;
        if (Config.NOTIFICATION_ADDRESSES != null) {
            this.setDaemon(true);
            service = new NotificationService();

            while (true) {
                entities = service.getNotificationByStatus(NotificationStatus.WAITING.getValue());
                currentDate = Calendar.getInstance().getTime();
                checkDate();
                checkTime();
                send();

                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            logger.info("The addressee is not set!");
        }
        isRun = false;
    }

    public boolean isRun() {
        return isRun;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    private void send() {

    }

    private void checkTime() {
        for (NotificationEntity entity : entities) {
            byte type = entity.getNotificationType();
            float hours = Math.abs(currentDate.getHours() - entity.getTime().getHours());
            float minute = Math.abs(currentDate.getMinutes() - entity.getTime().getMinutes());

            if (type == NotificationType.PER_HOUR.getValue() || type == NotificationType.ALL_TYPE.getValue()) {
                if (hours > 1 && minute > 20) {
                    entities.remove(entity);
                }
            }

            if (type == NotificationType.PER_DAY.getValue() || type == NotificationType.ALL_TYPE.getValue()) {
                if (!isNextDay(entity)) {
                    entities.remove(entity);
                }
            }

            if (type == NotificationType.PER_30.getValue() || type == NotificationType.ALL_TYPE.getValue()) {
                if (minute > 40) {
                    entities.remove(entity);
                }
            }

            if (type == NotificationType.PER_15.getValue() || type == NotificationType.ALL_TYPE.getValue()) {
                if (minute > 20) {
                    entities.remove(entity);
                }
            }

        }
    }

    private void checkDate() {
        for (NotificationEntity entity : entities) {
            if (isDone(entity)) {
                entity.setNotificationStatus(NotificationStatus.DONE.getValue());
            } else {
                entities.remove(entity);
            }
        }
    }

    private boolean isCurrentDate(NotificationEntity entity) {
        return entity.getDate().getYear() == currentDate.getYear()
                && entity.getDate().getMonth() == currentDate.getMonth()
                && entity.getDate().getDay() == currentDate.getDay();
    }

    private boolean isDone(NotificationEntity entity) {
        return entity.getDate().getYear() - currentDate.getYear() < 0
                || (entity.getDate().getYear() - currentDate.getYear() == 0
                    && entity.getDate().getMonth() - currentDate.getMonth() < 0)
                || (entity.getDate().getYear() - currentDate.getYear() == 0
                    && entity.getDate().getMonth() - currentDate.getMonth() == 0
                    && entity.getDate().getDay() - currentDate.getDay() < 0);
    }

    private boolean isNextDay(NotificationEntity entity) {
        return entity.getDate().getYear() == currentDate.getYear()
                && entity.getDate().getMonth() == currentDate.getMonth()
                && entity.getDate().getDay() - currentDate.getDay() == 1;
    }

}
