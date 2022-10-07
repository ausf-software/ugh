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

import ausf.software.api.store.entity.TimetableElementEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Реализация DAO к таблице, содержащей информацию о пунктах расписания.
 *
 * @author Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
public class TimetableElementDAO extends DAO <TimetableElementEntity, Integer> {

    public TimetableElementDAO() {
    }

    @Override
    public void add(@NotNull TimetableElementEntity entity) {
        currentSession.save(entity);
    }

    @Override
    public void update(@NotNull TimetableElementEntity entity) {
        currentSession.update(entity);
    }

    @Override
    public TimetableElementEntity findById(@NotNull Integer id) {
        return currentSession.get(TimetableElementEntity.class, id);
    }

    @Override
    public void delete(@NotNull TimetableElementEntity entity) {
        currentSession.delete(entity);
    }

    public List<TimetableElementEntity> getTimetableElementByArg(@NotNull String columnName, @NotNull Object value) {
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<TimetableElementEntity> criteria = criteriaBuilder.createQuery(TimetableElementEntity.class);
        Root<TimetableElementEntity> root = criteria.from(TimetableElementEntity.class);
        criteria.select(root).where(criteriaBuilder.equal(root.get(columnName), value));
        List<TimetableElementEntity> disciplines = currentSession.createQuery(criteria).getResultList();
        return disciplines;
    }

}
