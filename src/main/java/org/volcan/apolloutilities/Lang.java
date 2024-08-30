package org.volcan.apolloutilities;

import org.bukkit.plugin.Plugin;

public class Lang {

    public static String PREFIX;
    public static String ENABLE_XRAY;
    public static String DISABLE_XRAY;
    public static String NOT_PERMISSION;
    public static String PLAYER_NOT_FOUND;
    public static String NOT_PERMISSION_OTHER;
    public static String USAGE;

    public static void registerLang(Plugin plugin) {
        PREFIX = plugin.getConfig().getString("lang.prefix", "&8[&bApollo-Utilities&8] ");
        ENABLE_XRAY = plugin.getConfig().getString("lang.enable-xray", "&fXrayMod &7has been &aactivated&7!");
        DISABLE_XRAY = plugin.getConfig().getString("lang.disable-xray", "&fXrayMod &7has been &cdesactivated&7!");
        NOT_PERMISSION = plugin.getConfig().getString("lang.not-permission", "&cYou do not have permissions to run this command!");
        PLAYER_NOT_FOUND = plugin.getConfig().getString("lang.player-not-found", "&cThat player does not exist!");
        NOT_PERMISSION_OTHER = plugin.getConfig().getString("lang.not-permission-other", "&cThat player does not have permissions for that!");
        USAGE = plugin.getConfig().getString("lang.usage", "&f/apollo-utilities <xray | team | bearn | waypoins>.");
    }
}
