package ausf.software.api.store;

/**
 * Содержит значения, которые будут храниться в БД, соответствующие
 * категориям книг.
 *
 * @author Shcherbina Daniil
 * @since 1.0
 * @version 1.0
 */
public enum BookCategory {

    TEXT_BOOK               ((byte) 0, "Учебник"),
    SPECIALIZED_LITERATURE  ((byte) 1, "Специализированная литература"),
    ARTISTIC_LITERATURE     ((byte) 2, "Художественная литература");

    private byte index;
    private String title;

    BookCategory(byte i, String title) {
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
