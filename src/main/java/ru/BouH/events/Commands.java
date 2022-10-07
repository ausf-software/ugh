package ru.BouH.events;

import ausf.software.api.store.DayValue;
import ausf.software.api.store.WeekType;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import ru.BouH.tools.Table;

public class Commands {
    //Желательно в DayValue запихнуть еще вариант ALL

    //Наброски
    @EventCommand(command = "расписание", usage = "[<пробел>/пн/вт/ср/чт/пт/сб/вс] [ч/з]")
    public boolean getTimeTable(String[] args, MessageReceivedEvent event) {
        if (event.isFromType(ChannelType.PRIVATE)) {
            if (args.length > 0) {
                if (args.length == 2) {
                    WeekType weekType = args[1].equals("з") ? WeekType.NUMERATOR : args[1].equals("ч") ? WeekType.DENOMINATOR : null;
                    if (weekType != null) {
                        switch (args[0]) {
                            case "пн" -> {
                                this.buildSchedule(event.getChannel(), DayValue.MONDAY, weekType, true);
                            }
                            case "вт" -> {
                                this.buildSchedule(event.getChannel(), DayValue.TUESDAY, weekType, true);
                            }
                            case "ср" -> {
                                this.buildSchedule(event.getChannel(), DayValue.WEDNESDAY, weekType, true);
                            }
                            case "чт" -> {
                                this.buildSchedule(event.getChannel(), DayValue.THURSDAY, weekType, true);
                            }
                            case "пт" -> {
                                this.buildSchedule(event.getChannel(), DayValue.FRIDAY, weekType, true);
                            }
                            case "сб" -> {
                                this.buildSchedule(event.getChannel(), DayValue.SATURDAY, weekType, true);
                            }
                            case "вс" -> {
                                this.buildSchedule(event.getChannel(), DayValue.SUNDAY, weekType, true);
                            }
                            default -> {
                                return false;
                            }
                        }
                    } else {
                        return false;
                    }
                } else if (args.length == 1) {
                    WeekType weekType = args[0].equals("з") ? WeekType.NUMERATOR : args[0].equals("ч") ? WeekType.DENOMINATOR : null;
                    if (weekType != null) {
                        this.buildSchedule(event.getChannel(), null, weekType, true);
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
        return true;
    }

    //ЧИСТО НАБРОСКИ. КОГДА БУДЕТ БД ЭТО ВСЕ БУДЕТ ПЕРЕДЕЛАНО
    private void buildSchedule(MessageChannelUnion messageChannelUnion, DayValue dayValue, WeekType weekType, boolean isDefault) {
        if (isDefault) {
            messageChannelUnion.sendMessage(Table.defaultSchedule(dayValue, weekType)).submit();
        }
    }
}
