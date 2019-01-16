package at.hagenberg.clc.bot;

        import at.hagenberg.clc.bot.listeners.VotingBotMessageListener;
        import net.dv8tion.jda.core.AccountType;
        import net.dv8tion.jda.core.JDA;
        import net.dv8tion.jda.core.JDABuilder;
        import net.dv8tion.jda.core.MessageBuilder;
        import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
        import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
        import net.dv8tion.jda.core.hooks.ListenerAdapter;
        import org.apache.commons.lang3.StringUtils;

        import javax.security.auth.login.LoginException;
        import java.text.SimpleDateFormat;
        import java.util.Calendar;
        import java.util.Date;

public class VotingBot extends ListenerAdapter {
    private static JDA jda;

    public static void main(String[] args) {
        try {
            jda = new JDABuilder(AccountType.BOT) //JDA als Bot instanzieren
                    .setToken("NTM0ODIwMjkxMDcxNzA1MTAx.Dx_K7Q.Y6yDu65DeiQop6-WtSgWJVOImEc") //euren Bot-Token hier einfügen
                    .addEventListener(new VotingBotMessageListener())
                    .buildAsync();
        } catch (LoginException e) {
            e.printStackTrace();
        }

    }


}
