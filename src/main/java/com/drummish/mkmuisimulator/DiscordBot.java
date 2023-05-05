package com.drummish.mkmuisimulator;

import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DiscordBot extends ListenerAdapter {
    FakeMKMui mk_mui;

    public DiscordBot(FakeMKMui mk_mui) {
        this.mk_mui = mk_mui;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot())
            return;

        String msg = event.getMessage().getContentRaw();
        MessageChannelUnion channel = event.getChannel();
        if (msg.startsWith("$")) {
            if (msg.length() > 2) {
                int bound = 0;
                if (msg.startsWith("$娘娘") || msg.startsWith("$阿娘") || msg.startsWith("$牙娘"))
                    bound = 3;
                else if (msg.startsWith("$MK妹"))
                    bound = 4;

                if (bound == 0)
                    return;

                if (msg.substring(bound).isBlank())
                    channel.sendMessage(this.mk_mui.reply_to_empty()).queue();
                else
                    channel.sendMessage(this.mk_mui.reply(true)).queue();
            }
        }
    }
}
