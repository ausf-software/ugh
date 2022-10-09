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

import ausf.software.api.store.entity.BookLibraryElementEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Реализация DAO к таблице, содержащей информацию о рекомендуемых
 * одногрупниками книгах.
 *
 * @see DAO
 * @see BookLibraryElementEntity
 * @author Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
public class BookLibraryElementDAO extends DAO <BookLibraryElementEntity, Integer> {

    @Override
    public void add(@NotNull BookLibraryElementEntity entity) {
        currentSession.save(entity);
    }

    @Override
    public void update(@NotNull BookLibraryElementEntity entity) {
        currentSession.update(entity);
    }

    @Override
    public BookLibraryElementEntity findById(@NotNull Integer id) {
        return currentSession.get(BookLibraryElementEntity.class, id);
    }

    @Override
    public void delete(@NotNull BookLibraryElementEntity entity) {
        currentSession.delete(entity);
    }

    /**
     * Создает запрос к БД и возвращает список элементов по заданному значению категории книг.
     *
     * @param category категория книг по которой будет выборка.
     * @return список элементов соответствующих заданному условию.
     */
    public List<BookLibraryElementEntity> getBookLibraryElementByCategory(@NotNull byte category) {
        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        CriteriaQuery<BookLibraryElementEntity> criteria = criteriaBuilder.createQuery(BookLibraryElementEntity.class);
        Root<BookLibraryElementEntity> root = criteria.from(BookLibraryElementEntity.class);
        criteria.select(root).where(criteriaBuilder.equal(root.get("category"), category));
        return currentSession.createQuery(criteria).getResultList();
    }

}
