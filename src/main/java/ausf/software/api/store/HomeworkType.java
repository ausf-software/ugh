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

package ausf.software.api.store;

/**
 * Содержит значения, которые будут храниться в БД, соответствующие
 * типам дз.
 *
 * @author Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
public enum HomeworkType {

    HOMEWORK            ((byte) 0, "Обычное дз"),
    TEST                ((byte) 1, "Подготовка к кр"),
    INDIVIDUAL_WORK     ((byte) 2, "Подготовка к см"),
    PRESENTATION        ((byte) 3, "Доклад/презентация"),
    SEMINAR             ((byte) 4, "Подготовка к семинару");

    private byte index;
    private String title;

    HomeworkType(byte i, String text) {
        index = i;
        title = text;
    }

    public byte getValue() {
        return index;
    }

    public String getTitle() {
        return title;
    }
}
