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

import ausf.software.api.store.HomeworkType;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

/**
 * Описывает элемент из таблицы, содержащей информацию о дз.
 *
 * @see TimetableElementEntity
 * @see ausf.software.api.store.WeekType
 * @see HomeworkType
 * @author Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
@Entity
@Table(name = "homework")
public class HomeworkEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @NotNull
    @ManyToOne
    @JoinColumn (name="timetable_element_id")
    private TimetableElementEntity timetableElement;

    @Column (name = "homework")
    @NotNull
    private String homework;

    @Column (name = "homework_type")
    @NotNull
    private byte homeworkType;

    @Column (name = "week_type")
    @NotNull
    private byte weekType;

    public HomeworkEntity(@NotNull TimetableElementEntity timetableElement,
                          @NotNull String homework, @NotNull byte homeworkType, @NotNull byte weekType) {
        this.timetableElement = timetableElement;
        this.homework = homework;
        this.homeworkType = homeworkType;
        this.weekType = weekType;
    }

    public HomeworkEntity() {
    }

    public int getId() {
        return id;
    }

    public TimetableElementEntity getTimetableElement() {
        return timetableElement;
    }

    public void setTimetableElement(@NotNull TimetableElementEntity timetableElement) {
        this.timetableElement = timetableElement;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(@NotNull String homework) {
        this.homework = homework;
    }

    public byte getHomeworkType() {
        return homeworkType;
    }

    public void setHomeworkType(@NotNull byte homeworkType) {
        this.homeworkType = homeworkType;
    }

    public byte getWeekType() {
        return weekType;
    }

    public void setWeekType(@NotNull byte weekType) {
        this.weekType = weekType;
    }
}
