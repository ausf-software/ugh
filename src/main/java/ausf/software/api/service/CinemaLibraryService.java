/*
 * Copyright © 2022 Shcherbina Daniil, ***
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

import ausf.software.api.store.dao.CinemaLibraryElementDAO;
import ausf.software.api.store.entity.CinemaLibraryElementEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Сервис предоставдляющий интерфейс для взаимодействия с данными
 * в таблице, содержащей данные о рекомендуемых одногрупниками фильмах.
 *
 * @see Service
 * @see CinemaLibraryElementDAO
 * @see CinemaLibraryElementEntity
 * @author Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
public class CinemaLibraryService implements Service <CinemaLibraryElementEntity, Integer> {

    private CinemaLibraryElementDAO dao;

    public CinemaLibraryService() {
        dao = new CinemaLibraryElementDAO();
    }

    @Override
    public void add(CinemaLibraryElementEntity entity) {
        dao.openCurrentSessionWithTransaction();
        dao.add(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(CinemaLibraryElementEntity entity) {
        dao.openCurrentSessionWithTransaction();
        dao.update(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    @Override
    public CinemaLibraryElementEntity findById(Integer id) {
        dao.openCurrentSession();
        CinemaLibraryElementEntity entity = dao.findById(id);
        dao.closeCurrentSession();
        return entity;
    }

    @Override
    public void deleteByEntity(CinemaLibraryElementEntity entity) {
        dao.openCurrentSessionWithTransaction();
        dao.delete(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteByID(Integer id) {
        dao.openCurrentSessionWithTransaction();
        CinemaLibraryElementEntity entity = dao.findById(id);
        dao.delete(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    /**
     * Создает сессию для получения списока элементов по заданному значению категории фильмов.
     *
     * @param category категория фильма.
     * @return список подходящих условию выборки по категории элементов.
     */
    public List<CinemaLibraryElementEntity> getCinemaLibraryElementByCategory(@NotNull byte category) {
        dao.openCurrentSession();
        List<CinemaLibraryElementEntity> elements = dao.getCinemaLibraryElementByCategory(category);
        dao.closeCurrentSession();
        return elements;
    }

}
