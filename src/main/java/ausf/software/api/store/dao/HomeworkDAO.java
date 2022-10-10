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

import ausf.software.api.store.entity.HomeworkEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Реализация DAO к таблице, содержащей информацию о дз.
 *
 * @see DAO
 * @see HomeworkEntity
 * @author Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
public class HomeworkDAO extends DAO <HomeworkEntity, Integer> {

    @Override
    public void add(@NotNull HomeworkEntity entity) {
        currentSession.save(entity);
    }

    @Override
    public void update(@NotNull HomeworkEntity entity) {
        currentSession.update(entity);
    }

    @Override
    public HomeworkEntity findById(@NotNull Integer id) {
        return currentSession.get(HomeworkEntity.class, id);
    }

    @Override
    public void delete(@NotNull HomeworkEntity entity) {
        currentSession.delete(entity);
    }

    /**
     * Создает запрос к БД и возвращает список элементов по заданному значению типа недели.
     *
     * @param weekType тип недели по которой будет выборка.
     * @return список элементов соответствующих заданному условию.
     */
    public List<HomeworkEntity> getHomeworkByWeekType(@NotNull byte weekType) {
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<HomeworkEntity> criteria = criteriaBuilder.createQuery(HomeworkEntity.class);
        Root<HomeworkEntity> root = criteria.from(HomeworkEntity.class);
        criteria.select(root).where(criteriaBuilder.equal(root.get("week_type"), weekType));
        return currentSession.createQuery(criteria).getResultList();
    }

}
