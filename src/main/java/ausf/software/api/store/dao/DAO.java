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

import ausf.software.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * Абстрактное описание свойств объектов доступа к БД.
 *
 * @see HibernateUtil
 * @author Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
abstract class DAO <T, Id extends Serializable>{

    protected Session currentSession;
    protected Transaction currentTransaction;

    /**
     * Создает запрос к БД на добавление записи о элементе.
     */
    public abstract void add(@NotNull T entity);

    /**
     * Создает запрос к БД на обновление записи о элементе.
     */
    public abstract void update(@NotNull T entity);

    /**
     * Создает запрос к БД на поиск записи элемента по его ID.
     */
    public abstract T findById(@NotNull Id id);

    /**
     * Создает запрос к БД на удаление записи о элементе.
     */
    public abstract void delete(@NotNull T entity);

    /**
     * Открывает сессию для работы с БД.
     */
    public void openCurrentSession() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
    }

    /**
     * Открывает сессию для работы с БД и начинает транзакцию.
     */
    public void openCurrentSessionWithTransaction() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
    }

    /**
     * Закрывает сессию работы с БД.
     */
    public void closeCurrentSession() {
        currentSession.close();
    }

    /**
     * Подтверждает транзакцию и закрывает сессию работы с БД.
     */
    public void closeCurrentSessionWithTransaction(){
        currentTransaction.commit();
        closeCurrentSession();
    }

    /**
     * Возвращает объект текущей сессии.
     *
     * @return объект текущей сессии.
     */
    public Session getCurrentSession() {
        return currentSession;
    }

    /**
     * Возвращает объект текущей транзакции.
     *
     * @return объект текущей транзакции.
     */
    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

}
