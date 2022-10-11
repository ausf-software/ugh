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

package ausf.software.api.store.dao;

import ausf.software.api.store.entity.NotificationEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Реализация DAO к таблице, содержащей информацию о уведомлениях.
 *
 * @see DAO
 * @see ausf.software.api.store.entity.NotificationEntity
 * @author Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
public class NotificationDAO extends DAO <NotificationEntity, Integer> {

    @Override
    public void add(@NotNull NotificationEntity entity) {
        currentSession.save(entity);
    }

    @Override
    public void update(@NotNull NotificationEntity entity) {
        currentSession.update(entity);
    }

    @Override
    public NotificationEntity findById(@NotNull Integer id) {
        return currentSession.get(NotificationEntity.class, id);
    }

    @Override
    public void delete(@NotNull NotificationEntity entity) {
        currentSession.delete(entity);
    }

    /**
     * Создает запрос к БД и возвращает список элементов по заданному значению статуса уведомлений.
     *
     * @param notificationStatus статус уведомления.
     * @return список элементов соответствующих заданному условию.
     */
    public List<NotificationEntity> getNotificationByStatus(@NotNull byte notificationStatus) {
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<NotificationEntity> criteria = criteriaBuilder.createQuery(NotificationEntity.class);
        Root<NotificationEntity> root = criteria.from(NotificationEntity.class);
        criteria.select(root).where(criteriaBuilder.equal(root.get("notification_status"), notificationStatus));
        return currentSession.createQuery(criteria).getResultList();
    }

}
