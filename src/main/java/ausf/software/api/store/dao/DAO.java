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

import ausf.software.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * Абстрактное описание свойств объектов доступа к БД.
 *
 * @author Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
public abstract class DAO <T, Id extends Serializable>{

    protected Session currentSession;
    protected Transaction currentTransaction;

    public abstract void add(@NotNull T entity);

    public abstract void update(@NotNull T entity);

    public abstract T findById(@NotNull Id id);

    public abstract void delete(@NotNull T entity);

    public void openCurrentSession() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
    }

    public void openCurrentSessionWithTransaction() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionWithTransaction(){
        currentTransaction.commit();
        closeCurrentSession();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

}
