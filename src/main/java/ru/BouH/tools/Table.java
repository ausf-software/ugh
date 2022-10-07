package ru.BouH.tools;

import ausf.software.api.store.DayValue;
import ausf.software.api.store.WeekType;

public class Table {
    public static String defaultSchedule(DayValue dayValue, WeekType weekType) {
        StringBuilder stringBuilder = new StringBuilder();
        if (dayValue != null) {
            stringBuilder.append("День недели");
            stringBuilder.append("Числитель");
            stringBuilder.append("..............");
            stringBuilder.append("Время : Предмет : Айдитория : Препод");
        } else {
            stringBuilder.append("Все дни");
        }
        return stringBuilder.toString();
    }
}
