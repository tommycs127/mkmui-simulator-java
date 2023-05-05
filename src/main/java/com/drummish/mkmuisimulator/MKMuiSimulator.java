package com.drummish.mkmuisimulator;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class MKMuiSimulator extends JavaPlugin {
    FileConfiguration config;
    JDA jda;
    FakeMKMui mk_mui;

    @Override
    public void onEnable() {
        this.loadConfig();

        mk_mui = new FakeMKMui();

        String discord_token = this.config.getString("discord-token", "");

        int discord_flag = this.startJDA(discord_token);

        if (discord_flag > 0)
            this.getLogger().info("You may edit the config and use \"/mkmui reload\" to connect to the Discord bot again.");

        this.getLogger().info("Enabled " + this.getName() + ". Here comes MK Mui!");
    }

    private void loadConfig() {
        this.config = getConfig();
        this.config.addDefault("discord-token", "");
        this.config.options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();
        saveDefaultConfig();
        this.loadConfig();
    }

    private int startJDA(@NotNull String token) {
        this.getLogger().info("Starting Discord bot...");

        if (token.isBlank()) {
            this.getLogger().info("The Discord token is blank.");
            return 1;
        }

        try {
            this.jda = JDABuilder.createDefault(token)
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                    .addEventListeners(new DiscordBot(this.mk_mui))
                    .build();
            this.jda.awaitReady();
            return 0;
        }
        catch (Exception e) {
            this.jda = null;
            this.getLogger().severe("An exception occurred while connecting to Discord bot: " + e.getMessage());
            return 2;
        }
    }

    private void stopJDA() {
        if (this.jda == null) {
            this.getLogger().info("No Discord bot connected. Ignoring...");
            return;
        }
        this.getLogger().info("Stopping Discord bot...");
        this.jda.shutdownNow();
    }

    private int restartJDA() {
        this.stopJDA();
        this.reloadConfig();
        return this.startJDA(config.getString("discord-token", ""));
    }

    @Override
    public void onDisable() {
        this.stopJDA();
        this.getLogger().info("Disabled " + this.getName() + ". Sleep la 7 head!");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("say")) {
                if (sender instanceof Player player && !player.hasPermission("mkmui.say")) {
                    player.sendMessage(this.msgWithPrefix(ChatColor.RED + "You do not have permission."));
                    return true;
                }

                if (args.length > 1) {
                    StringBuilder rest_args = new StringBuilder();
                    for (int i = 1; i < args.length; i++, rest_args.append(" "))
                        rest_args.append(args[i]);
                    sender.sendMessage(this.msgWithPrefix(String.valueOf(rest_args), false));
                    sender.sendMessage(this.msgWithPrefix(mk_mui.reply(false), true));
                }
                else {
                    sender.sendMessage(this.msgWithPrefix(mk_mui.reply_to_empty()));
                    sender.sendMessage(this.msgWithPrefix(ChatColor.GRAY + "(Usage: /" + label + " say <任何句話>)"));
                }
                return true;
            }
            else if (args[0].equalsIgnoreCase("reload")) {
                if (sender instanceof Player player && !player.hasPermission("mkmui.reload")) {
                    player.sendMessage(this.msgWithPrefix(ChatColor.RED + "You do not have permission."));
                    return true;
                }

                if (this.restartJDA() == 0)
                    if (sender instanceof Player player)
                        player.sendMessage(this.msgWithPrefix(ChatColor.GREEN + "Discord bot restarted!"));
                    else
                        this.getLogger().info("Discord bot restarted!");
                else
                    if (sender instanceof Player player)
                        player.sendMessage(this.msgWithPrefix(ChatColor.RED + "Failed to restart Discord bot! Check the console for details."));
                    else
                        this.getLogger().severe("Failed to restart Discord bot! Check the console for details.");
                return true;
            }
            else if (args[0].equalsIgnoreCase("version")) {
                if (sender instanceof Player player)
                    player.sendMessage(this.msgWithPrefix("MKMuiSimulator 7.7 is made by tommycs127."));
                else
                    this.getLogger().info("MKMuiSimulator 7.7 is made by tommycs127.");
                return true;
            }
        }
        return false;
    }

    private String msgWithPrefix(String msg) {
        return ChatColor.LIGHT_PURPLE + "[MK妹] " + ChatColor.RESET + msg;
    }

    private String msgWithPrefix(String msg, Boolean mk_mui_to_you) {
        return (mk_mui_to_you) ?
                ChatColor.LIGHT_PURPLE + "[MK妹 " + ChatColor.RESET + "-> 你" + ChatColor.LIGHT_PURPLE + "] " + ChatColor.RESET + msg:
                ChatColor.LIGHT_PURPLE + "[" + ChatColor.RESET + "你 -> " + ChatColor.LIGHT_PURPLE + "MK妹] " + ChatColor.RESET + msg;
    }
}
