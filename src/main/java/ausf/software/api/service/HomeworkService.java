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

import ausf.software.api.store.dao.HomeworkDAO;
import ausf.software.api.store.entity.HomeworkEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Сервис предоставдляющий интерфейс для взаимодействия с данными
 * в таблице, содержащей данные о элементах необходимых для составления
 * таблицы с дз.
 *
 * @see Service
 * @see HomeworkDAO
 * @see HomeworkEntity
 * @author Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
public class HomeworkService implements Service <HomeworkEntity, Integer> {

    private HomeworkDAO dao;

    public HomeworkService() {
        dao = new HomeworkDAO();
    }

    @Override
    public void add(HomeworkEntity entity) {
        dao.openCurrentSessionWithTransaction();
        dao.add(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(HomeworkEntity entity) {
        dao.openCurrentSessionWithTransaction();
        dao.update(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    @Override
    public HomeworkEntity findById(Integer id) {
        dao.openCurrentSession();
        HomeworkEntity entity = dao.findById(id);
        dao.closeCurrentSession();
        return entity;
    }

    @Override
    public void deleteByEntity(HomeworkEntity entity) {
        dao.openCurrentSessionWithTransaction();
        dao.delete(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteByID(Integer id) {
        dao.openCurrentSessionWithTransaction();
        HomeworkEntity entity = dao.findById(id);
        dao.delete(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    /**
     * Создает сессию для получения списока элементов по заданному значению типа
     * недели на которое размещено дз.
     *
     * @param weekType тип недели.
     * @return список подходящих условию выборки по типу недели элементов.
     */
    public List<HomeworkEntity> getHomeworkByWeekType(@NotNull byte weekType) {
        dao.openCurrentSession();
        List<HomeworkEntity> elements = dao.getHomeworkByWeekType(weekType);
        dao.closeCurrentSession();
        return elements;
    }

}
