package org.volcan.apolloutilities;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.common.location.ApolloLocation;
import com.lunarclient.apollo.event.ApolloListener;
import com.lunarclient.apollo.event.EventBus;
import com.lunarclient.apollo.event.Listen;
import com.lunarclient.apollo.event.player.ApolloRegisterPlayerEvent;
import com.lunarclient.apollo.module.ApolloModuleManager;
import com.lunarclient.apollo.module.staffmod.StaffModModule;
import com.lunarclient.apollo.module.waypoint.Waypoint;
import com.lunarclient.apollo.module.waypoint.WaypointModule;
import com.lunarclient.apollo.player.ApolloPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;
import java.util.logging.Level;

public final class ApolloUtilities extends JavaPlugin implements ApolloListener {

    private StaffModModule staffModModule;
    private WaypointModule waypointModule;

    @Override
    public void onEnable() {
        if (!getServer().getPluginManager().isPluginEnabled("Apollo-Bukkit")) {
            getLogger().log(Level.SEVERE, "You need Apollo-Bukkit to start the plugin!");
            getLogger().log(Level.SEVERE, "Deactivating plugin.");
            getServer().getPluginManager().disablePlugin(this);
        } else {
            getLogger().info("§aActivating ApolloUtilities!");

            Lang.registerLang(this);

            loadModules();
            loadListeners();
        }
    }

    private void loadModules() {
        ApolloModuleManager manager = Apollo.getModuleManager();
        staffModModule = manager.getModule(StaffModModule.class);
        waypointModule = manager.getModule(WaypointModule.class);
    }

    private void loadListeners() {
        if (getConfig().getBoolean("auto-xray")) {
            EventBus.getBus().register(this);
        }
    }

    @Listen
    public void onApolloPlayerLogin(ApolloRegisterPlayerEvent event) {
        Player player = (Player) event.getPlayer();

        if (player.hasPermission("apollo-utilities.xray")) {
            enableXrayMod(player);
        }
    }

    public void addWaypointPlayer(Player player, Waypoint w) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(player.getUniqueId());
        apolloPlayerOpt.ifPresent(a -> waypointModule.displayWaypoint(a, w));
    }

    public void enableXrayMod(Player player) {
        Optional<ApolloPlayer> apolloPlayerOpt = Apollo.getPlayerManager().getPlayer(player.getUniqueId());
        apolloPlayerOpt.ifPresent(a -> staffModModule.enableAllStaffMods(a));

        if (getConfig().getBoolean("message-enable-xray")) {
            String message = Lang.PREFIX + Lang.ENABLE_XRAY;
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }

}
