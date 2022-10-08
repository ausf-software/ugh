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

import ausf.software.api.store.dao.GameLibraryElementDAO;
import ausf.software.api.store.entity.GameLibraryElementEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Сервис предоставдляющий интерфейс для взаимодействия с данными
 * в таблице, содержащей данные о рекомендуемых одногрупниками играх.
 *
 * @see Service
 * @see GameLibraryElementDAO
 * @see GameLibraryElementEntity
 * @author Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
public class GameLibraryService implements Service <GameLibraryElementEntity, Integer> {

    private GameLibraryElementDAO dao;

    public GameLibraryService() {
        dao = new GameLibraryElementDAO();
    }

    @Override
    public void add(GameLibraryElementEntity entity) {
        dao.openCurrentSessionWithTransaction();
        dao.add(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(GameLibraryElementEntity entity) {
        dao.openCurrentSessionWithTransaction();
        dao.update(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    @Override
    public GameLibraryElementEntity findById(Integer id) {
        dao.openCurrentSession();
        GameLibraryElementEntity entity = dao.findById(id);
        dao.closeCurrentSession();
        return entity;
    }

    @Override
    public void deleteByEntity(GameLibraryElementEntity entity) {
        dao.openCurrentSessionWithTransaction();
        dao.delete(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteByID(Integer id) {
        dao.openCurrentSessionWithTransaction();
        GameLibraryElementEntity entity = dao.findById(id);
        dao.delete(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    /**
     * Создает сессию для получения списока элементов по заданному значению категории игр.
     *
     * @param category категория игр.
     * @return список подходящих условию выборки по категории элементов.
     */
    public List<GameLibraryElementEntity> getGameLibraryElementByCategory(@NotNull byte category) {
        dao.openCurrentSession();
        List<GameLibraryElementEntity> elements = dao.getGameLibraryElementByCategory(category);
        dao.closeCurrentSession();
        return elements;
    }

}
