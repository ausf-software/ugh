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

package ausf.software.api.store.dao;

import ausf.software.api.store.entity.DisciplineEntity;
import org.jetbrains.annotations.NotNull;

/**
 * Реализация DAO к таблице, содержащей информацию о дисциплинах.
 *
 * @author Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
public class DisciplineDAO extends DAO <DisciplineEntity, Integer>{

    public DisciplineDAO() {
    }

    @Override
    public void add(@NotNull DisciplineEntity entity) {
        currentSession.save(entity);
    }

    @Override
    public void update(@NotNull DisciplineEntity entity) {
        currentSession.update(entity);
    }

    @Override
    public DisciplineEntity findById(@NotNull Integer id) {
        return currentSession.get(DisciplineEntity.class, id);
    }

    @Override
    public void delete(@NotNull DisciplineEntity entity) {
        currentSession.delete(entity);
    }

}
