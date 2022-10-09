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

package ausf.software.api.store.entity;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

/**
 * Описывает элемент из таблицы, содержащей информацию о изучаемых дисциплинах.
 *
 * @see ausf.software.api.store.DisciplineType
 * @author Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
@Entity
@Table(name = "discipline")
public class DisciplineEntity {

    @Column (name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @Column (name = "name")
    @NotNull
    private String name;

    @Column (name = "lecturer")
    @NotNull
    private String lecturer;

    @Column (name = "type")
    @NotNull
    private byte type;

    public DisciplineEntity(@NotNull String name, @NotNull String lecturer, @NotNull byte type) {
        this.name = name;
        this.lecturer = lecturer;
        this.type = type;
    }

    public DisciplineEntity() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(@NotNull String lecturer) {
        this.lecturer = lecturer;
    }

    public byte getType() {
        return type;
    }

    public void setType(@NotNull byte type) {
        this.type = type;
    }

}
