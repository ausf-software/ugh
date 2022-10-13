package ru.BouH.events;

import ausf.software.api.service.TimetableService;
import ausf.software.api.store.DayValue;
import ausf.software.api.store.WeekType;
import ausf.software.api.store.entity.TimetableElementEntity;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class Commands {
    private final TimetableService timetableService = new TimetableService();

    @EventCommand(command = "расп", usage = "[<пробел>/пн/вт/ср/чт/пт/сб/вс] [ч/з]")
    public boolean getTimeTable(String[] args, MessageReceivedEvent event) {
        if (args.length > 0) {
            if (args.length == 2) {
                WeekType weekType = args[1].equals("ч") ? WeekType.NUMERATOR : args[1].equals("з") ? WeekType.DENOMINATOR : null;
                if (weekType != null) {
                    switch (args[0]) {
                        case "пн" -> {
                            this.buildSchedule(event.getChannel(), DayValue.MONDAY, weekType);
                        }
                        case "вт" -> {
                            this.buildSchedule(event.getChannel(), DayValue.TUESDAY, weekType);
                        }
                        case "ср" -> {
                            this.buildSchedule(event.getChannel(), DayValue.WEDNESDAY, weekType);
                        }
                        case "чт" -> {
                            this.buildSchedule(event.getChannel(), DayValue.THURSDAY, weekType);
                        }
                        case "пт" -> {
                            this.buildSchedule(event.getChannel(), DayValue.FRIDAY, weekType);
                        }
                        case "сб" -> {
                            this.buildSchedule(event.getChannel(), DayValue.SATURDAY, weekType);
                        }
                        case "вс" -> {
                            this.buildSchedule(event.getChannel(), DayValue.SUNDAY, weekType);
                        }
                        default -> {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            } else if (args.length == 1) {
                WeekType weekType = args[0].equals("ч") ? WeekType.NUMERATOR : args[0].equals("з") ? WeekType.DENOMINATOR : null;
                if (weekType != null) {
                    this.buildSchedule(event.getChannel(), DayValue.ALL_DAYS, weekType);
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    private void buildSchedule(MessageChannelUnion messageChannelUnion, DayValue dayValue, WeekType weekType) {
        StringBuilder stringBuilder = new StringBuilder();
        List<TimetableElementEntity> timetableElementEntityList;
        if (dayValue == DayValue.ALL_DAYS) {
            timetableElementEntityList = this.timetableService.getTimetableElementsByWeekType(weekType.getValue());
        } else {
            timetableElementEntityList = this.timetableService.getTimetableElementsByDay(dayValue.getValue());
        }
        for (TimetableElementEntity timetableElementEntity : timetableElementEntityList) {
            stringBuilder.append(DayValue.values()[timetableElementEntity.getDay()].getTitle()).append(" : ");
            stringBuilder.append(timetableElementEntity.getWeekType() == 0 ? "Числитель" : "Знаменатель").append("\n");
            stringBuilder.append("..............\n");
            stringBuilder.append(timetableElementEntity.getDiscipline().getName()).append(" | ").append(timetableElementEntity.getDiscipline().getLecturer()).append("\n");
        }
        messageChannelUnion.sendMessage(stringBuilder.toString()).submit();
    }
}
