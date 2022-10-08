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

@Entity
@Table(name = "homework")
public class HomeworkEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @Column (name = "timetable_element_id")
    @NotNull
    private int timetableElementId;

    @Column (name = "homework")
    @NotNull
    private String homework;

    @Column (name = "homework_type")
    @NotNull
    private byte homeworkType;

    public HomeworkEntity(@NotNull int timetableElementId, @NotNull String homework, @NotNull byte homeworkType) {
        this.timetableElementId = timetableElementId;
        this.homework = homework;
        this.homeworkType = homeworkType;
    }

    public HomeworkEntity() {
    }

    public int getId() {
        return id;
    }

    public int getTimetableElementId() {
        return timetableElementId;
    }

    public void setTimetableElementId(@NotNull int timetableElementId) {
        this.timetableElementId = timetableElementId;
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

}
