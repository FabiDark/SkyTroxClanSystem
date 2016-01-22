package de.fabidark.clansystem.listener;

import de.fabidark.clansystem.files.ConfigFile;
import de.fabidark.clansystem.sqlmanager.ClanManager;
import de.fabidark.clansystem.sqlmanager.PlayerManager;
import de.fabidark.clansystem.utils.UUIDFetcher;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.UUID;

/**
 * Created by Fabian on 16.01.2016.
 */

public class DamageListener implements Listener {

    @EventHandler
    public void on(EntityDamageByEntityEvent e) {
        try {
            if(e.getDamager() instanceof Player) {
                Player Damager = (Player) e.getDamager();
                if(e.getEntity() instanceof Player) {
                    Player p = (Player) e.getEntity();
                    if(ClanManager.getAllMitglieder(PlayerManager.getClan(UUIDFetcher.getUUIDOf(p.getName()).toString())).contains(UUIDFetcher.getUUIDOf(Damager.getName()).toString())) {
                        if(ConfigFile.getClanMemberDamage().equalsIgnoreCase("true")) {
                            e.setCancelled(false);
                        } else {
                            e.setCancelled(true);
                        }
                    }
                }
            }
        } catch (Exception ex) {
        }
    }

}
