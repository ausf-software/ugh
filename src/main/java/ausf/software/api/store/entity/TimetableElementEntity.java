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

package ausf.software.api.store.entity;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

/**
 * Описывает элемент из таблицы, содержащей информацию о элементах расписания.
 *
 * @see DisciplineEntity
 * @see ausf.software.api.store.WeekType
 * @see ausf.software.api.store.DayValue
 * @author Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
@Entity
@Table(name = "timetable_element")
public class TimetableElementEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @Column (name = "day")
    @NotNull
    private byte day;

    @Column (name = "week_type")
    @NotNull
    private byte weekType;

    @Column (name = "number")
    @NotNull
    private byte number;

    @NotNull
    @ManyToOne
    @JoinColumn (name="discipline_id")
    private DisciplineEntity discipline;

    public TimetableElementEntity(@NotNull byte day, @NotNull byte weekType,
                                  @NotNull byte number, @NotNull DisciplineEntity discipline) {
        this.day = day;
        this.weekType = weekType;
        this.number = number;
        this.discipline = discipline;
    }

    public TimetableElementEntity() {
    }

    public int getId() {
        return id;
    }

    public byte getDay() {
        return day;
    }

    public void setDay(@NotNull byte day) {
        this.day = day;
    }

    public byte getWeekType() {
        return weekType;
    }

    public void setWeekType(@NotNull byte weekType) {
        this.weekType = weekType;
    }

    public byte getNumber() {
        return number;
    }

    public void setNumber(@NotNull byte number) {
        this.number = number;
    }

    public DisciplineEntity getDiscipline() {
        return discipline;
    }

    public void setDiscipline(@NotNull DisciplineEntity discipline) {
        this.discipline = discipline;
    }

}
