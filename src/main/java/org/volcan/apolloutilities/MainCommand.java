package org.volcan.apolloutilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainCommand implements CommandExecutor, TabCompleter {

    private final ApolloUtilities plugin;

    public MainCommand(ApolloUtilities plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (plugin.getConfig().getBoolean("command-with-permission") && !sender.hasPermission("apollo-utilities.command")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Lang.PREFIX + Lang.NOT_PERMISSION));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Lang.PREFIX + Lang.USAGE));
        } else if (args[0].equalsIgnoreCase("xray")) {
            if (args.length == 1) {
                if (!(sender instanceof Player)) return true;
                if (sender.hasPermission("apollo-utilities.xray")) {
                    plugin.enableXrayMod((Player) sender);
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Lang.PREFIX + Lang.NOT_PERMISSION));
                }
            } else {
                Player player = Bukkit.getPlayer(args[1]);

                if (player == null) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Lang.PREFIX + Lang.PLAYER_NOT_FOUND));
                } else {
                    if (player.hasPermission("apollo-utilities.xray")) {
                        plugin.enableXrayMod(player);
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Lang.PREFIX + Lang.NOT_PERMISSION_OTHER));
                    }
                }
            }
        } else if (args[0].equalsIgnoreCase("waypoins")) {
            if (args.length == 1) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Lang.PREFIX + Lang.USAGE));
            } else {

            }
        }
        return true;
    }

    private static final String[] COMPLETIONS = new String[] {
            "team",
            "xray",
            "bearn",
            "waypoins"
    };

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if (sender.hasPermission("apollo-utilities.command")) {
            if (args.length == 0) {
                List<String> list = new ArrayList<>();
                StringUtil.copyPartialMatches(args[0], Arrays.asList(COMPLETIONS), list);
                return list;
            } else {
                return null;
            }
        } else {
            return Collections.emptyList();
        }
    }
}
