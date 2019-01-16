package at.hagenberg.clc.bot.commands;

public enum VotingBotCommands {
    VOTING("%poll"),
    OPTION("%option"),
    VOTE("%vote"),
    SHOWVOTING("%showpoll"),
    START("%start"),
    HELP("%help");

    private String commandValue;

    VotingBotCommands(String commandValue){
        this.commandValue=commandValue;
    }

    public String getCommandValue() {
        return this.commandValue;
    }
}
