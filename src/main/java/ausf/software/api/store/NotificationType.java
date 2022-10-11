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
 * типам уведомлений.
 *
 * @author Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
public enum NotificationType {

    PER_DAY     ((byte) 0, "За день"),
    PER_HOUR    ((byte) 1, "За час"),
    PER_30      ((byte) 2, "За 30 минут"),
    PER_15      ((byte) 3, "За 15 минут"),
    ALL_TYPE    ((byte) -1, "Все вышепредставленные");

    private byte index;
    private String title;

    NotificationType(byte i, String text) {
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
