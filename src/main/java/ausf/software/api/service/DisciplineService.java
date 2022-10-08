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

import ausf.software.api.store.dao.DisciplineDAO;
import ausf.software.api.store.entity.DisciplineEntity;
import org.jetbrains.annotations.NotNull;

/**
 * Сервис предоставдляющий интерфейс для взаимодействия с данными
 * в таблице, содержащей данные о дисциплинах.
 *
 * @see Service
 * @see DisciplineDAO
 * @see DisciplineEntity
 * @author Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
public class DisciplineService implements Service <DisciplineEntity, Integer> {

    private DisciplineDAO dao;

    public DisciplineService() {
        dao = new DisciplineDAO();
    }

    @Override
    public DisciplineEntity findById(@NotNull Integer id) {
        dao.openCurrentSession();
        DisciplineEntity entity = dao.findById(id);
        dao.closeCurrentSession();
        return entity;
    }

    @Override
    public void add(@NotNull DisciplineEntity entity) {
        dao.openCurrentSessionWithTransaction();
        dao.add(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(@NotNull DisciplineEntity entity) {
        dao.openCurrentSessionWithTransaction();
        dao.update(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteByEntity(@NotNull DisciplineEntity entity) {
        dao.openCurrentSessionWithTransaction();
        dao.delete(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteByID(@NotNull Integer id) {
        dao.openCurrentSessionWithTransaction();
        DisciplineEntity entity = dao.findById(id);
        dao.delete(entity);
        dao.closeCurrentSessionWithTransaction();
    }

}
