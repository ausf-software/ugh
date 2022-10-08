/*
 * Copyright © 2022 Shcherbina Daniil
 * License: http://opensource.org/licenses/MIT
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
