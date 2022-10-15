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

package ausf.software.api.service;

import ausf.software.api.store.dao.NotificationDAO;
import ausf.software.api.store.entity.NotificationEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Сервис предоставдляющий интерфейс для взаимодействия с данными
 * в таблице, содержащей данные о элементах уведомлений.
 *
 * @see Service
 * @see ausf.software.api.store.dao.NotificationDAO
 * @see ausf.software.api.store.entity.NotificationEntity
 * @author Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
public class NotificationService implements Service <NotificationEntity, Integer> {

    private NotificationDAO dao;

    public NotificationService() {
        dao = new NotificationDAO();
    }

    @Override
    public void add(NotificationEntity entity) {
        dao.openCurrentSessionWithTransaction();
        dao.add(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(NotificationEntity entity) {
        dao.openCurrentSessionWithTransaction();
        dao.update(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    @Override
    public NotificationEntity findById(Integer id) {
        dao.openCurrentSession();
        NotificationEntity entity = dao.findById(id);
        dao.closeCurrentSession();
        return entity;
    }

    @Override
    public void deleteByEntity(NotificationEntity entity) {
        dao.openCurrentSessionWithTransaction();
        dao.delete(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteByID(Integer id) {
        dao.openCurrentSessionWithTransaction();
        NotificationEntity entity = dao.findById(id);
        dao.delete(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    /**
     * Создает сессию для получения списока элементов по заданному значению статуса уведомлений.
     *
     * @param notificationStatus статус уведомлений.
     * @return список подходящих условию выборки по категории элементов.
     */
    public List<NotificationEntity> getNotificationByStatus(@NotNull byte notificationStatus) {
        dao.openCurrentSession();
        List<NotificationEntity> elements = dao.getNotificationByStatus(notificationStatus);
        dao.closeCurrentSession();
        return elements;
    }

}
