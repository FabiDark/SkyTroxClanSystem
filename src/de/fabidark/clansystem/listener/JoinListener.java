package de.fabidark.clansystem.listener;

import de.fabidark.clansystem.api.API;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Fabian on 18.01.2016.
 */

public class JoinListener implements Listener {

    @EventHandler
    public void on(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        API api = new API(p);

        api.addtPlayerToSQL();

    }

}
