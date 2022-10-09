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

/**
 * Интерфейс описывающий ряд общих интерфейсов взаимодействия с сервисами.
 *
 * @author Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
public interface Service <T, K> {

    /**
     * Создает сессию для добавления записи о новом элементе.
     */
    void add(T entity);

    /**
     * Создает сессию для обновдения в БД записи о элементе.
     */
    void update(T entity);

    /**
     * Создает сессию для получения записи элемента из БД по его ID.
     */
    T findById(K id);

    /**
     * Создает сессию для удаления записи элемента из БД.
     */
    void deleteByEntity(T entity);

    /**
     * Создает сессию для удаления записи элемента из БД по его ID.
     */
    void deleteByID(K id);

}
