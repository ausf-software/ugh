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

package ausf.software.bot;

import ausf.software.bot.channel.SettingsCommandListener;
import ausf.software.bot.common.CommonCommandListener;
import ausf.software.bot.personal.PrivateMessageCommandListener;
import ausf.software.bot.server.Notification;
import ausf.software.bot.server.ServerCommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.ArrayList;
import java.util.List;

public class App  {

    public static void main(String[] args) throws InterruptedException {

        if (args.length == 0)
        {
            System.err.println("Unable to start without token!");
            System.exit(1);
        }
        String token = args[0];

        List<GatewayIntent> intents = new ArrayList<>();
        intents.add(GatewayIntent.GUILD_MESSAGES);
        intents.add(GatewayIntent.DIRECT_MESSAGES);
        intents.add(GatewayIntent.MESSAGE_CONTENT);

        JDA jda = JDABuilder.createDefault(token, intents).build();

        jda.addEventListener(new CommonCommandListener());
        jda.addEventListener(new PrivateMessageCommandListener());
        jda.addEventListener(new SettingsCommandListener());
        jda.addEventListener(new ServerCommandListener());
        jda.addEventListener(new Notification());

        jda.awaitReady();

        Guild guild = jda.getGuildById("1026101822466838628");

        if (guild != null) {
            guild.upsertCommand("notification", "Создает уведомление, которое будет отправлено всем в ЛС")
                    .queue();

            guild.upsertCommand("set-notification-addresses", "Устанавливает роль, которая будет получать уведомления")
                    .addOption(OptionType.ROLE, "role", "роль, которая будет получать уведомления", true)
                    .queue();
        }

    }

}
