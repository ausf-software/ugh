/*
 * Copyright © 2022 Shcherbina Daniil, ***
 *
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

import ausf.software.api.store.dao.TimetableElementDAO;
import ausf.software.api.store.entity.TimetableElementEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Сервис предоставдляющий интерфейс для взаимодействия с данными
 * в таблице, содержащей данные о элементах необходимых для составления
 * таблицы расписания.
 *
 * @see Service
 * @see TimetableElementDAO
 * @see TimetableElementEntity
 * @author Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
public class TimetableService implements Service <TimetableElementEntity, Integer> {

    private TimetableElementDAO dao;

    public TimetableService() {
        dao = new TimetableElementDAO();
    }

    @Override
    public void add(TimetableElementEntity entity) {
        dao.openCurrentSessionWithTransaction();
        dao.add(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(TimetableElementEntity entity) {
        dao.openCurrentSessionWithTransaction();
        dao.update(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    @Override
    public TimetableElementEntity findById(Integer id) {
        dao.openCurrentSession();
        TimetableElementEntity entity = dao.findById(id);
        dao.closeCurrentSession();
        return entity;
    }

    @Override
    public void deleteByEntity(TimetableElementEntity entity) {
        dao.openCurrentSessionWithTransaction();
        dao.delete(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteByID(Integer id) {
        dao.openCurrentSessionWithTransaction();
        TimetableElementEntity entity = dao.findById(id);
        dao.delete(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    /**
     * Создает сессию для получения всех записей имеющих совпадение
     * по заданному значению в заданном столбце, после чего возвращает
     * полученные данные.
     *
     * @param columnName имя столбца в БД.
     * @param value значение на совпадение которого будет проверка.
     * @return список объектов удовлетворяющих данному условию.
     */
    private List<TimetableElementEntity> getTimetableElementsByArg(@NotNull String columnName, @NotNull Object value) {
        dao.openCurrentSession();
        List<TimetableElementEntity> disciplines = dao.getTimetableElementByArg(columnName, value);
        dao.closeCurrentSession();
        return disciplines;
    }

    /**
     * Создает сессию для получения всех записей имеющих совпадение
     * по заданному значению в стобце с типом недели, после чего возвращает
     * полученные данные.
     *
     * @param value значение на совпадение которого будет проверка.
     * @return список объектов удовлетворяющих данному условию.
     */
    public List<TimetableElementEntity> getTimetableElementsByWeekType(@NotNull int value) {
        List<TimetableElementEntity> disciplines = getTimetableElementsByArg("week_type", value);
        return disciplines;
    }

    /**
     * Создает сессию для получения всех записей имеющих совпадение
     * по заданному значению в стобце с днем недели, после чего возвращает
     * полученные данные.
     *
     * @param value значение на совпадение которого будет проверка.
     * @return список объектов удовлетворяющих данному условию.
     */
    public List<TimetableElementEntity> getTimetableElementsByDay(@NotNull int value) {
        List<TimetableElementEntity> disciplines = getTimetableElementsByArg("day", value);
        return disciplines;
    }

    /**
     * Создает сессию для получения всех записей.
     *
     * @return список всех элементов из БД.
     */
    public List<TimetableElementEntity> getAllElement() {
        dao.openCurrentSession();
        List<TimetableElementEntity> disciplines = dao.getAllElement();
        dao.closeCurrentSession();
        return disciplines;
    }

}
