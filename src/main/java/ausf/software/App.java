/*
 * Copyright © 2022 Shcherbina Daniil
 * License: http://opensource.org/licenses/MIT
 */

package ausf.software;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class App  {

    public static void main(String[] args) {

        if (args.length == 0)
        {
            System.err.println("Unable to start without token!");
            System.exit(1);
        }
        String token = args[0];

        JDA jda = JDABuilder.createDefault(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.MESSAGE_CONTENT).build();
        jda.addEventListener(new CommandHandler());

    }

}
