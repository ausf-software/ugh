/*
 * Copyright © 2022 Shcherbina Daniil
 * License: http://opensource.org/licenses/MIT
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

    HOMEWORK            ((byte) 0), // обычное дз
    TEST                ((byte) 1), // подготовка к контрольной работе
    INDIVIDUAL_WORK     ((byte) 2), // подготовка к самостоятельной работе
    PRESENTATION        ((byte) 3), // доклад\презентация
    SEMINAR             ((byte) 4); // подготовка к семинару

    private byte index;

    HomeworkType(byte i) {
        index = i;
    }

    public byte getValue() {
        return index;
    }
}
