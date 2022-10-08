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

import ausf.software.api.store.entity.CinemaLibraryElementEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Реализация DAO к таблице, содержащей информацию о рекомендуемых
 * одногрупниками фильмах.
 *
 * @see DAO
 * @see CinemaLibraryElementEntity
 * @author Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
public class CinemaLibraryElementDAO extends DAO <CinemaLibraryElementEntity, Integer> {

    @Override
    public void add(@NotNull CinemaLibraryElementEntity entity) {
        currentSession.save(entity);
    }

    @Override
    public void update(@NotNull CinemaLibraryElementEntity entity) {
        currentSession.update(entity);
    }

    @Override
    public CinemaLibraryElementEntity findById(@NotNull Integer id) {
        return currentSession.get(CinemaLibraryElementEntity.class, id);
    }

    @Override
    public void delete(@NotNull CinemaLibraryElementEntity entity) {
        currentSession.delete(entity);
    }

    /**
     * Создает запрос к БД и возвращает список элементов по заданному значению категории фильмов.
     *
     * @param category категория фильмов по которой будет выборка.
     * @return список элементов соответствующих заданному условию.
     */
    public List<CinemaLibraryElementEntity> getCinemaLibraryElementByCategory(@NotNull byte category) {
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<CinemaLibraryElementEntity> criteria = criteriaBuilder.createQuery(CinemaLibraryElementEntity.class);
        Root<CinemaLibraryElementEntity> root = criteria.from(CinemaLibraryElementEntity.class);
        criteria.select(root).where(criteriaBuilder.equal(root.get("category"), category));
        return currentSession.createQuery(criteria).getResultList();
    }

}
