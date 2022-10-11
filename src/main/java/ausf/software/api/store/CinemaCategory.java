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
 * категориям фильмов.
 *
 * @author Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
public enum CinemaCategory {

    ANIME           ((byte) 0, "Аниме"),
    BIOGRAPHICAL    ((byte) 1, "Биографический"),
    ACTION          ((byte) 2, "Боевик"),
    WESTERN         ((byte) 3, "Вестерн"),
    DETECTIVE       ((byte) 4, "Детектив"),
    DOCUMENTARY     ((byte) 5, "Документальный"),
    DRAMA           ((byte) 6, "Драма"),
    HISTORICAL      ((byte) 7, "Исторический"),
    COMEDY          ((byte) 8, "Комедия"),
    CRIME           ((byte) 9, "Криминал"),
    ROMANCE         ((byte) 10, "Мелодрама"),
    MYSTICISM       ((byte) 11, "Мистика"),
    CARTOON         ((byte) 12, "Мультфильм"),
    SCIENCE         ((byte) 13, "Научный"),
    NOIR            ((byte) 14, "Нуар"),
    ADVENTURE       ((byte) 15, "Приключения"),
    THRILLER        ((byte) 16, "Триллер"),
    HORROR          ((byte) 17, "Ужасы"),
    FANTASTIC       ((byte) 18, "Фантастика"),
    FANTASY         ((byte) 19, "Фэнтези");

    private byte index;
    private String title;

    CinemaCategory(byte i, String title) {
        index = i;
        this.title = title;
    }

    public byte getValue() {
        return index;
    }

    public String getTitle() {
        return title;
    }

}
