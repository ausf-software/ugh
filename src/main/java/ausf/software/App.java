/*
 * Copyright © 2022 Shcherbina Daniil, ***
 * License: http://opensource.org/licenses/MIT
 */

package ausf.software;

import ausf.software.channel.SettingsCommandListener;
import ausf.software.common.CommonCommandListener;
import ausf.software.personal.PrivateMessageCommandListener;
import ausf.software.server.ServerCommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.ArrayList;
import java.util.List;

public class App  {

    public static void main(String[] args) {

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

    }

}
