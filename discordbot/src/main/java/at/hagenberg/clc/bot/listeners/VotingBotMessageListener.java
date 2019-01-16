package at.hagenberg.clc.bot.listeners;

import at.hagenberg.clc.bot.commands.VotingBotCommands;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class VotingBotMessageListener extends ListenerAdapter {
    private static final int MINUTEINMILLI= 60000;

    public void handleMessage(MessageReceivedEvent event, String message){
        String trimmedMessage = StringUtils.trim(message);
        if(StringUtils.startsWith(message.toLowerCase(), VotingBotCommands.VOTING.getCommandValue())){
            String commandValue = StringUtils.trim(StringUtils.removeStart(message,VotingBotCommands.VOTING.getCommandValue()));
            if(commandValue.length()<=0){
                event.getChannel().sendMessage("Sorry I didn't understand you :(").queue();
            }else{
                event.getChannel().sendMessage("Starting Poll for: "+commandValue+ " - add options with %option <Option>").queue();
            }
        }else if(StringUtils.startsWith(message.toLowerCase(), VotingBotCommands.OPTION.getCommandValue())){
            String commandValue = StringUtils.trim(StringUtils.removeStart(message,VotingBotCommands.OPTION.getCommandValue()));
            if(commandValue.length()<=0){
                event.getChannel().sendMessage("Sorry I didn't understand you :(").queue();
            }else{
                event.getChannel().sendMessage("Option "+commandValue+" added").queue();
            }
        }else if(StringUtils.startsWith(message.toLowerCase(), VotingBotCommands.VOTE.getCommandValue())){
            String commandValue = StringUtils.trim(StringUtils.removeStart(message,VotingBotCommands.VOTE.getCommandValue()));
            if(commandValue.length()<=0){
                event.getChannel().sendMessage("Sorry I didn't understand you :(").queue();
            }else{
                event.getChannel().sendMessage("Voted for "+commandValue+" added").queue();
            }
        }else if(StringUtils.startsWith(message.toLowerCase(), VotingBotCommands.SHOWVOTING.getCommandValue())){
            String commandValue = StringUtils.trim(StringUtils.removeStart(message,VotingBotCommands.SHOWVOTING.getCommandValue()));
            event.getChannel().sendMessage("Poll: ...").queue();
        }else if(StringUtils.startsWith(message.toLowerCase(), VotingBotCommands.START.getCommandValue())){
            Calendar date = Calendar.getInstance();
            long t= date.getTimeInMillis();
            Date currentPlusFive=new Date(t + (5 * MINUTEINMILLI));
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            event.getChannel().sendMessage("Poll active until "+ formatter.format(currentPlusFive)).queue();
        }else if(StringUtils.startsWith(message.toLowerCase(), VotingBotCommands.HELP.getCommandValue())){
            String commandValue = StringUtils.trim(StringUtils.removeStart(message,VotingBotCommands.HELP.getCommandValue()));
            event.getAuthor().openPrivateChannel().queue((channel)-> {
                StringBuilder builder = new StringBuilder();
                builder.append("Hello, my name is Didi!\n");
                builder.append("To start a poll, enter %poll <name>.\n");
                builder.append("To add an option, enter %option <value>.\n");
                builder.append("To start a poll, enter %start.\n");
                builder.append("To vote, enter %vote <value>.\n");
                builder.append("To show the result of last poll, enter %showpoll.\n");
                builder.append("To get help, enter %help.");
                channel.sendMessage(builder.toString()).queue();
            });

        }
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        event.getUser().openPrivateChannel().queue((channel)-> {
            StringBuilder builder = new StringBuilder();
            builder.append("Hello, my name is Didi!\n");
            builder.append("To start a poll, enter %poll <name>.\n");
            builder.append("To add an option, enter %option <value>.\n");
            builder.append("To start a poll, enter %start.\n");
            builder.append("To vote, enter %vote <value>.\n");
            builder.append("To show the result of last poll, enter %showpoll.\n");
            builder.append("To get help, enter %help.");
            channel.sendMessage(builder.toString()).queue();
        });
    }



    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        super.onMessageReceived(event);
        handleMessage(event, event.getMessage().getContentRaw());
    }
}
