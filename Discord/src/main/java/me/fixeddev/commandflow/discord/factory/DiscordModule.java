package me.fixeddev.commandflow.discord.factory;

import me.fixeddev.commandflow.annotated.part.AbstractModule;
import me.fixeddev.commandflow.annotated.part.Key;
import me.fixeddev.commandflow.discord.annotation.Sender;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class DiscordModule extends AbstractModule {

    @Override
    public void configure() {
        bindFactory(Member.class, new MemberPartFactory());
        bindFactory(Message.class, new MessagePartFactory());
        bindFactory(TextChannel.class, new TextChannelPartFactory());
        bindFactory(new Key(Member.class, Sender.class), new MemberSenderPartFactory());
    }
}