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
 * категориям игр.
 *
 * @author Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
public enum GameCategory {

    ACTION              ((byte) 0, "Action"),
    ACTION_ADVENTURE    ((byte) 1, "Action-adventure"),
    ADVENTURE           ((byte) 2, "Adventure"),
    FIGHTING            ((byte) 3, "Fighting"),
    PUZZLE              ((byte) 4, "Puzzle"),
    PLATFORM            ((byte) 5, "Platform"),
    RACING              ((byte) 6, "Racing"),
    RPG                 ((byte) 7, "RPG"),
    SPORTS              ((byte) 8, "Sports"),
    STRATEGY            ((byte) 9, "Strategy"),
    SURViVAL_HORROR     ((byte) 10, "Survival horror"),
    SANDBOX             ((byte) 11, "Sandbox"),
    SIMULATOR           ((byte) 12, "Simulator");

    private byte index;
    private String title;

    GameCategory(byte i, String title) {
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
