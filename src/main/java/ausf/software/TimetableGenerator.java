/*
 * Copyright © 2022 Shcherbina Daniil
 * License: http://opensource.org/licenses/MIT
 */

package ausf.software;

public class TimetableGenerator {

    private String mondayHeader =       ";------------------------------------------------------------;\n" +
                                        ";                         Понедельник                        ;\n" +
                                        ";-----------;----------------------;---------;---------------;\n" +
                                        ";   Время   ;      Дисциплина      ; Кабинет ; Преподаватель ;\n" +
                                        ";-----------;----------------------;---------;---------------;\n";

    private String tuesdayHeader =      ";------------------------------------------------------------;\n" +
                                        ";                           Вторник                          ;\n" +
                                        ";-----------;----------------------;---------;---------------;\n" +
                                        ";   Время   ;      Дисциплина      ; Кабинет ; Преподаватель ;\n" +
                                        ";-----------;----------------------;---------;---------------;\n";

    private String wednesdayHeader =    ";------------------------------------------------------------;\n" +
                                        ";                            Среда                           ;\n" +
                                        ";-----------;----------------------;---------;---------------;\n" +
                                        ";   Время   ;      Дисциплина      ; Кабинет ; Преподаватель ;\n" +
                                        ";-----------;----------------------;---------;---------------;\n";

    private String thursdayHeader =     ";------------------------------------------------------------;\n" +
                                        ";                           Четверг                          ;\n" +
                                        ";-----------;----------------------;---------;---------------;\n" +
                                        ";   Время   ;      Дисциплина      ; Кабинет ; Преподаватель ;\n" +
                                        ";-----------;----------------------;---------;---------------;\n";

    private String fridayHeader =       ";------------------------------------------------------------;\n" +
                                        ";                           Пятница                          ;\n" +
                                        ";-----------;----------------------;---------;---------------;\n" +
                                        ";   Время   ;      Дисциплина      ; Кабинет ; Преподаватель ;\n" +
                                        ";-----------;----------------------;---------;---------------;\n";

    private String saturdayHeader =     ";------------------------------------------------------------;\n" +
                                        ";                           Суббота                          ;\n" +
                                        ";-----------;----------------------;---------;---------------;\n" +
                                        ";   Время   ;      Дисциплина      ; Кабинет ; Преподаватель ;\n" +
                                        ";-----------;----------------------;---------;---------------;\n";

    private TimetableGenerator() {
    }

    private static class SingletonHolder {
        public static final TimetableGenerator instance = new TimetableGenerator();
    }

    public static TimetableGenerator getInstance() {
        return SingletonHolder.instance;
    }

    public String getTimetable() {
        return getMonday() + "\n"
                + getSaturday() + "\n"
                + getWednesday() + "\n"
                + getThursday() + "\n"
                + getFriday() + "\n"
                + getSaturday() + "\n";
    }

    public String getMonday() {
        return mondayHeader;
    }

    public String getTuesday() {
        return tuesdayHeader;
    }

    public String getWednesday() {
        return wednesdayHeader;
    }

    public String getThursday() {
        return thursdayHeader;
    }

    public String getFriday() {
        return fridayHeader;
    }

    public String getSaturday() {
        return saturdayHeader;
    }

}
